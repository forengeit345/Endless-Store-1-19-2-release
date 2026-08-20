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

public class TrashLandfill extends Feature<NoneFeatureConfiguration> {

    public TrashLandfill() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Получаем блоки напрямую из реестра (во время генерации они уже точно есть)
        Block garbageGrey = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.PLASTIC_GARBAGE_GREY));
        Block garbageBlue = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.PLASTIC_GARBAGE_BLUE));
        Block garbageColored = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.PLASTIC_GARBAGE_COLORED));
        List<Block> surfaceBlocks = List.of(garbageBlue, garbageColored);

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
                    if (world.getBlockState(terrainPos.below()).isSolid()) {
                        world.setBlock(terrainPos, garbageGrey.defaultBlockState(), 3);
                    }
                }

                if (random.nextInt(10) >= 2) {
                    BlockPos surfacePos = pos.above(height);
                    BlockState currentState = world.getBlockState(surfacePos);
                    if (currentState.is(garbageGrey)) {
                        Block selectedBlock = surfaceBlocks.get(random.nextInt(surfaceBlocks.size()));
                        world.setBlock(surfacePos, selectedBlock.defaultBlockState(), 3);
                    }
                }
            }
        }
        return true;
    }
}