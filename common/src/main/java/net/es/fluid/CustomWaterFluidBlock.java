package net.es.fluid;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

public class CustomWaterFluidBlock extends LiquidBlock {
    public CustomWaterFluidBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }
}