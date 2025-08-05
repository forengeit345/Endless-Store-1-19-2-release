package net.es.endless_store_mod.registry;

import net.es.endless_store_mod.item.EndlessStoreItems;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class EndlessStoreFuel {
    public static void init() {
        FuelRegistry.INSTANCE.add(EndlessStoreItems.WOODEN_BOARD, 200);
        FuelRegistry.INSTANCE.add(EndlessStoreItems.PIECE_OF_CARDBOARD, 55);
        FuelRegistry.INSTANCE.add(EndlessStoreItems.PIECE_OF_WALLPAPER, 55);
        FuelRegistry.INSTANCE.add(EndlessStoreItems.PIECE_OF_LINOLEUM, 75);
    }
}
