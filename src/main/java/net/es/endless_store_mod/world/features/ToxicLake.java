package net.es.endless_store_mod.world.features;

import net.es.endless_store_mod.fluid.EndlessStoreFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.HashSet;
import java.util.Set;

public class ToxicLake extends Feature<NoneFeatureConfiguration> {
    private static final int MIN_DEPTH = 5;
    private static final int MAX_DEPTH = 8;
    private static final int MAX_WIDTH = 7;
    private static final float WIDTH_VARIATION = 1.5f;
    private static final float SLOPE_SMOOTHNESS = 0.7f;

    public ToxicLake() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Проверяем, что под точкой спавна есть твёрдый блок
        if (!level.getBlockState(origin.below()).isSolid()) return false;

        Set<BlockPos> airBlocks = new HashSet<>();
        int maxDepth = 0;

        for (int x = -MAX_WIDTH; x <= MAX_WIDTH; x++) {
            for (int z = -MAX_WIDTH; z <= MAX_WIDTH; z++) {
                double distance = Math.sqrt(x * x + z * z);
                if (distance > MAX_WIDTH) continue;

                double noise = random.nextDouble() * WIDTH_VARIATION;
                double effectiveRadius = MAX_WIDTH - noise;

                if (distance <= effectiveRadius) {
                    int baseDepth = MIN_DEPTH + random.nextInt(MAX_DEPTH - MIN_DEPTH + 1);
                    double depthFactor = Math.pow(1 - (distance / effectiveRadius), SLOPE_SMOOTHNESS);
                    int currentDepth = (int) (baseDepth * depthFactor);
                    maxDepth = Math.max(maxDepth, currentDepth);

                    for (int y = 0; y >= -currentDepth; y--) {
                        BlockPos pos = origin.offset(x, y, z);
                        if (level.getBlockState(pos).isSolid()) {
                            level.setBlock(pos, Blocks.CAVE_AIR.defaultBlockState(), 3);
                            airBlocks.add(pos);
                        }
                    }
                }
            }
        }

        int waterLevel = origin.getY() - maxDepth + 4; // Уровень воды на 1 блок выше дна
        for (BlockPos airPos : airBlocks) {
            if (airPos.getY() <= waterLevel) {
                level.setBlock(airPos, EndlessStoreFluids.ACID_BLOCK.get().defaultBlockState(), 3);
            }
        }

        return true;
    }
}