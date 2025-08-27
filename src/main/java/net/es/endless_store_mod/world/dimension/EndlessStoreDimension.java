package net.es.endless_store_mod.world.dimension;

import net.es.endless_store_mod.EndlessStoreMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;


public class EndlessStoreDimension {
    public static final RegistryKey<DimensionOptions> ESDIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(EndlessStoreMod.MOD_ID, "esdim"));
    public static final RegistryKey<World> ESDIM_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(EndlessStoreMod.MOD_ID, "esdim"));
    public static final RegistryKey<DimensionType> ESDIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(EndlessStoreMod.MOD_ID, "esdim_type"));
}
