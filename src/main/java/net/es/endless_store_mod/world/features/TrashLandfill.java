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

public class TrashLandfill extends Feature<NoneFeatureConfiguration> {
    private static final List<Block> SURFACE_BLOCKS = List.of(
            EndlessStoreBlocks.PLASTIC_GARBAGE_BLUE.get(),
            EndlessStoreBlocks.PLASTIC_GARBAGE_COLORED.get()
    );

    public TrashLandfill() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        if (level.isClientSide()) return false;

        int radius = 10 + random.nextInt(6);
        int maxHeight = 4 + random.nextInt(4);

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos pos = origin.offset(x, 0, z);
                double distance = Math.sqrt(x * x + z * z) / radius;

                if (distance > 1.0) continue;

                double heightFactor = 1 - distance * distance;
                int height = (int) (heightFactor * maxHeight);
                height += random.nextInt(2) * 2 - 1;

                for (int y = 0; y < height; y++) {
                    BlockPos terrainPos = pos.above(y);
                    if (level.getBlockState(terrainPos.below()).isSolid()) {
                        level.setBlock(terrainPos, EndlessStoreBlocks.PLASTIC_GARBAGE_GREY.get().defaultBlockState(), 3);
                    }
                }

                if (random.nextInt(10) >= 2) {
                    BlockPos surfacePos = pos.above(height);
                    BlockState currentState = level.getBlockState(surfacePos);

                    if (currentState.is(EndlessStoreBlocks.PLASTIC_GARBAGE_GREY.get())) {
                        Block selectedBlock = SURFACE_BLOCKS.get(random.nextInt(SURFACE_BLOCKS.size()));
                        level.setBlock(surfacePos, selectedBlock.defaultBlockState(), 3);
                    }
                }
            }
        }

        return true;
    }
}