package net.es.endless_store_mod.world.features;

import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;


public class AbandonedHills extends Feature<DefaultFeatureConfig> {
    private static final List<Block> SURFACE_BLOCKS = List.of(
            EndlessStoreBlocks.CONCRETE_MUD,
            EndlessStoreBlocks.GREEN_MUD
    );

    // Настраиваемые параметры
    private static final int BASE_RADIUS = 14;
    private static final int RADIUS_VARIATION = 15;
    private static final int TERRACE_HEIGHT = 2;
    private static final int MIN_TERRACES = 2;
    private static final int MAX_TERRACES = 7;
    private static final float SMOOTHING_FACTOR = 0.75f; // От 0 (резкие террасы) до 1 (плавные склоны) было 0.35

    public AbandonedHills() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        if (context.getWorld().isClient()) return false;

        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        int radius = BASE_RADIUS + random.nextInt(RADIUS_VARIATION);
        int terraceCount = MIN_TERRACES + random.nextInt(MAX_TERRACES - MIN_TERRACES + 1);
        int maxHeight = terraceCount * TERRACE_HEIGHT; // Теперь используется правильно

        // Создаем карту высот для террасирования
        int[][] heightMap = new int[radius * 2 + 1][radius * 2 + 1];

        // Заполняем карту высот с учетом террас
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double distance = Math.sqrt(x * x + z * z) / radius;

                if (distance > 1.0) {
                    heightMap[x + radius][z + radius] = 0;
                    continue;
                }

                // Определяем базовый уровень террасы на основе расстояния
                double terraceProgress = (1 - distance) * terraceCount;
                int baseTerraceLevel = (int) terraceProgress;
                double terraceFraction = terraceProgress - baseTerraceLevel;

                // Применяем сглаживание между уровнями террас
                int height;
                if (terraceFraction < SMOOTHING_FACTOR) {
                    // Резкий переход к следующему уровню
                    height = baseTerraceLevel * TERRACE_HEIGHT;
                } else {
                    // Плавный переход между уровнями
                    float smoothProgress = (float) ((terraceFraction - SMOOTHING_FACTOR) / (1 - SMOOTHING_FACTOR));
                    smoothProgress = Math.min(1, Math.max(0, smoothProgress));
                    height = (int) (baseTerraceLevel * TERRACE_HEIGHT + smoothProgress * TERRACE_HEIGHT);
                }

                height = Math.max(0, Math.min(maxHeight, height));
                heightMap[x + radius][z + radius] = height;
            }
        }

        // Дополнительное сглаживание для устранения резких переходов
        heightMap = applyHeightMapSmoothing(heightMap, radius, 1);

        // Генерируем холм на основе карты высот
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int height = heightMap[x + radius][z + radius];

                if (height == 0) continue;

                BlockPos pos = origin.add(x, 0, z);

                // Заполняем колонку блоками до нужной высоты
                for (int y = 0; y < height; y++) {
                    BlockPos terrainPos = pos.up(y);
                    if (world.getBlockState(terrainPos.down()).isOpaque() || y == 0) {
                        world.setBlockState(terrainPos, EndlessStoreBlocks.PROBABLY_MUD.getDefaultState(), 3);
                    }
                }

                // Добавляем поверхностный блок
                if (random.nextInt(10) >= 6 && height > 0) {
                    BlockPos surfacePos = pos.up(height);
                    BlockState currentState = world.getBlockState(surfacePos);

                    if (currentState.isOf(EndlessStoreBlocks.PROBABLY_MUD)) {
                        Block selectedBlock = SURFACE_BLOCKS.get(random.nextInt(SURFACE_BLOCKS.size()));
                        world.setBlockState(surfacePos, selectedBlock.getDefaultState(), 3);
                    }
                }
            }
        }

        return true;
    }

    // Метод для сглаживания карты высот
    private int[][] applyHeightMapSmoothing(int[][] heightMap, int radius, int iterations) {
        int size = radius * 2 + 1;
        int[][] smoothed = new int[size][size];

        // Копируем исходную карту
        for (int i = 0; i < size; i++) {
            System.arraycopy(heightMap[i], 0, smoothed[i], 0, size);
        }

        // Применяем сглаживание в несколько итераций
        for (int iter = 0; iter < iterations; iter++) {
            int[][] temp = new int[size][size];

            for (int x = 1; x < size - 1; x++) {
                for (int z = 1; z < size - 1; z++) {
                    // Вычисляем среднее значение с соседями
                    int sum = smoothed[x][z] * 4; // Текущая точка имеет больший вес
                    sum += smoothed[x-1][z] + smoothed[x+1][z] + smoothed[x][z-1] + smoothed[x][z+1];
                    sum += smoothed[x-1][z-1] + smoothed[x-1][z+1] + smoothed[x+1][z-1] + smoothed[x+1][z+1];

                    temp[x][z] = sum / 12; // Среднее значение
                }
            }

            // Обновляем границы
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