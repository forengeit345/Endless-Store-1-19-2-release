package net.es.fluid;

import net.es.item.CustomBucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class EndlessStoreFluids {

    // Поставщики для кислоты
    public static Supplier<Fluid> ACID = () -> { throw new IllegalStateException("Acid fluid not registered"); };
    public static Supplier<FlowingFluid> FLOWING_ACID = () -> { throw new IllegalStateException("Flowing acid fluid not registered"); };
    public static Supplier<Block> ACID_BLOCK = () -> { throw new IllegalStateException("Acid block not registered"); };
    public static Supplier<Item> PLASTIC_ACID_BUCKET = () -> { throw new IllegalStateException("Acid bucket not registered"); };

    // Поставщики для сточных вод
    public static Supplier<Fluid> WASTE_WATER = () -> { throw new IllegalStateException("Waste water fluid not registered"); };
    public static Supplier<FlowingFluid> FLOWING_WASTE_WATER = () -> { throw new IllegalStateException("Flowing waste water fluid not registered"); };
    public static Supplier<Block> WASTE_WATER_BLOCK = () -> { throw new IllegalStateException("Waste water block not registered"); };
    public static Supplier<Item> PLASTIC_WASTE_WATER_BUCKET = () -> { throw new IllegalStateException("Waste water bucket not registered"); };

    @FunctionalInterface
    public interface FluidRegistry {
        void register(String name,
                      Supplier<Fluid> stillFactory,
                      Supplier<FlowingFluid> flowingFactory,
                      Supplier<Block> blockFactory,
                      Supplier<Item> bucketFactory);
    }

    public static void init(FluidRegistry registry) {
        // Регистрируем кислоту
        registry.register("acid",
                AcidFluid.Still::new,
                AcidFluid.Flowing::new,
                () -> new CustomFluidBlock(FLOWING_ACID.get(),
                        Block.Properties.ofFullCopy(Blocks.WATER).replaceable()),
                () -> new CustomBucketItem(ACID.get(),
                        new Item.Properties().stacksTo(1))
        );

        // Регистрируем сточные воды (ведро тоже можно добавить, если нужно)
        registry.register("waste_water",
                WasteWaterFluid.Still::new,
                WasteWaterFluid.Flowing::new,
                () -> new CustomWaterFluidBlock(FLOWING_WASTE_WATER.get(),
                        Block.Properties.ofFullCopy(Blocks.WATER).replaceable()),
                () -> new CustomBucketItem(WASTE_WATER.get(),
                        new Item.Properties().stacksTo(1))
        );
    }
}
