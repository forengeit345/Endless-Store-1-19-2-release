package net.es.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class CustomBucketItem extends BucketItem {
    private final Fluid content;

    public CustomBucketItem(Fluid fluid, Properties properties) {
        super(fluid, properties);
        this.content = fluid;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemStack);
        } else if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemStack);
        } else {
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getDirection();
            BlockPos blockPos2 = blockPos.relative(direction);
            if (level.mayInteract(player, blockPos) && player.mayUseItemAt(blockPos2, direction, itemStack)) {
                if (this.content == Fluids.EMPTY) {
                    // Зачерпывание жидкости
                    BlockState blockState = level.getBlockState(blockPos);
                    if (blockState.getBlock() instanceof BucketPickup bucketPickup) {
                        ItemStack filledStack = bucketPickup.pickupBlock(player, level, blockPos, blockState);
                        if (!filledStack.isEmpty()) {
                            player.awardStat(Stats.ITEM_USED.get(this));
                            bucketPickup.getPickupSound().ifPresent(sound -> player.playSound(sound, 1.0F, 1.0F));
                            level.gameEvent(player, GameEvent.FLUID_PICKUP, blockPos);
                            ItemStack result = ItemUtils.createFilledResult(itemStack, player, filledStack);
                            if (!level.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, filledStack);
                            }
                            return InteractionResultHolder.sidedSuccess(result, level.isClientSide());
                        }
                    }
                    return InteractionResultHolder.fail(itemStack);
                } else {
                    // Выливание жидкости
                    BlockState blockState = level.getBlockState(blockPos);
                    BlockPos placePos = blockState.getBlock() instanceof LiquidBlockContainer && this.content == Fluids.WATER ? blockPos : blockPos2;
                    if (this.emptyContents(player, level, placePos, blockHitResult)) {
                        this.checkExtraContent(player, level, itemStack, placePos);
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, placePos, itemStack);
                        }
                        player.awardStat(Stats.ITEM_USED.get(this));
                        ItemStack result = ItemUtils.createFilledResult(itemStack, player, getEmptySuccessItem(itemStack, player));
                        return InteractionResultHolder.sidedSuccess(result, level.isClientSide());
                    } else {
                        return InteractionResultHolder.fail(itemStack);
                    }
                }
            } else {
                return InteractionResultHolder.fail(itemStack);
            }
        }
    }

    public static ItemStack getEmptySuccessItem(ItemStack stack, Player player) {
        return !player.hasInfiniteMaterials() ? new ItemStack(Items.BUCKET) : stack;
    }

    public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult hitResult) {
        Fluid fluid = this.content;
        if (!(fluid instanceof FlowingFluid flowingFluid)) {
            return false;
        }
        BlockState blockState = level.getBlockState(pos);
        boolean canPlace = blockState.canBeReplaced(fluid);
        if (!blockState.isAir() && !canPlace) {
            if (blockState.getBlock() instanceof LiquidBlockContainer liquidBlockContainer) {
                if (!liquidBlockContainer.canPlaceLiquid(player, level, pos, blockState, fluid)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        if (level.dimensionType().ultraWarm() && fluid.is(FluidTags.WATER)) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
            for (int l = 0; l < 8; ++l) {
                level.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
            }
            return true;
        }
        if (blockState.getBlock() instanceof LiquidBlockContainer && fluid == Fluids.WATER) {
            ((LiquidBlockContainer) blockState.getBlock()).placeLiquid(level, pos, blockState, flowingFluid.getSource(false));
            this.playEmptySound(player, level, pos);
            return true;
        }
        if (!level.isClientSide && canPlace && !blockState.liquid()) {
            level.destroyBlock(pos, true);
        }
        if (!level.setBlock(pos, fluid.defaultFluidState().createLegacyBlock(), 11) && !blockState.getFluidState().isSource()) {
            return false;
        }
        this.playEmptySound(player, level, pos);
        return true;
    }

    protected void playEmptySound(@Nullable Player player, Level level, BlockPos pos) {
        SoundEvent sound = this.content.is(FluidTags.LAVA) ? SoundEvents.BUCKET_EMPTY_LAVA : SoundEvents.BUCKET_EMPTY;
        level.playSound(player, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.gameEvent(player, GameEvent.FLUID_PLACE, pos);
    }
}
