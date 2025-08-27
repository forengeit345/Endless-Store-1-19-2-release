package net.es.endless_store_mod.datagen;

import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class EndlessStoreLootTableProvider extends FabricBlockLootTableProvider {
    public EndlessStoreLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(EndlessStoreBlocks.ORANGE_GRANIT);
        addDrop(EndlessStoreBlocks.WHITE_BRICK);
        addDrop(EndlessStoreBlocks.WHITE_BRICK_ALTERNATIVE);
        addDrop(EndlessStoreBlocks.WHITE_BRICK_SLAB);
        addDrop(EndlessStoreBlocks.WHITE_BRICK_STAIRS_ES);
        addDrop(EndlessStoreBlocks.WHITE_BRICK_SLAB_ALTERNATIVE);
        addDrop(EndlessStoreBlocks.WHITE_BRICK_STAIRS_ALTERNATIVE);
        addDrop(EndlessStoreBlocks.PATTERNED_CARPET);
        addDrop(EndlessStoreBlocks.PLUSH_MOUSE_GRAF);
        addDrop(EndlessStoreBlocks.OUTDOOR_SIGN);
        addDrop(EndlessStoreBlocks.KETTLE);
        addDrop(EndlessStoreBlocks.WALL_CLOCK);
        addDrop(EndlessStoreBlocks.TRASH_CAN);
        addDrop(EndlessStoreBlocks.PLATE);
        addDrop(EndlessStoreBlocks.TOOLBOX);
        addDrop(EndlessStoreBlocks.TOWEL_HOLDER);
        addDrop(EndlessStoreBlocks.CALENDAR);
        addDrop(EndlessStoreBlocks.GLOBE);
        addDrop(EndlessStoreBlocks.LAMP);
        addDrop(EndlessStoreBlocks.MRE);
        addDrop(EndlessStoreBlocks.BOTTLE);
        addDrop(EndlessStoreBlocks.COMPUTER);
        addDrop(EndlessStoreBlocks.ORGANIZER);
        addDrop(EndlessStoreBlocks.CONCRETE_FURNACE);
        addDrop(EndlessStoreBlocks.EMPTY_ORGANIZER);
    }
}
