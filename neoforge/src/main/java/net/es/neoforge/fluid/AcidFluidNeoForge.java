package net.es.neoforge.fluid;

import net.es.fluid.AcidFluid;
import net.neoforged.neoforge.fluids.FluidType;

public class AcidFluidNeoForge {
    public static class Still extends AcidFluid.Still {
        @Override
        public FluidType getFluidType() {
            return EndlessStoreFluidsNeoForge.ACID_TYPE.get();
        }
    }

    public static class Flowing extends AcidFluid.Flowing {
        @Override
        public FluidType getFluidType() {
            return EndlessStoreFluidsNeoForge.ACID_TYPE.get();
        }
    }
}