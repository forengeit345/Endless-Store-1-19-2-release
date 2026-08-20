package net.es.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

public class EndlessStoreFeatures {
    public static Supplier<Feature<NoneFeatureConfiguration>> HILLS_OF_BOXES = () -> {
        throw new IllegalStateException("Feature hills_of_boxes not registered");
    };

    public static Supplier<Feature<NoneFeatureConfiguration>> SIN_WAVE_HILL_FEATURE = () -> {
        throw new IllegalStateException("Feature sin_wave_hill not registered");
    };

    public static Supplier<Feature<NoneFeatureConfiguration>> TOXIC_LAKE = () -> {
        throw new IllegalStateException("Feature toxic_lake not registered");
    };

    public static Supplier<Feature<NoneFeatureConfiguration>> TRASH_LANDFILL = () -> {
        throw new IllegalStateException("Feature trash_landfill not registered");
    };

    public static Supplier<Feature<NoneFeatureConfiguration>> CHAIN_MAIL_TREES = () -> {
        throw new IllegalStateException("Feature chain_mail_trees not registered");
    };

    public static Supplier<Feature<NoneFeatureConfiguration>> ABANDONED_HILLS = () -> {
        throw new IllegalStateException("Feature abandoned_hills not registered");
    };
}