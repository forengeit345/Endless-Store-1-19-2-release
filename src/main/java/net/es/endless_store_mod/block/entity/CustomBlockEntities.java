package net.es.endless_store_mod.block.entity;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomBlockEntities {
    public static void init() {}

    public static final BlockEntityType<OldSafeEntity> OLD_SAFE = register("old_safe", FabricBlockEntityTypeBuilder.create(OldSafeEntity::new, EndlessStoreBlocks.OLD_SAFE).build(null));
    public static final BlockEntityType<BedsideTableEntity> BEDSIDE_TABLE = register("bedside_table", FabricBlockEntityTypeBuilder.create(BedsideTableEntity::new, EndlessStoreBlocks.BEDSIDE_TABLE).build(null));
    public static final BlockEntityType<CustomFurnaceBlockEntity> CONCRETE_FURNACE = register("concrete_safe", FabricBlockEntityTypeBuilder.create(CustomFurnaceBlockEntity::new, EndlessStoreBlocks.CONCRETE_FURNACE).build(null));


    private static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType<T> type) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(EndlessStoreMod.MOD_ID, id), type);
    }
}
