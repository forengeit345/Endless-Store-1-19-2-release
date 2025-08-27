package net.es.endless_store_mod.registry;

import net.es.endless_store_mod.world.features.HillsOfBoxes;
import net.es.endless_store_mod.world.features.SinWaveHillFeature;
import net.es.endless_store_mod.world.features.ToxicLake;
import net.es.endless_store_mod.world.features.TrashLandfill;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class EndlessStoreFeatures {
    public static final SinWaveHillFeature SIN_WAVE_HILL_FEATURE = new SinWaveHillFeature();
    public static final Identifier SIN_WAVE_HILL_ID = new Identifier("endless_store_mod", "sin_wave_hill");

    public static final HillsOfBoxes HILLS_OF_BOXES  = new HillsOfBoxes();
    public static final Identifier HILLS_OF_BOXES_ID = new Identifier("endless_store_mod", "hills_of_boxes");

    public static final TrashLandfill TRASH_LANDFILL  = new TrashLandfill();
    public static final Identifier TRASH_LANDFILL_ID = new Identifier("endless_store_mod", "trash_landfill");

    public static final ToxicLake TOXIC_LAKE  = new ToxicLake();
    public static final Identifier TOXIC_LAKE_ID = new Identifier("endless_store_mod", "toxic_lake");


    public static void registerFeatures() {
        Registry.register(Registries.FEATURE, SIN_WAVE_HILL_ID, SIN_WAVE_HILL_FEATURE);
        Registry.register(Registries.FEATURE, HILLS_OF_BOXES_ID, HILLS_OF_BOXES);
        Registry.register(Registries.FEATURE, TRASH_LANDFILL_ID, TRASH_LANDFILL);
        Registry.register(Registries.FEATURE, TOXIC_LAKE_ID, TOXIC_LAKE);
    }
}