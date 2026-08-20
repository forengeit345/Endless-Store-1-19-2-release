package net.es.world.features;

import net.es.EndlessStoreMod;
import net.es.block.EndlessStoreBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ChainMailTrees extends Feature<NoneFeatureConfiguration> {

    private static final int MIN_TRUNK_HEIGHT = 3;
    private static final int MAX_TRUNK_HEIGHT = 11;
    private static final int MIN_BRANCH_COUNT = 2;
    private static final int MAX_BRANCH_COUNT = 6;
    private static final int MIN_BRANCH_LENGTH = 2;
    private static final int MAX_BRANCH_LENGTH = 8;

    private static final float HORIZONTAL_MOVE_CHANCE = 0.8f;
    private static final float VERTICAL_MOVE_CHANCE = 0.4f;

    public ChainMailTrees() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        if (world.isClientSide()) {
            return false;
        }

        Block chainMail = getBlock(EndlessStoreBlocks.CHAIN_MAIL);

        BlockPos surfacePos = findSurfacePos(world, origin);
        if (surfacePos == null) {
            return false;
        }

        BlockPos treeOrigin = surfacePos.above();

        int trunkHeightRange = MAX_TRUNK_HEIGHT - MIN_TRUNK_HEIGHT;
        if (trunkHeightRange < 0) return false;
        int trunkHeight = MIN_TRUNK_HEIGHT + (trunkHeightRange > 0 ? random.nextInt(trunkHeightRange + 1) : 0);

        if (!canGenerateTrunk(world, treeOrigin, trunkHeight)) {
            return false;
        }

        for (int y = 0; y < trunkHeight; y++) {
            BlockPos trunkPos = treeOrigin.above(y);
            world.setBlock(trunkPos, chainMail.defaultBlockState(), 3);
        }

        int branchCountRange = MAX_BRANCH_COUNT - MIN_BRANCH_COUNT;
        if (branchCountRange < 0) return false;
        int branchCount = MIN_BRANCH_COUNT + (branchCountRange > 0 ? random.nextInt(branchCountRange + 1) : 0);

        for (int i = 0; i < branchCount; i++) {
            int branchHeight = trunkHeight > 1 ? 1 + random.nextInt(trunkHeight - 1) : 0;
            if (branchHeight > 0) {
                BlockPos branchStart = treeOrigin.above(branchHeight);
                generateBranch(world, branchStart, random, chainMail);
            }
        }

        return true;
    }

    private BlockPos findSurfacePos(WorldGenLevel world, BlockPos origin) {
        for (int y = world.getMaxBuildHeight() - 1; y >= world.getMinBuildHeight(); y--) {
            BlockPos checkPos = new BlockPos(origin.getX(), y, origin.getZ());
            BlockState state = world.getBlockState(checkPos);
            if (state.isSolid()) {
                return checkPos;
            }
        }
        return null;
    }

    private boolean canGenerateTrunk(WorldGenLevel world, BlockPos origin, int trunkHeight) {
        for (int y = 0; y < trunkHeight; y++) {
            BlockPos checkPos = origin.above(y);
            if (!world.getBlockState(checkPos).isAir()) {
                return false;
            }
        }
        return true;
    }

    private void generateBranch(WorldGenLevel world, BlockPos startPos, RandomSource random, Block block) {
        int branchLengthRange = MAX_BRANCH_LENGTH - MIN_BRANCH_LENGTH;
        if (branchLengthRange < 0) return;

        int branchLength = MIN_BRANCH_LENGTH + (branchLengthRange > 0 ? random.nextInt(branchLengthRange + 1) : 0);
        BlockPos currentPos = startPos;

        for (int i = 0; i < branchLength; i++) {
            boolean moveHoriz = random.nextFloat() < HORIZONTAL_MOVE_CHANCE;
            boolean moveVert = random.nextFloat() < VERTICAL_MOVE_CHANCE;

            if (!moveHoriz && !moveVert) {
                moveHoriz = true;
            }

            if (moveHoriz) {
                Direction horizDir = getRandomHorizontalDirection(random);
                currentPos = currentPos.relative(horizDir);
            }

            if (moveVert) {
                Direction vertDir = getRandomVerticalDirection(random);
                currentPos = currentPos.relative(vertDir);
            }

            if (world.getBlockState(currentPos).isAir()) {
                world.setBlock(currentPos, block.defaultBlockState(), 3);
            } else {
                break;
            }
        }
    }

    private Direction getRandomHorizontalDirection(RandomSource random) {
        Direction[] directions = { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };
        return directions[random.nextInt(directions.length)];
    }

    private Direction getRandomVerticalDirection(RandomSource random) {
        return random.nextBoolean() ? Direction.UP : Direction.DOWN;
    }

    private Block getBlock(String name) {
        return BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name));
    }
}