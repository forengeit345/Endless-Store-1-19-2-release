package net.es.neoforge.fluid;

import net.es.fluid.WasteWaterFluid;
import net.neoforged.neoforge.fluids.FluidType;

public class WasteWaterFluidNeoForge {
    public static class Still extends WasteWaterFluid.Still {
        @Override
        public FluidType getFluidType() {
            return EndlessStoreFluidsNeoForge.WASTE_WATER_TYPE.get();
        }
    }

    public static class Flowing extends WasteWaterFluid.Flowing {
        @Override
        public FluidType getFluidType() {
            return EndlessStoreFluidsNeoForge.WASTE_WATER_TYPE.get();
        }
    }
}