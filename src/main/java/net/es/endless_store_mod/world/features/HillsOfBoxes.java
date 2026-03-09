package net.es.endless_store_mod.world.features;

import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;

import java.util.List;

public class HillsOfBoxes extends Feature<NoneFeatureConfiguration> {
    private static final List<Block> BASE_BLOCKS = List.of(
            EndlessStoreBlocks.CARDBOARD_BOX_HUGE.get(),
            EndlessStoreBlocks.CONCRETE.get()
    );

    private static final float SECONDARY_BLOCK_CHANCE = 0.3F;
    private static final List<Block> SURFACE_BLOCKS = List.of(
            EndlessStoreBlocks.CARDBOARD_BOX.get(),
            EndlessStoreBlocks.CARDBOARD_BOX_OPEN.get(),
            EndlessStoreBlocks.CARDBOARD_BOX_MIDDLE.get(),
            EndlessStoreBlocks.CARDBOARD_BOX_MIDDLE_OPEN.get(),
            EndlessStoreBlocks.CARDBOARD_BOX_LARGE.get(),
            EndlessStoreBlocks.CARDBOARD_BOX_LARGE_OPEN.get(),
            EndlessStoreBlocks.CARDBOARD_BOX_HUGE_OPEN.get()
    );

    private static final int CHECK_DEPTH = 5;

    public HillsOfBoxes() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        PerlinNoise noiseSampler = PerlinNoise.create(random, List.of(2));

        int radius = 13;
        double maxHeight = 12.0;
        double baseWidth = 0.355;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double distance = Math.sqrt(x * x + z * z) / radius;
                if (distance > 1.0) continue;

                BlockPos pos = origin.offset(x, 0, z);
                int surfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ());
                double noise = noiseSampler.getValue(pos.getX() * baseWidth, pos.getZ() * baseWidth, 0.0) * (1.0 - distance * distance);

                int height = (int) (noise * maxHeight);
                int lastY = -1;

                for (int y = 0; y < height; y++) {
                    BlockPos blockPos = pos.atY(surfaceY + y);

                    if (y == 0 && !hasValidBase(level, blockPos)) {
                        break;
                    }

                    if (level.isEmptyBlock(blockPos)) {
                        level.setBlock(blockPos, EndlessStoreBlocks.CARDBOARD_BOX_HUGE.get().defaultBlockState(), 3);
                        lastY = surfaceY + y;
                    }
                }

                if (lastY != -1 && random.nextFloat() < SECONDARY_BLOCK_CHANCE) {
                    BlockPos topPos = pos.atY(lastY + 1);
                    if (level.isEmptyBlock(topPos)) {
                        Block surfaceBlock = getRandomSurfaceBlock(random);
                        level.setBlock(topPos, surfaceBlock.defaultBlockState(), 3);
                    }
                }
            }
        }
        return true;
    }

    private boolean hasValidBase(WorldGenLevel level, BlockPos pos) {
        for (int dy = 0; dy < CHECK_DEPTH; dy++) {
            BlockPos checkPos = pos.below(dy + 1);
            Block block = level.getBlockState(checkPos).getBlock();

            if (BASE_BLOCKS.contains(block)) {
                return true;
            }
            if (!level.isEmptyBlock(checkPos)) {
                return false;
            }
        }
        return false;
    }

    private Block getRandomSurfaceBlock(RandomSource random) {
        return SURFACE_BLOCKS.get(random.nextInt(SURFACE_BLOCKS.size()));
    }
}