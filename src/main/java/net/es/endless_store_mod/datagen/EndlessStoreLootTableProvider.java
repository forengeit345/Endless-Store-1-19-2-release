package net.es.endless_store_mod.datagen;

import net.es.endless_store_mod.datagen.loot.EndlessStoreBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class EndlessStoreLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(EndlessStoreBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}