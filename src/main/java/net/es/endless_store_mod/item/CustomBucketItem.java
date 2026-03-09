package net.es.endless_store_mod.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class CustomBucketItem extends Item implements DispensibleContainerItem {
    // Убираем прямое поле content, оставляем только Supplier
    private final Supplier<? extends Fluid> fluidSupplier;

    // Устаревший конструктор – для совместимости, но лучше использовать Supplier
    @Deprecated
    public CustomBucketItem(Fluid pContent, Item.Properties pProperties) {
        super(pProperties);
        // Для обратной совместимости создаём Supplier, который всегда возвращает эту жидкость
        this.fluidSupplier = () -> pContent;
    }

    // Основной конструктор с Supplier (рекомендуемый)
    public CustomBucketItem(Supplier<? extends Fluid> supplier, Item.Properties builder) {
        super(builder);
        this.fluidSupplier = supplier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Fluid currentFluid = getFluid(); // всегда используем getFluid()

        // Определяем режим рейкаста: если жидкость пустая – ищем источники, иначе ничего особенного
        BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer,
                currentFluid == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(pPlayer, pLevel, itemstack, blockhitresult);
        if (ret != null) return ret;

        if (blockhitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (pLevel.mayInteract(pPlayer, blockpos) && pPlayer.mayUseItemAt(blockpos1, direction, itemstack)) {
                // Пустое ведро – забор жидкости
                if (currentFluid == Fluids.EMPTY) {
                    BlockState blockstate1 = pLevel.getBlockState(blockpos);
                    if (blockstate1.getBlock() instanceof BucketPickup bucketpickup) {
                        ItemStack itemstack1 = bucketpickup.pickupBlock(pLevel, blockpos, blockstate1);
                        if (!itemstack1.isEmpty()) {
                            pPlayer.awardStat(Stats.ITEM_USED.get(this));
                            bucketpickup.getPickupSound(blockstate1).ifPresent(p_150709_ -> pPlayer.playSound(p_150709_, 1.0F, 1.0F));
                            pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, blockpos);
                            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, pPlayer, itemstack1);
                            if (!pLevel.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) pPlayer, itemstack1);
                            }
                            return InteractionResultHolder.sidedSuccess(itemstack2, pLevel.isClientSide());
                        }
                    }
                    return InteractionResultHolder.fail(itemstack);
                }
                // Наполненное ведро – размещение жидкости
                else {
                    BlockState blockstate = pLevel.getBlockState(blockpos);
                    BlockPos blockpos2 = canBlockContainFluid(pLevel, blockpos, blockstate) ? blockpos : blockpos1;
                    if (this.emptyContents(pPlayer, pLevel, blockpos2, blockhitresult, itemstack)) {
                        this.checkExtraContent(pPlayer, pLevel, itemstack, blockpos2);
                        if (pPlayer instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) pPlayer, blockpos2, itemstack);
                        }
                        pPlayer.awardStat(Stats.ITEM_USED.get(this));
                        return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(itemstack, pPlayer), pLevel.isClientSide());
                    } else {
                        return InteractionResultHolder.fail(itemstack);
                    }
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    public static ItemStack getEmptySuccessItem(ItemStack pBucketStack, Player pPlayer) {
        return !pPlayer.getAbilities().instabuild ? new ItemStack(EndlessStoreItems.PLASTIC_BUCKET.get()) : pBucketStack;
    }

    public void checkExtraContent(@Nullable Player pPlayer, Level pLevel, ItemStack pContainerStack, BlockPos pPos) {
    }

    @Deprecated //Forge: use the ItemStack sensitive version
    public boolean emptyContents(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, @Nullable BlockHitResult pResult) {
        return this.emptyContents(pPlayer, pLevel, pPos, pResult, null);
    }

    public boolean emptyContents(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, @Nullable BlockHitResult pResult, @Nullable ItemStack container) {
        Fluid fluid = getFluid(); // используем getFluid()
        if (!(fluid instanceof FlowingFluid flowingFluid)) {
            return false;
        }
        BlockState blockstate = pLevel.getBlockState(pPos);
        Block block = blockstate.getBlock();
        boolean flag = blockstate.canBeReplaced(fluid);
        boolean flag1 = blockstate.isAir() || flag || (block instanceof LiquidBlockContainer liquidContainer && liquidContainer.canPlaceLiquid(pLevel, pPos, blockstate, fluid));

        java.util.Optional<net.minecraftforge.fluids.FluidStack> containedFluidStack = java.util.Optional.ofNullable(container).flatMap(net.minecraftforge.fluids.FluidUtil::getFluidContained);
        if (!flag1) {
            return pResult != null && this.emptyContents(pPlayer, pLevel, pResult.getBlockPos().relative(pResult.getDirection()), null, container);
        } else if (containedFluidStack.isPresent() && fluid.getFluidType().isVaporizedOnPlacement(pLevel, pPos, containedFluidStack.get())) {
            fluid.getFluidType().onVaporize(pPlayer, pLevel, pPos, containedFluidStack.get());
            return true;
        } else if (pLevel.dimensionType().ultraWarm() && fluid.is(FluidTags.WATER)) {
            int i = pPos.getX();
            int j = pPos.getY();
            int k = pPos.getZ();
            pLevel.playSound(pPlayer, pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.8F);
            for (int l = 0; l < 8; ++l) {
                pLevel.addParticle(ParticleTypes.LARGE_SMOKE, i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D);
            }
            return true;
        } else if (block instanceof LiquidBlockContainer liquidContainer && liquidContainer.canPlaceLiquid(pLevel, pPos, blockstate, fluid)) {
            liquidContainer.placeLiquid(pLevel, pPos, blockstate, flowingFluid.getSource(false));
            return true;
        } else {
            if (!pLevel.isClientSide && flag && !blockstate.liquid()) {
                pLevel.destroyBlock(pPos, true);
            }
            if (!pLevel.setBlock(pPos, fluid.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @Nullable net.minecraft.nbt.CompoundTag nbt) {
        if (this.getClass() == CustomBucketItem.class)
            return new net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper(stack);
        else
            return super.initCapabilities(stack, nbt);
    }

    public Fluid getFluid() {
        return fluidSupplier.get();
    }

    protected boolean canBlockContainFluid(Level worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer liquidContainer &&
                liquidContainer.canPlaceLiquid(worldIn, posIn, blockstate, getFluid());
    }
}