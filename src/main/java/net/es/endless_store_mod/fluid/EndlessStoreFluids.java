package net.es.endless_store_mod.fluid;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.item.CustomBucketItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import java.util.function.Consumer;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;

public class EndlessStoreFluids {
    // Используем ResourceKey из ForgeRegistries. Keys для надёжной регистрации
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, EndlessStoreMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.Keys.FLUIDS, EndlessStoreMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.Keys.BLOCKS, EndlessStoreMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.Keys.ITEMS, EndlessStoreMod.MOD_ID);

    // Кастомный класс FluidType с текстурами и цветом
    public static class ModFluidType extends FluidType {
        private final ResourceLocation stillTexture;
        private final ResourceLocation flowingTexture;
        private final int tintColor;

        public ModFluidType(ResourceLocation stillTexture, ResourceLocation flowingTexture, int tintColor,
                            Properties properties) {
            super(properties);
            this.stillTexture = stillTexture;
            this.flowingTexture = flowingTexture;
            this.tintColor = tintColor;
        }

        @Override
        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
            consumer.accept(new IClientFluidTypeExtensions() {
                @Override
                public ResourceLocation getStillTexture() {
                    return stillTexture;
                }

                @Override
                public ResourceLocation getFlowingTexture() {
                    return flowingTexture;
                }

                @Override
                public int getTintColor() {
                    return tintColor;
                }

                @Override
                public @Nullable ResourceLocation getOverlayTexture() {
                    return null;
                }
            });
        }
    }

    public static final RegistryObject<FluidType> ACID_FLUID_TYPE = FLUID_TYPES.register("acid",
            () -> new ModFluidType(
                    new ResourceLocation("minecraft:block/water_still"),
                    new ResourceLocation("minecraft:block/water_flow"),
                    0xA1E0FFFF,
                    FluidType.Properties.create()
                            .descriptionId("block.endless_store_mod.acid")
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            ));

    public static final RegistryObject<FlowingFluid> ACID = FLUIDS.register("acid",
            () -> new AcidFluid.Still(EndlessStoreFluids.ACID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_ACID = FLUIDS.register("flowing_acid",
            () -> new AcidFluid.Flowing(EndlessStoreFluids.ACID_PROPERTIES));

    public static final RegistryObject<LiquidBlock> ACID_BLOCK = BLOCKS.register("acid_block",
            () -> new CustomFluidBlock(EndlessStoreFluids.ACID.get(),
                    Block.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<Item> PLASTIC_ACID_BUCKET = ITEMS.register("plastic_acid_bucket",
            () -> new CustomBucketItem(EndlessStoreFluids.ACID,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<FluidType> WASTE_WATER_FLUID_TYPE = FLUID_TYPES.register("waste_water",
            () -> new ModFluidType(
                    new ResourceLocation("minecraft:block/water_still"),
                    new ResourceLocation("minecraft:block/water_flow"),
                    0xA148D1CC,
                    FluidType.Properties.create()
                            .descriptionId("block.endless_store_mod.waste_water")
                            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            ));

    public static final RegistryObject<FlowingFluid> WASTE_WATER = FLUIDS.register("waste_water",
            () -> new WasteWaterFluid.Still(EndlessStoreFluids.WASTE_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_WASTE_WATER = FLUIDS.register("flowing_waste_water",
            () -> new WasteWaterFluid.Flowing(EndlessStoreFluids.WASTE_WATER_PROPERTIES));

    public static final RegistryObject<LiquidBlock> WASTE_WATER_BLOCK = BLOCKS.register("waste_water_block",
            () -> new LiquidBlock(EndlessStoreFluids.WASTE_WATER.get(),
                    Block.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<Item> PLASTIC_WASTE_WATER_BUCKET = ITEMS.register("plastic_waste_water_bucket",
            () -> new CustomBucketItem(EndlessStoreFluids.WASTE_WATER,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    // Fluid properties
    private static final ForgeFlowingFluid.Properties ACID_PROPERTIES =
            new ForgeFlowingFluid.Properties(ACID_FLUID_TYPE, ACID, FLOWING_ACID)
                    .block(ACID_BLOCK)
                    .bucket(PLASTIC_ACID_BUCKET);

    private static final ForgeFlowingFluid.Properties WASTE_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(WASTE_WATER_FLUID_TYPE, WASTE_WATER, FLOWING_WASTE_WATER)
                    .block(WASTE_WATER_BLOCK)
                    .bucket(PLASTIC_WASTE_WATER_BUCKET);

    // Метод для регистрации в главном классе
    public static void register(net.minecraftforge.eventbus.api.IEventBus modEventBus) {
        FLUID_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}