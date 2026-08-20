package net.es.world.features;

import net.es.EndlessStoreMod;
import net.es.block.EndlessStoreBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class AbandonedHills extends Feature<NoneFeatureConfiguration> {

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
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        Block probablyMud = getBlock(EndlessStoreBlocks.PROBABLY_MUD);
        Block concreteMud = getBlock(EndlessStoreBlocks.CONCRETE_MUD);
        Block greenMud = getBlock(EndlessStoreBlocks.GREEN_MUD);
        List<Block> surfaceBlocks = List.of(concreteMud, greenMud);

        int radius = BASE_RADIUS + random.nextInt(RADIUS_VARIATION);
        int terraceCount = MIN_TERRACES + random.nextInt(MAX_TERRACES - MIN_TERRACES + 1);
        int maxHeight = terraceCount * TERRACE_HEIGHT;

        int size = radius * 2 + 1;
        int[][] heightMap = new int[size][size];

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double distance = Math.sqrt(x * x + z * z) / radius;
                if (distance > 1.0) {
                    heightMap[x + radius][z + radius] = 0;
                    continue;
                }

                int height = getHeight(distance, terraceCount, maxHeight);
                heightMap[x + radius][z + radius] = height;
            }
        }

        heightMap = applyHeightMapSmoothing(heightMap, radius, 1);

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int height = heightMap[x + radius][z + radius];
                if (height == 0) continue;

                BlockPos pos = origin.offset(x, 0, z);

                for (int y = 0; y < height; y++) {
                    BlockPos terrainPos = pos.above(y);
                    if (world.getBlockState(terrainPos.below()).isSolid() || y == 0) {
                        world.setBlock(terrainPos, probablyMud.defaultBlockState(), 3);
                    }
                }

                if (random.nextInt(10) >= 6 && height > 0) {
                    BlockPos surfacePos = pos.above(height);
                    BlockState currentState = world.getBlockState(surfacePos);
                    if (currentState.is(probablyMud)) {
                        Block selectedBlock = surfaceBlocks.get(random.nextInt(surfaceBlocks.size()));
                        world.setBlock(surfacePos, selectedBlock.defaultBlockState(), 3);
                    }
                }
            }
        }

        return true;
    }

    private static int getHeight(double distance, int terraceCount, int maxHeight) {
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
        return height;
    }

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
                    sum += smoothed[x - 1][z] + smoothed[x + 1][z] + smoothed[x][z - 1] + smoothed[x][z + 1];
                    sum += smoothed[x - 1][z - 1] + smoothed[x - 1][z + 1] + smoothed[x + 1][z - 1] + smoothed[x + 1][z + 1];
                    temp[x][z] = sum / 12;
                }
            }

            for (int i = 0; i < size; i++) {
                temp[i][0] = smoothed[i][0];
                temp[i][size - 1] = smoothed[i][size - 1];
                temp[0][i] = smoothed[0][i];
                temp[size - 1][i] = smoothed[size - 1][i];
            }

            smoothed = temp;
        }

        return smoothed;
    }

    private Block getBlock(String name) {
        return BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name));
    }
}