package net.es.endless_store_mod.fluid;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.item.CustomBucketItem;
import net.es.endless_store_mod.item.EndlessStoreItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EndlessStoreFluids {
    public static FlowableFluid ACID;
    public static FlowableFluid FLOWING_ACID;
    public static Block ACID_BLOCK;
    public static Item PLASTIC_ACID_BUCKET;

    public static FlowableFluid WASTE_WATER;
    public static FlowableFluid FLOWING_WASTE_WATER;
    public static Block WASTE_WATER_BLOCK;
    public static Item PLASTIC_WASTE_WATER_BUCKET;

    public static void register() {
        ACID = Registry.register(Registry.FLUID, new Identifier(EndlessStoreMod.MOD_ID, "acid"), new AcidFluid.Still());
        FLOWING_ACID = Registry.register(Registry.FLUID, new Identifier(EndlessStoreMod.MOD_ID, "flowing_acid"), new AcidFluid.Flowing());
        ACID_BLOCK = Registry.register(Registry.BLOCK, new Identifier(EndlessStoreMod.MOD_ID, "acid_block"), new CustomFluidBlock(EndlessStoreFluids.ACID, FabricBlockSettings.copyOf(Blocks.WATER)));
        PLASTIC_ACID_BUCKET = Registry.register(Registry.ITEM, new Identifier(EndlessStoreMod.MOD_ID, "plastic_acid_bucket"), new CustomBucketItem(EndlessStoreFluids.ACID, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).recipeRemainder(Items.BUCKET).maxCount(1)));

        WASTE_WATER = Registry.register(Registry.FLUID, new Identifier(EndlessStoreMod.MOD_ID, "waste_water"), new WasteWaterFluid.Still());
        FLOWING_WASTE_WATER = Registry.register(Registry.FLUID, new Identifier(EndlessStoreMod.MOD_ID, "flowing_waste_water"), new WasteWaterFluid.Flowing());
        WASTE_WATER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(EndlessStoreMod.MOD_ID, "waste_water_block"), new FluidBlock(EndlessStoreFluids.WASTE_WATER, FabricBlockSettings.copyOf(Blocks.WATER)));
        PLASTIC_WASTE_WATER_BUCKET = Registry.register(Registry.ITEM, new Identifier(EndlessStoreMod.MOD_ID, "plastic_waste_water_bucket"), new CustomBucketItem(EndlessStoreFluids.WASTE_WATER, new FabricItemSettings().group(EndlessStoreItemGroup.ENDLESS_MOD).recipeRemainder(Items.BUCKET).maxCount(19)));
    }
}