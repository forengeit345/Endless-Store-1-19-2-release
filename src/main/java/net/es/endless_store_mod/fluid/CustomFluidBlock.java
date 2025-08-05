package net.es.endless_store_mod.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomFluidBlock extends FluidBlock {
    public CustomFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        entity.setVelocity(entity.getVelocity().multiply(0.5));
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addStatusEffect(
                    new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0));
            ((LivingEntity) entity).addStatusEffect(
                    new StatusEffectInstance(StatusEffects.POISON, 200, 0));
        }
    }
}