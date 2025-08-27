package net.es.endless_store_mod;

import net.es.endless_store_mod.datagen.EndlessStoreLootTableProvider;
import net.es.endless_store_mod.datagen.EndlessStoreModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EndlessStoreDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(EndlessStoreModelProvider::new);
        pack.addProvider(EndlessStoreLootTableProvider::new);
    }
}
