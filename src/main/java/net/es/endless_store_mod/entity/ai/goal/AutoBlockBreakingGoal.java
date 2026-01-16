package net.es.endless_store_mod.entity.ai.goal;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AutoBlockBreakingGoal extends SmartBlockBreakingGoal {

    public static class BlockBreakingData {
        public final int breakTime;
        public final float hardness;
        public final boolean dropsItems;

        public BlockBreakingData(int breakTime, float hardness, boolean dropsItems) {
            this.breakTime = breakTime;
            this.hardness = hardness;
            this.dropsItems = dropsItems;
        }

        public BlockBreakingData(int breakTime) {
            this(breakTime, 1.0f, true);
        }
    }

    private final Map<Block, BlockBreakingData> blockData;
    private int breakProgress;
    private int prevBreakProgress;
    private int currentMaxBreakTime;
    private final float timeMultiplier;

    public AutoBlockBreakingGoal(PathAwareEntity mob, Set<Block> breakableBlocks, float timeMultiplier) {
        super(mob, breakableBlocks);
        this.timeMultiplier = timeMultiplier;
        this.blockData = createBlockData(breakableBlocks);
    }

    private Map<Block, BlockBreakingData> createBlockData(Set<Block> blocks) {
        Map<Block, BlockBreakingData> config = new HashMap<>();

        for (Block block : blocks) {
            float hardness = block.getDefaultState().getHardness(null, BlockPos.ORIGIN);
            int breakTime;

            if (hardness < 0) {
                continue;
            } else if (hardness == 0) {
                breakTime = 10;
            } else {
                breakTime = (int)(hardness * 20 * timeMultiplier);
            }

            config.put(block, new BlockBreakingData(breakTime, hardness, true));
        }

        return config;
    }

    @Override
    protected boolean isBreakable(Block block) {
        return blockData.containsKey(block);
    }

    @Override
    public boolean canStart() {
        boolean parentCanStart = super.canStart();
        if (parentCanStart && targetBlock != null) {
            Block block = mob.getWorld().getBlockState(targetBlock).getBlock();
            BlockBreakingData data = blockData.get(block);
            this.currentMaxBreakTime = data != null ? data.breakTime : 60;
            this.breakProgress = 0;
            this.prevBreakProgress = -1;
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        if (!super.shouldContinue()) return false;
        return breakProgress < currentMaxBreakTime;
    }

    @Override
    public void tick() {
        if (targetBlock == null) return;

        BlockState state = mob.getWorld().getBlockState(targetBlock);
        Block blockType = state.getBlock();
        BlockBreakingData data = blockData.get(blockType);

        if (data == null) {
            stop();
            return;
        }

        double distance = mob.getPos().distanceTo(Vec3d.ofCenter(targetBlock));

        if (distance > 2.5) {
            mob.getNavigation().startMovingTo(
                    targetBlock.getX(),
                    targetBlock.getY(),
                    targetBlock.getZ(),
                    1.0
            );
            return;
        } else {
            mob.getNavigation().stop();
        }

        mob.getLookControl().lookAt(
                targetBlock.getX() + 0.5,
                targetBlock.getY() + 0.5,
                targetBlock.getZ() + 0.5
        );

        int animationFreq = Math.max(5, (int)(10 / data.hardness));
        if (mob.getRandom().nextInt(animationFreq) == 0) {
            mob.getWorld().syncWorldEvent(1019, targetBlock, 0);
            mob.swingHand(mob.getActiveHand());
        }

        breakProgress++;
        int progress = (int)((float)breakProgress / (float)currentMaxBreakTime * 10.0F);

        if (progress != prevBreakProgress) {
            mob.getWorld().setBlockBreakingInfo(mob.getId(), targetBlock, progress);
            prevBreakProgress = progress;
        }

        if (breakProgress >= currentMaxBreakTime) {
            finishBreakingBlock(targetBlock);
            mob.getWorld().syncWorldEvent(1021, targetBlock, 0);
            mob.getWorld().syncWorldEvent(2001, targetBlock, Block.getRawIdFromState(state));
            mob.getWorld().setBlockBreakingInfo(mob.getId(), targetBlock, -1);

            breakProgress = 0;
            prevBreakProgress = -1;
            targetBlock = findOptimalBlockToBreak();

            if (targetBlock != null) {
                Block newBlockType = mob.getWorld().getBlockState(targetBlock).getBlock();
                BlockBreakingData newData = blockData.get(newBlockType);
                currentMaxBreakTime = newData != null ? newData.breakTime : 60;
            }
        }
    }

    @Override
    protected void finishBreakingBlock(BlockPos pos) {
        BlockState state = mob.getWorld().getBlockState(pos);
        Block blockType = state.getBlock();
        BlockBreakingData data = blockData.get(blockType);

        if (data != null && data.dropsItems) {
            mob.getWorld().breakBlock(pos, true, mob);
        } else {
            mob.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    @Override
    public void stop() {
        if (targetBlock != null) {
            mob.getWorld().setBlockBreakingInfo(mob.getId(), targetBlock, -1);
        }
        breakProgress = 0;
        prevBreakProgress = -1;
        super.stop();
    }
}