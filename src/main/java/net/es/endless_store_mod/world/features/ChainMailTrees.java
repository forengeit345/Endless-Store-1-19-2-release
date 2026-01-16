package net.es.endless_store_mod.world.features;

import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ChainMailTrees extends Feature<DefaultFeatureConfig> {

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
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        if (world.isClient()) {
            return false;
        }

        // Находим самую высокую Y-координату в этой колонке X-Z
        BlockPos surfacePos = findSurfacePos(world, origin);
        if (surfacePos == null) {
            return false; // Не нашли подходящую поверхность
        }

        // Начальная позиция для дерева - над поверхностью
        BlockPos treeOrigin = surfacePos.up();

        // Проверяем, что над поверхностью достаточно места для ствола
        int trunkHeightRange = MAX_TRUNK_HEIGHT - MIN_TRUNK_HEIGHT;
        if (trunkHeightRange < 0) return false;

        int trunkHeight = MIN_TRUNK_HEIGHT + (trunkHeightRange > 0 ? random.nextInt(trunkHeightRange + 1) : 0);

        if (!canGenerateTrunk(world, treeOrigin, trunkHeight)) {
            return false;
        }

        // Строим ствол
        for (int y = 0; y < trunkHeight; y++) {
            BlockPos trunkPos = treeOrigin.up(y);
            world.setBlockState(trunkPos, EndlessStoreBlocks.CHAIN_MAIL.getDefaultState(), 3);
        }

        // Генерация количества ветвей
        int branchCountRange = MAX_BRANCH_COUNT - MIN_BRANCH_COUNT;
        if (branchCountRange < 0) return false;

        int branchCount = MIN_BRANCH_COUNT + (branchCountRange > 0 ? random.nextInt(branchCountRange + 1) : 0);

        // Генерируем ветви
        for (int i = 0; i < branchCount; i++) {
            int branchHeight = trunkHeight > 1 ? 1 + random.nextInt(trunkHeight - 1) : 0;
            if (branchHeight > 0) {
                BlockPos branchStart = treeOrigin.up(branchHeight);
                generateBranch(world, branchStart, random);
            }
        }

        return true;
    }

    // Находит самую высокую позицию поверхности в колонке X-Z
    private BlockPos findSurfacePos(StructureWorldAccess world, BlockPos origin) {
        // Начинаем поиск с максимальной высоты мира и идем вниз
        int worldHeight = world.getHeight();

        for (int y = worldHeight - 1; y >= world.getBottomY(); y--) {
            BlockPos checkPos = new BlockPos(origin.getX(), y, origin.getZ());
            BlockState state = world.getBlockState(checkPos);

            // Если блок непрозрачный и твердый - это поверхность
            if (state.isOpaque() && state.isSolidBlock(world, checkPos)) {
                return checkPos;
            }
        }

        return null; // Не нашли подходящую поверхность
    }

    // Проверка возможности генерации ствола
    private boolean canGenerateTrunk(StructureWorldAccess world, BlockPos origin, int trunkHeight) {
        for (int y = 0; y < trunkHeight; y++) {
            BlockPos checkPos = origin.up(y);
            if (!world.getBlockState(checkPos).isAir()) {
                return false;
            }
        }
        return true;
    }

    // Генерация одной ветви
    private void generateBranch(StructureWorldAccess world, BlockPos startPos, Random random) {
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
                currentPos = currentPos.offset(horizDir);
            }

            if (moveVert) {
                Direction vertDir = getRandomVerticalDirection(random);
                currentPos = currentPos.offset(vertDir);
            }

            if (world.getBlockState(currentPos).isAir()) {
                world.setBlockState(currentPos, EndlessStoreBlocks.CHAIN_MAIL.getDefaultState(), 3);
            } else {
                break;
            }
        }
    }

    private Direction getRandomHorizontalDirection(Random random) {
        Direction[] directions = {
                Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST
        };
        return directions[random.nextInt(directions.length)];
    }

    private Direction getRandomVerticalDirection(Random random) {
        return random.nextBoolean() ? Direction.UP : Direction.DOWN;
    }
}