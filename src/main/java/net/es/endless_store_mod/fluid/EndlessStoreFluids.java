package net.es.endless_store_mod.fluid;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.item.CustomBucketItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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
        ACID = Registry.register(Registries.FLUID, new Identifier(EndlessStoreMod.MOD_ID, "acid"), new AcidFluid.Still());
        FLOWING_ACID = Registry.register(Registries.FLUID, new Identifier(EndlessStoreMod.MOD_ID, "flowing_acid"), new AcidFluid.Flowing());
        ACID_BLOCK = Registry.register(Registries.BLOCK, new Identifier(EndlessStoreMod.MOD_ID, "acid_block"), new CustomFluidBlock(EndlessStoreFluids.ACID, FabricBlockSettings.copyOf(Blocks.WATER).replaceable()));
        PLASTIC_ACID_BUCKET = Registry.register(Registries.ITEM, new Identifier(EndlessStoreMod.MOD_ID, "plastic_acid_bucket"), new CustomBucketItem(EndlessStoreFluids.ACID, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        WASTE_WATER = Registry.register(Registries.FLUID, new Identifier(EndlessStoreMod.MOD_ID, "waste_water"), new WasteWaterFluid.Still());
        FLOWING_WASTE_WATER = Registry.register(Registries.FLUID, new Identifier(EndlessStoreMod.MOD_ID, "flowing_waste_water"), new WasteWaterFluid.Flowing());
        WASTE_WATER_BLOCK = Registry.register(Registries.BLOCK, new Identifier(EndlessStoreMod.MOD_ID, "waste_water_block"), new FluidBlock(EndlessStoreFluids.WASTE_WATER, FabricBlockSettings.copyOf(Blocks.WATER).replaceable()));
        PLASTIC_WASTE_WATER_BUCKET = Registry.register(Registries.ITEM, new Identifier(EndlessStoreMod.MOD_ID, "plastic_waste_water_bucket"), new CustomBucketItem(EndlessStoreFluids.WASTE_WATER, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));
    }
}