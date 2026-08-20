package net.es.entity.custom;

import net.es.entity.EndlessStoreEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PlateEntity extends Entity {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM =
            SynchedEntityData.defineId(PlateEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> DATA_ROTATION =
            SynchedEntityData.defineId(PlateEntity.class, EntityDataSerializers.INT);

    private BlockPos platePos; // позиция блока тарелки

    public PlateEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
        this.setPos(0, 0, 0);
    }

    public PlateEntity(Level level, BlockPos pos, Direction facing) {
        this(EndlessStoreEntities.PLATE.get(), level);
        this.setPos(pos.getX() + 0.5, pos.getY() + 0.03125, pos.getZ() + 0.5);
        int rotation = 0;
        if (facing.getAxis().isHorizontal()) {
            rotation = switch (facing) {
                case EAST -> 2;
                case SOUTH -> 4;
                case WEST -> 6;
                default -> 0;
            };
        }
        this.entityData.set(DATA_ROTATION, rotation);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_ITEM, ItemStack.EMPTY);
        builder.define(DATA_ROTATION, 0);
    }

    // --- Геттеры / сеттеры ---
    public ItemStack getItem() {
        return this.entityData.get(DATA_ITEM);
    }

    public void setItem(ItemStack stack) {
        if (!stack.isEmpty()) {
            stack = stack.copyWithCount(1);
        }
        this.entityData.set(DATA_ITEM, stack);
    }

    public int getRotation() {
        return this.entityData.get(DATA_ROTATION);
    }

    public void setRotation(int rotation) {
        this.entityData.set(DATA_ROTATION, rotation % 8);
    }

    // --- Взаимодействие ---
    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack playerStack = player.getItemInHand(hand);
        if (this.level().isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (!this.getItem().isEmpty() && playerStack.isEmpty()) {
            player.setItemInHand(hand, this.getItem().copy());
            this.setItem(ItemStack.EMPTY);
            this.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.0F, 1.0F);
            return InteractionResult.CONSUME;
        } else if (!playerStack.isEmpty() && this.getItem().isEmpty()) {
            ItemStack toPlace = playerStack.copy();
            toPlace.setCount(1);
            this.setItem(toPlace);
            if (!player.isCreative()) {
                playerStack.shrink(1);
            }
            this.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            return InteractionResult.CONSUME;
        } else if (playerStack.isEmpty() && !this.getItem().isEmpty() && hand == InteractionHand.MAIN_HAND) {
            setRotation((getRotation() + 1) % 8);
            this.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM, 1.0F, 1.0F);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    // --- Физика ---
    @Override
    public boolean isPickable() { return false; }
    @Override
    public boolean isPushable() { return false; }
    @Override
    public boolean isNoGravity() { return true; }
    @Override
    public void push(Entity entity) {}
    @Override
    public void push(double x, double y, double z) {}

    @Override
    public boolean shouldBeSaved() {
        return true;
    }

    @Override
    public void tick() {
    }


    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
//        System.out.println("Saving PlateEntity at " + this.getX() + "," + this.getY() + "," + this.getZ() + " item=" + this.getItem());
        if (!this.getItem().isEmpty()) {
            tag.put("Item", this.getItem().save(this.registryAccess()));
        }
        tag.putInt("Rotation", this.getRotation());
    }

    // --- Сохранение и загрузка (ВАЖНО: вызовы super!) ---
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
//        System.out.println("Loading PlateEntity, tag contains Item? " + tag.contains("Item"));
        if (tag.contains("Item")) {
            ItemStack itemstack = ItemStack.parse(this.registryAccess(), tag.getCompound("Item")).orElse(ItemStack.EMPTY);
            this.setItem(itemstack);
        }
        if (tag.contains("Rotation")) {
            this.setRotation(tag.getInt("Rotation"));
        }
    }

    // --- Дроп предмета ---
    public void dropItem() {
        if (!this.getItem().isEmpty()) {
            ItemEntity itemEntity = new ItemEntity(
                    this.level(),
                    this.getX(), this.getY() + 0.1, this.getZ(),
                    this.getItem().copy()
            );
            itemEntity.setDefaultPickUpDelay();
            this.level().addFreshEntity(itemEntity);
            this.setItem(ItemStack.EMPTY);
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        if (!this.level().isClientSide && reason.shouldDestroy()) {
            this.dropItem();
        }
        super.remove(reason);
    }
}