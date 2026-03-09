package net.es.endless_store_mod.world.dimension;

import net.es.endless_store_mod.EndlessStoreMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class EndlessStoreDimension {
    public static final ResourceKey<LevelStem> ESDIM_KEY =
            ResourceKey.create(Registries.LEVEL_STEM, new ResourceLocation(EndlessStoreMod.MOD_ID, "esdim"));

    public static final ResourceKey<Level> ESDIM_LEVEL_KEY =
            ResourceKey.create(Registries.DIMENSION, new ResourceLocation(EndlessStoreMod.MOD_ID, "esdim"));

    public static final ResourceKey<DimensionType> ESDIM_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(EndlessStoreMod.MOD_ID, "esdim_type"));
}
