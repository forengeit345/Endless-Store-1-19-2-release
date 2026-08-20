package net.es.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class AcidFluid extends FlowingFluid {

    @Override
    public Fluid getFlowing() {
        return EndlessStoreFluids.FLOWING_ACID.get();
    }

    @Override
    public Fluid getSource() {
        return EndlessStoreFluids.ACID.get();
    }

    @Override
    public Item getBucket() {
        return EndlessStoreFluids.PLASTIC_ACID_BUCKET.get();
    }

    @Override
    public void animateTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
        if (!state.isSource() && !state.getValue(FALLING)) {
            if (random.nextInt(64) == 0) {
                level.playLocalSound(
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS,
                        random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false
                );
            }
        } else if (random.nextInt(10) == 0) {
            level.addParticle(ParticleTypes.UNDERWATER,
                    pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(),
                    0.0, 0.0, 0.0);
        }
    }

    @Nullable
    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    @Override
    protected boolean canConvertToSource(Level level) {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, blockEntity);
    }

    @Override
    public int getSlopeFindDistance(LevelReader level) {
        return 4;
    }

    @Override
    public BlockState createLegacyBlock(FluidState state) {
        return EndlessStoreFluids.ACID_BLOCK.get().defaultBlockState()
                .setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public boolean isSame(Fluid fluid) {
        return fluid == EndlessStoreFluids.ACID.get() || fluid == EndlessStoreFluids.FLOWING_ACID.get();
    }

    @Override
    protected int getDropOff(LevelReader level) {
        return 1;
    }

    @Override
    public int getTickDelay(LevelReader level) {
        return 10;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }

    public static class Flowing extends AcidFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Still extends AcidFluid {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}