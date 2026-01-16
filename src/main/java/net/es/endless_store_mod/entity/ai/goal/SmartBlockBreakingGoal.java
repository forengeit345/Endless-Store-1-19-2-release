package net.es.endless_store_mod.entity.ai.goal;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Set;

public class SmartBlockBreakingGoal extends Goal {
    protected final PathAwareEntity mob;
    protected final Set<Block> breakableBlocks;
    protected BlockPos targetBlock;

    public SmartBlockBreakingGoal(PathAwareEntity mob, Set<Block> breakableBlocks) {
        this.mob = mob;
        this.breakableBlocks = breakableBlocks;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (this.mob.getTarget() == null) return false;
        this.targetBlock = findOptimalBlockToBreak();
        return this.targetBlock != null;
    }

    protected BlockPos findOptimalBlockToBreak() {
        if (mob.getTarget() == null) return null;

        Vec3d mobPos = mob.getPos();
        Vec3d targetPos = mob.getTarget().getPos();
        Direction toTarget = getHorizontalDirection(mobPos, targetPos);

        int heightDiff = (int) (targetPos.y - mobPos.y);

        if (heightDiff > 0) {
            BlockPos aboveHead = mob.getBlockPos().up(2);
            if (!isPassable(aboveHead) && isBreakable(mob.getWorld().getBlockState(aboveHead).getBlock())) {
                return aboveHead;
            }
        }

        BlockPos frontPos = BlockPos.ofFloored(mobPos).offset(toTarget);
        if (isPassable(frontPos) && isPassable(frontPos.up())) {
            return null;
        }

        if (canJumpOver(frontPos)) {
            return null;
        }

        int wallHeight = calculateWallHeight(frontPos, toTarget);

        if (heightDiff > 0 && wallHeight >= 1) {
            if (canClimbUp(frontPos, heightDiff)) {
                return selectBlockForStairs(frontPos, heightDiff);
            }
        }

        if (wallHeight == 2) {
            return findBreakableBlockInColumn(frontPos, 1, 2);
        } else if (wallHeight >= 3) {
            if (Math.abs(heightDiff) <= 1) {
                return selectBlockForDoorway(frontPos, heightDiff);
            } else {
                return selectBlockForStairs(frontPos, heightDiff);
            }
        }

        return findBreakableBlockInColumn(frontPos, 0, 1);
    }

    private BlockPos findBreakableBlockInColumn(BlockPos basePos, int minYOffset, int maxYOffset) {
        World world = mob.getWorld();
        for (int y = minYOffset; y <= maxYOffset; y++) {
            BlockPos checkPos = basePos.up(y);
            if (isBreakable(world.getBlockState(checkPos).getBlock())) {
                return checkPos;
            }
        }
        return null;
    }

    private BlockPos selectBlockForDoorway(BlockPos basePos, int heightDiff) {
        BlockPos headLevel = basePos.up(1);
        if (isBreakable(mob.getWorld().getBlockState(headLevel).getBlock())) {
            return headLevel;
        }
        if (isBreakable(mob.getWorld().getBlockState(basePos).getBlock())) {
            return basePos;
        }
        return null;
    }

    private BlockPos selectBlockForStairs(BlockPos basePos, int heightDiff) {
        int step = heightDiff > 0 ? 1 : -1;
        World world = mob.getWorld();

        if (step > 0) {
            BlockPos aboveHead = mob.getBlockPos().up(2);

            if (!isPassable(aboveHead) && isBreakable(world.getBlockState(aboveHead).getBlock())) {
                return aboveHead;
            }

            BlockPos headLevel = mob.getBlockPos().up(1);
            if (!isPassable(headLevel) && isBreakable(world.getBlockState(headLevel).getBlock())) {
                return headLevel;
            }
        }

        BlockPos firstStep = basePos.up(step);
        if (isBreakable(world.getBlockState(firstStep).getBlock())) {
            return firstStep;
        }

        if (Math.abs(heightDiff) > 1) {
            BlockPos secondStep = basePos.up(step * 2);
            if (isBreakable(world.getBlockState(secondStep).getBlock())) {
                return secondStep;
            }
        }

        return null;
    }

    private boolean canJumpOver(BlockPos obstacleBase) {
        World world = mob.getWorld();
        boolean isObstaclePassable = isPassable(obstacleBase) || isBreakable(world.getBlockState(obstacleBase).getBlock());
        boolean hasAirAbove = isPassable(obstacleBase.up(1)) && isPassable(obstacleBase.up(2));
        return isObstaclePassable && hasAirAbove;
    }

    private int calculateWallHeight(BlockPos startPos, Direction dir) {
        World world = mob.getWorld();
        int height = 0;

        for (int yOffset = 0; yOffset <= 2; yOffset++) {
            BlockPos checkPos = startPos.up(yOffset);
            if (!isPassable(checkPos) && isBreakable(world.getBlockState(checkPos).getBlock())) {
                height = Math.max(height, yOffset + 1);
            } else if (!isPassable(checkPos) && !isBreakable(world.getBlockState(checkPos).getBlock())) {
                break;
            }
        }

        return height;
    }

    private boolean canClimbUp(BlockPos frontPos, int heightDiff) {
        if (heightDiff <= 0) return false;

        World world = mob.getWorld();
        BlockPos mobPos = mob.getBlockPos();

        BlockPos aboveHead = mobPos.up(2);
        if (!isPassable(aboveHead) && !isBreakable(world.getBlockState(aboveHead).getBlock())) {
            return false;
        }

        BlockPos headLevel = mobPos.up(1);
        if (!isPassable(headLevel) && !isBreakable(world.getBlockState(headLevel).getBlock())) {
            return false;
        }

        BlockPos climbPos = frontPos.up(1);
        if (!isPassable(climbPos) && !isBreakable(world.getBlockState(climbPos).getBlock())) {
            return false;
        }

        BlockPos aboveClimbPos = climbPos.up(1);
        if (!isPassable(aboveClimbPos) && !isBreakable(world.getBlockState(aboveClimbPos).getBlock())) {
            return false;
        }

        return true;
    }

    protected boolean isPassable(BlockPos pos) {
        return mob.getWorld().getBlockState(pos).isAir();
    }

    protected boolean isBreakable(Block block) {
        return breakableBlocks.contains(block);
    }

    private Direction getHorizontalDirection(Vec3d from, Vec3d to) {
        double dx = to.x - from.x;
        double dz = to.z - from.z;
        if (Math.abs(dx) > Math.abs(dz)) {
            return dx > 0 ? Direction.EAST : Direction.WEST;
        } else {
            return dz > 0 ? Direction.SOUTH : Direction.NORTH;
        }
    }

    @Override
    public boolean shouldContinue() {
        if (targetBlock == null || mob.getTarget() == null) return false;
        double distance = mob.getPos().distanceTo(Vec3d.ofCenter(targetBlock));
        return distance < 3.0 && isBreakable(mob.getWorld().getBlockState(targetBlock).getBlock());
    }

    @Override
    public void tick() {
        if (targetBlock != null) {
            finishBreakingBlock(targetBlock);
        }
    }

    protected void finishBreakingBlock(BlockPos pos) {
        mob.getWorld().breakBlock(pos, true, mob);
    }

    @Override
    public void stop() {
        targetBlock = null;
        mob.getNavigation().stop();
    }
}