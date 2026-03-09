package net.es.endless_store_mod.world.features;

import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ChainMailTrees extends Feature<NoneFeatureConfiguration> {

    // Настраиваемые параметры дерева
    private static final int MIN_TRUNK_HEIGHT = 3;
    private static final int MAX_TRUNK_HEIGHT = 11;
    private static final int MIN_BRANCH_COUNT = 2;
    private static final int MAX_BRANCH_COUNT = 6;
    private static final int MIN_BRANCH_LENGTH = 2;
    private static final int MAX_BRANCH_LENGTH = 8;

    // Вероятности движений
    private static final float HORIZONTAL_MOVE_CHANCE = 0.8f;
    private static final float VERTICAL_MOVE_CHANCE = 0.4f;

    public ChainMailTrees() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        if (level.isClientSide()) {
            return false;
        }

        // Находим самую высокую Y-координату в этой колонке X-Z
        BlockPos surfacePos = findSurfacePos(level, origin);
        if (surfacePos == null) {
            return false; // Не нашли подходящую поверхность
        }

        // Начальная позиция для дерева - над поверхностью
        BlockPos treeOrigin = surfacePos.above();

        // Проверяем, что над поверхностью достаточно места для ствола
        int trunkHeightRange = MAX_TRUNK_HEIGHT - MIN_TRUNK_HEIGHT;
        if (trunkHeightRange < 0) return false;

        int trunkHeight = MIN_TRUNK_HEIGHT + (trunkHeightRange > 0 ? random.nextInt(trunkHeightRange + 1) : 0);

        if (!canGenerateTrunk(level, treeOrigin, trunkHeight)) {
            return false;
        }

        // Строим ствол
        for (int y = 0; y < trunkHeight; y++) {
            BlockPos trunkPos = treeOrigin.above(y);
            level.setBlock(trunkPos, EndlessStoreBlocks.CHAIN_MAIL.get().defaultBlockState(), 3);
        }

        // Генерация количества ветвей
        int branchCountRange = MAX_BRANCH_COUNT - MIN_BRANCH_COUNT;
        if (branchCountRange < 0) return false;

        int branchCount = MIN_BRANCH_COUNT + (branchCountRange > 0 ? random.nextInt(branchCountRange + 1) : 0);

        // Генерируем ветви
        for (int i = 0; i < branchCount; i++) {
            int branchHeight = trunkHeight > 1 ? 1 + random.nextInt(trunkHeight - 1) : 0;
            if (branchHeight > 0) {
                BlockPos branchStart = treeOrigin.above(branchHeight);
                generateBranch(level, branchStart, random);
            }
        }

        return true;
    }

    // Находит самую высокую позицию поверхности в колонке X-Z
    private BlockPos findSurfacePos(WorldGenLevel level, BlockPos origin) {
        int maxY = level.getMaxBuildHeight();
        int minY = level.getMinBuildHeight();

        for (int y = maxY - 1; y >= minY; y--) {
            BlockPos checkPos = new BlockPos(origin.getX(), y, origin.getZ());
            BlockState state = level.getBlockState(checkPos);

            // Если блок непрозрачный и твердый - это поверхность
            if (state.isSolid() && state.isSolidRender(level, checkPos)) {
                return checkPos;
            }
        }

        return null; // Не нашли подходящую поверхность
    }

    // Проверка возможности генерации ствола
    private boolean canGenerateTrunk(WorldGenLevel level, BlockPos origin, int trunkHeight) {
        for (int y = 0; y < trunkHeight; y++) {
            BlockPos checkPos = origin.above(y);
            if (!level.getBlockState(checkPos).isAir()) {
                return false;
            }
        }
        return true;
    }

    // Генерация одной ветви
    private void generateBranch(WorldGenLevel level, BlockPos startPos, RandomSource random) {
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

            if (level.getBlockState(currentPos).isAir()) {
                level.setBlock(currentPos, EndlessStoreBlocks.CHAIN_MAIL.get().defaultBlockState(), 3);
            } else {
                break;
            }
        }
    }

    private Direction getRandomHorizontalDirection(RandomSource random) {
        Direction[] directions = {
                Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST
        };
        return directions[random.nextInt(directions.length)];
    }

    private Direction getRandomVerticalDirection(RandomSource random) {
        return random.nextBoolean() ? Direction.UP : Direction.DOWN;
    }
}