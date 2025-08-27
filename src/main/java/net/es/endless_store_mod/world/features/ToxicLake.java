package net.es.endless_store_mod.world.features;

import net.es.endless_store_mod.fluid.EndlessStoreFluids;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.HashSet;
import java.util.Set;


public class ToxicLake extends Feature<DefaultFeatureConfig> {
    private static final int MIN_DEPTH = 5;
    private static final int MAX_DEPTH = 8;
    private static final int MAX_WIDTH = 7;
    private static final float WIDTH_VARIATION = 1.5f;
    private static final float SLOPE_SMOOTHNESS = 0.7f;

    public ToxicLake() {
        super(DefaultFeatureConfig.CODEC);
    }


    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        if (!world.getBlockState(origin.down()).isOpaque()) return false;

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
                        BlockPos pos = origin.add(x, y, z);
                        if (world.getBlockState(pos).isOpaque()) {
                            world.setBlockState(pos, Blocks.CAVE_AIR.getDefaultState(), 3);
                            airBlocks.add(pos);
                        }
                    }
                }
            }
        }

        int waterLevel = origin.getY() - maxDepth + 4; // Уровень воды на 1 блок выше дна
        for (BlockPos airPos : airBlocks) {
            if (airPos.getY() <= waterLevel) {
                world.setBlockState(airPos, EndlessStoreFluids.ACID_BLOCK.getDefaultState(), 3);
            }
        }

        return true;
    }
}