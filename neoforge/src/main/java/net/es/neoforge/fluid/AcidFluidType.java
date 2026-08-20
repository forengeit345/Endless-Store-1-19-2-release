package net.es.neoforge.fluid;

import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;

public class AcidFluidType extends FluidType {
    public AcidFluidType() {
        super(Properties.create()
                .descriptionId("block.endless_store_mod.acid")
                .canSwim(true)
                .canDrown(true)
                .supportsBoating(false)
                .lightLevel(0)
                .density(1000)
                .temperature(300)
                .viscosity(1000)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
        );
    }
}
