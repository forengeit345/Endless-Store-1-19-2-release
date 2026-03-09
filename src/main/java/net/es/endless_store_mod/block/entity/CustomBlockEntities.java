package net.es.endless_store_mod.block.entity;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CustomBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EndlessStoreMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<CustomFurnaceBlockEntity>> CONCRETE_FURNACE =
            BLOCK_ENTITIES.register("concrete_furnace", () ->
                    BlockEntityType.Builder.of(CustomFurnaceBlockEntity::new,
                            EndlessStoreBlocks.CONCRETE_FURNACE.get(), EndlessStoreBlocks.CONCRETE_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BedsideTableEntity>> BEDSIDE_TABLE =
            BLOCK_ENTITIES.register("bedside_table", () ->
                    BlockEntityType.Builder.of(BedsideTableEntity::new,
                            EndlessStoreBlocks.BEDSIDE_TABLE.get(), EndlessStoreBlocks.BEDSIDE_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<OldSafeEntity>> OLD_SAFE =
            BLOCK_ENTITIES.register("old_safe", () ->
                    BlockEntityType.Builder.of(OldSafeEntity::new,
                            EndlessStoreBlocks.BEDSIDE_TABLE.get(), EndlessStoreBlocks.OLD_SAFE.get()).build(null));

    public static final RegistryObject<BlockEntityType<CardboardBoxHugeTapeEntity>> CARDBOARD_BOX_HUGE_TAPE =
            BLOCK_ENTITIES.register("cardboard_box_huge_tape", () ->
                    BlockEntityType.Builder.of(CardboardBoxHugeTapeEntity::new,
                            EndlessStoreBlocks.BEDSIDE_TABLE.get(), EndlessStoreBlocks.CARDBOARD_BOX_HUGE_TAPE.get()).build(null));

    public static final RegistryObject<BlockEntityType<PlateEntity>> PLATE_ENTITY =
            BLOCK_ENTITIES.register("plate", () ->
                    BlockEntityType.Builder.of(PlateEntity::new,
                            EndlessStoreBlocks.BEDSIDE_TABLE.get(), EndlessStoreBlocks.PLATE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

//    public static final BlockEntityType<OldSafeEntity> OLD_SAFE = register("old_safe", FabricBlockEntityTypeBuilder.create(OldSafeEntity::new, EndlessStoreBlocks.OLD_SAFE).build(null));
//    public static final BlockEntityType<BedsideTableEntity> BEDSIDE_TABLE = register("bedside_table", FabricBlockEntityTypeBuilder.create(BedsideTableEntity::new, EndlessStoreBlocks.BEDSIDE_TABLE).build(null));
//    public static final BlockEntityType<CardboardBoxHugeTapeEntity> CARDBOARD_BOX_HUGE_TAPE = register("cardboard_box_huge_tape", FabricBlockEntityTypeBuilder.create(CardboardBoxHugeTapeEntity::new, EndlessStoreBlocks.CARDBOARD_BOX_HUGE_TAPE).build(null));
//    public static final BlockEntityType<CustomFurnaceBlockEntity> CONCRETE_FURNACE = register("concrete_safe", FabricBlockEntityTypeBuilder.create(CustomFurnaceBlockEntity::new, EndlessStoreBlocks.CONCRETE_FURNACE).build(null));
//    public static final BlockEntityType<PlateEntity> PLATE_ENTITY = register("plate_entity", FabricBlockEntityTypeBuilder.create(PlateEntity::new, EndlessStoreBlocks.PLATE).build(null));

}