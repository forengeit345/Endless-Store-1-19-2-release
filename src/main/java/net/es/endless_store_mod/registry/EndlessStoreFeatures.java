package net.es.endless_store_mod.registry;


import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.world.features.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EndlessStoreFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, EndlessStoreMod.MOD_ID);

    public static final RegistryObject<SinWaveHillFeature> SIN_WAVE_HILL =
            FEATURES.register("sin_wave_hill", SinWaveHillFeature::new);

    public static final RegistryObject<HillsOfBoxes> HILLS_OF_BOXES =
            FEATURES.register("hills_of_boxes", HillsOfBoxes::new);

    public static final RegistryObject<TrashLandfill> TRASH_LANDFILL =
            FEATURES.register("trash_landfill", TrashLandfill::new);

    public static final RegistryObject<AbandonedHills> ABANDONED_HILLS =
            FEATURES.register("abandoned_hills", AbandonedHills::new);

    public static final RegistryObject<ToxicLake> TOXIC_LAKE =
            FEATURES.register("toxic_lake", ToxicLake::new);

    public static final RegistryObject<ChainMailTrees> CHAIN_MAIL_TREES =
            FEATURES.register("chain_mail_trees", ChainMailTrees::new);
}