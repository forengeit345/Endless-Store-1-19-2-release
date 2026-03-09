package net.es.endless_store_mod.world.features;

import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class AbandonedHills extends Feature<NoneFeatureConfiguration> {
    private static final List<Block> SURFACE_BLOCKS = List.of(
            EndlessStoreBlocks.CONCRETE_MUD.get(),
            EndlessStoreBlocks.GREEN_MUD.get()
    );

    // Настраиваемые параметры
    private static final int BASE_RADIUS = 14;
    private static final int RADIUS_VARIATION = 15;
    private static final int TERRACE_HEIGHT = 2;
    private static final int MIN_TERRACES = 2;
    private static final int MAX_TERRACES = 7;
    private static final float SMOOTHING_FACTOR = 0.75f;

    public AbandonedHills() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        if (level.isClientSide()) return false;

        int radius = BASE_RADIUS + random.nextInt(RADIUS_VARIATION);
        int terraceCount = MIN_TERRACES + random.nextInt(MAX_TERRACES - MIN_TERRACES + 1);
        int maxHeight = terraceCount * TERRACE_HEIGHT;

        // Создаём карту высот
        int size = radius * 2 + 1;
        int[][] heightMap = new int[size][size];

        // Заполняем карту высот
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double distance = Math.sqrt(x * x + z * z) / radius;

                if (distance > 1.0) {
                    heightMap[x + radius][z + radius] = 0;
                    continue;
                }

                double terraceProgress = (1 - distance) * terraceCount;
                int baseTerraceLevel = (int) terraceProgress;
                double terraceFraction = terraceProgress - baseTerraceLevel;

                int height;
                if (terraceFraction < SMOOTHING_FACTOR) {
                    height = baseTerraceLevel * TERRACE_HEIGHT;
                } else {
                    float smoothProgress = (float) ((terraceFraction - SMOOTHING_FACTOR) / (1 - SMOOTHING_FACTOR));
                    smoothProgress = Math.min(1, Math.max(0, smoothProgress));
                    height = (int) (baseTerraceLevel * TERRACE_HEIGHT + smoothProgress * TERRACE_HEIGHT);
                }

                height = Math.max(0, Math.min(maxHeight, height));
                heightMap[x + radius][z + radius] = height;
            }
        }

        // Сглаживание
        heightMap = applyHeightMapSmoothing(heightMap, radius, 1);

        // Генерация холма
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int height = heightMap[x + radius][z + radius];
                if (height == 0) continue;

                BlockPos pos = origin.offset(x, 0, z);

                for (int y = 0; y < height; y++) {
                    BlockPos terrainPos = pos.above(y);
                    if (y == 0 || level.getBlockState(terrainPos.below()).isSolid()) {
                        level.setBlock(terrainPos, EndlessStoreBlocks.PROBABLY_MUD.get().defaultBlockState(), 3);
                    }
                }

                if (random.nextInt(10) >= 6 && height > 0) {
                    BlockPos surfacePos = pos.above(height);
                    BlockState currentState = level.getBlockState(surfacePos);

                    if (currentState.is(EndlessStoreBlocks.PROBABLY_MUD.get())) {
                        Block selectedBlock = SURFACE_BLOCKS.get(random.nextInt(SURFACE_BLOCKS.size()));
                        level.setBlock(surfacePos, selectedBlock.defaultBlockState(), 3);
                    }
                }
            }
        }

        return true;
    }

    // Сглаживание карты высот (без изменений, кроме типов)
    private int[][] applyHeightMapSmoothing(int[][] heightMap, int radius, int iterations) {
        int size = radius * 2 + 1;
        int[][] smoothed = new int[size][size];

        for (int i = 0; i < size; i++) {
            System.arraycopy(heightMap[i], 0, smoothed[i], 0, size);
        }

        for (int iter = 0; iter < iterations; iter++) {
            int[][] temp = new int[size][size];

            for (int x = 1; x < size - 1; x++) {
                for (int z = 1; z < size - 1; z++) {
                    int sum = smoothed[x][z] * 4;
                    sum += smoothed[x-1][z] + smoothed[x+1][z] + smoothed[x][z-1] + smoothed[x][z+1];
                    sum += smoothed[x-1][z-1] + smoothed[x-1][z+1] + smoothed[x+1][z-1] + smoothed[x+1][z+1];
                    temp[x][z] = sum / 12;
                }
            }

            for (int i = 0; i < size; i++) {
                temp[i][0] = smoothed[i][0];
                temp[i][size-1] = smoothed[i][size-1];
                temp[0][i] = smoothed[0][i];
                temp[size-1][i] = smoothed[size-1][i];
            }

            smoothed = temp;
        }

        return smoothed;
    }
}