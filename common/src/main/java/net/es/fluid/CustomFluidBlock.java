package net.es.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class CustomFluidBlock extends LiquidBlock {
    public CustomFluidBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);
        entity.setDeltaMovement(entity.getDeltaMovement().scale(0.5));
        if (entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
            living.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
        }
    }
}