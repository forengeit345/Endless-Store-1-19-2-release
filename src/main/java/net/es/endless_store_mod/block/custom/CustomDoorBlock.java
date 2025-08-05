package net.es.endless_store_mod.block.custom;

import net.minecraft.block.DoorBlock;

public class CustomDoorBlock extends DoorBlock {
    public CustomDoorBlock(Settings settings) {
        super(settings.nonOpaque());
    }
}
