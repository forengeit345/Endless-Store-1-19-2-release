package net.es.neoforge.fluid;

import net.neoforged.neoforge.fluids.FluidType;
import java.util.function.Supplier;

public class EndlessStoreFluidsNeoForge {
    public static Supplier<FluidType> ACID_TYPE = () -> {
        throw new IllegalStateException("Acid type not registered");
    };
    public static Supplier<FluidType> WASTE_WATER_TYPE = () -> {
        throw new IllegalStateException("Waste water type not registered");
    };
}