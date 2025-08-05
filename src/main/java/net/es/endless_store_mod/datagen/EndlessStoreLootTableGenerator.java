package net.es.endless_store_mod.datagen;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class EndlessStoreLootTableGenerator extends SimpleFabricLootTableProvider {
    public EndlessStoreLootTableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/orange_granit"), BlockLootTableGenerator.drops(EndlessStoreBlocks.ORANGE_GRANIT));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/white_brick"), BlockLootTableGenerator.drops(EndlessStoreBlocks.WHITE_BRICK));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/white_brick_alternative"), BlockLootTableGenerator.drops(EndlessStoreBlocks.WHITE_BRICK_ALTERNATIVE));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/white_brick_slab"), BlockLootTableGenerator.drops(EndlessStoreBlocks.WHITE_BRICK_SLAB));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/white_brick_stairs_es"), BlockLootTableGenerator.drops(EndlessStoreBlocks.WHITE_BRICK_STAIRS_ES));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/white_brick_slab_alternative"), BlockLootTableGenerator.drops(EndlessStoreBlocks.WHITE_BRICK_SLAB_ALTERNATIVE));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/white_brick_stairs_alternative"), BlockLootTableGenerator.drops(EndlessStoreBlocks.WHITE_BRICK_STAIRS_ALTERNATIVE));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/patterned_carpet"), BlockLootTableGenerator.drops(EndlessStoreBlocks.PATTERNED_CARPET));

        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/plush_mouse_graf"), BlockLootTableGenerator.drops(EndlessStoreBlocks.PLUSH_MOUSE_GRAF));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/outdoor_sign"), BlockLootTableGenerator.drops(EndlessStoreBlocks.OUTDOOR_SIGN));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/kettle"), BlockLootTableGenerator.drops(EndlessStoreBlocks.KETTLE));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/wall_clock"), BlockLootTableGenerator.drops(EndlessStoreBlocks.WALL_CLOCK));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/trash_can"), BlockLootTableGenerator.drops(EndlessStoreBlocks.TRASH_CAN));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/plate"), BlockLootTableGenerator.drops(EndlessStoreBlocks.PLATE));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/toolbox"), BlockLootTableGenerator.drops(EndlessStoreBlocks.TOOLBOX));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/towel_holder"), BlockLootTableGenerator.drops(EndlessStoreBlocks.TOWEL_HOLDER));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/calendar"), BlockLootTableGenerator.drops(EndlessStoreBlocks.CALENDAR));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/globe"), BlockLootTableGenerator.drops(EndlessStoreBlocks.GLOBE));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/lamp"), BlockLootTableGenerator.drops(EndlessStoreBlocks.LAMP));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/concrete_furnace"), BlockLootTableGenerator.drops(EndlessStoreBlocks.CONCRETE_FURNACE));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/mre"), BlockLootTableGenerator.drops(EndlessStoreBlocks.MRE));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/bottle"), BlockLootTableGenerator.drops(EndlessStoreBlocks.BOTTLE));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/computer"), BlockLootTableGenerator.drops(EndlessStoreBlocks.COMPUTER));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/organizer"), BlockLootTableGenerator.drops(EndlessStoreBlocks.ORGANIZER));
        identifierBuilderBiConsumer.accept(new Identifier(EndlessStoreMod.MOD_ID, "blocks/empty_organizer"), BlockLootTableGenerator.drops(EndlessStoreBlocks.EMPTY_ORGANIZER));
    }
}
