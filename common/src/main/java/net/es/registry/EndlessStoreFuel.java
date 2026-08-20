package net.es.registry;

import net.es.item.EndlessStoreItems;

import java.util.LinkedHashMap;
import java.util.Map;

public class EndlessStoreFuel {
    public static final Map<String, Integer> FUEL_VALUES = new LinkedHashMap<>();

    static {
        FUEL_VALUES.put(EndlessStoreItems.WOODEN_BOARD, 200);
        FUEL_VALUES.put(EndlessStoreItems.PIECE_OF_CARDBOARD, 55);
        FUEL_VALUES.put(EndlessStoreItems.PIECE_OF_WALLPAPER, 55);
        FUEL_VALUES.put(EndlessStoreItems.PIECE_OF_LINOLEUM, 75);
    }

    public static void init() {}
}