package net.es.world.features;

import net.es.EndlessStoreMod;
import net.es.block.EndlessStoreBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import java.util.List;

public class SinWaveHillFeature extends Feature<NoneFeatureConfiguration> {

    private static final float SECONDARY_BLOCK_CHANCE = 0.2F;
    private static final int CHECK_DEPTH = 5;
    private static final int OCTAVES = 4;
    private static final double BASE_FREQUENCY = 0.055;
    private static final double PERSISTENCE = 0.5;

    public SinWaveHillFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        Block hugeBox = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX_HUGE));
        Block concrete = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CONCRETE));
        List<Block> baseBlocks = List.of(hugeBox, concrete);

        Block normalBox = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX));
        Block openBox = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX_OPEN));
        Block middleBox = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX_MIDDLE));
        Block middleOpen = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX_MIDDLE_OPEN));
        Block largeBox = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX_LARGE));
        Block largeOpen = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX_LARGE_OPEN));
        Block hugeOpen = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX_HUGE_OPEN));
        List<Block> surfaceBlocks = List.of(normalBox, openBox, middleBox, middleOpen, largeBox, largeOpen, hugeOpen);

        int radius = 13;
        double maxHeight = 13.0;
        SimplexNoise noise = new SimplexNoise(random);

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double distance = Math.sqrt(x * x + z * z) / radius;
                if (distance > 1.0) continue;

                BlockPos pos = origin.offset(x, 0, z);
                int surfaceY = world.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());

                double noiseValue = multiOctaveNoise(noise, pos.getX() * BASE_FREQUENCY, pos.getZ() * BASE_FREQUENCY);
                double pillarChance = noiseValue * (1.0 - distance * distance);
                int height = 0;
                if (random.nextDouble() < pillarChance) {
                    double t = random.nextDouble();
                    double heightFactor = 0.2 + 0.8 * t * t; // регулируйте степень для контраста
                    height = (int) (maxHeight * heightFactor);
                }

                int lastY = -1;
                for (int y = 0; y < height; y++) {
                    BlockPos blockPos = pos.atY(surfaceY + y);
                    if (y == 0 && !hasValidBase(world, blockPos, baseBlocks)) {
                        break;
                    }
                    if (world.getBlockState(blockPos).isAir()) {
                        world.setBlock(blockPos, hugeBox.defaultBlockState(), 3);
                        lastY = surfaceY + y;
                    }
                }

                if (lastY != -1 && random.nextFloat() < SECONDARY_BLOCK_CHANCE) {
                    BlockPos topPos = pos.atY(lastY + 1);
                    if (world.getBlockState(topPos).isAir()) {
                        Block surfaceBlock = surfaceBlocks.get(random.nextInt(surfaceBlocks.size()));
                        world.setBlock(topPos, surfaceBlock.defaultBlockState(), 3);
                    }
                }
            }
        }
        return true;
    }

    private double multiOctaveNoise(SimplexNoise noise, double x, double z) {
        double value = 0;
        double amplitude = 1.0;
        double frequency = 1.0;
        double maxValue = 0;
        for (int i = 0; i < OCTAVES; i++) {
            value += noise.getValue(x * frequency, z * frequency) * amplitude;
            maxValue += amplitude;
            amplitude *= PERSISTENCE;
            frequency *= 2.0;
        }
        return (value / maxValue) * 0.5 + 0.5;
    }

    private boolean hasValidBase(WorldGenLevel world, BlockPos pos, List<Block> baseBlocks) {
        for (int dy = 0; dy < CHECK_DEPTH; dy++) {
            BlockPos checkPos = pos.below(dy + 1);
            Block block = world.getBlockState(checkPos).getBlock();
            if (baseBlocks.contains(block)) {
                return true;
            }
            if (!world.getBlockState(checkPos).isAir()) {
                return false;
            }
        }
        return false;
    }
}