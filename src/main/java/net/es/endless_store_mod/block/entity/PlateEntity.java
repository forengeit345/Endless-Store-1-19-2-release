package net.es.endless_store_mod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PlateEntity extends BlockEntity {
    private ItemStack heldItem = ItemStack.EMPTY;
    private int rotation = 0;
    private boolean needsClientUpdate = false;

    public PlateEntity(BlockPos pos, BlockState state) {
        super(CustomBlockEntities.PLATE_ENTITY.get(), pos, state);
    }

    public boolean interact(Player player, InteractionHand hand) {
        ItemStack playerStack = player.getItemInHand(hand);
        Level level = getLevel();
        if (level == null) return false;

        if (!level.isClientSide) {
            if (!this.heldItem.isEmpty() && playerStack.isEmpty()) {
                // Игрок забирает предмет
                player.setItemInHand(hand, this.heldItem.copy());
                setHeldItem(ItemStack.EMPTY);
                markDirtyAndUpdate();
                return true;
            }
            else if (!playerStack.isEmpty() && this.heldItem.isEmpty()) {
                // Игрок кладет предмет
                ItemStack toPlace = playerStack.copy();
                toPlace.setCount(1);
                setHeldItem(toPlace);

                if (!player.getAbilities().instabuild) {
                    playerStack.shrink(1);
                }
                markDirtyAndUpdate();
                return true;
            }
            else if (playerStack.isEmpty() && !this.heldItem.isEmpty() && hand == InteractionHand.MAIN_HAND) {
                // Вращение предмета по пустой ПКМ
                setRotation((rotation + 1) % 8);
                markDirtyAndUpdate();
                return true;
            }
        }

        return false;
    }

    public void dropItem(Level level, BlockPos pos) {
        if (level.isClientSide) return;

        if (!this.heldItem.isEmpty()) {
            ItemEntity itemEntity = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 0.1,
                    pos.getZ() + 0.5,
                    this.heldItem.copy()
            );
            itemEntity.setDefaultPickUpDelay();
            level.addFreshEntity(itemEntity);

            setHeldItem(ItemStack.EMPTY);
            markDirtyAndUpdate();
        }
    }

    private void markDirtyAndUpdate() {
        this.setChanged();
        this.needsClientUpdate = true;

        Level level = getLevel();
        if (level != null && !level.isClientSide) {
            // Принудительное обновление блока для клиентов
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public void setChanged() {
        super.setChanged();
        this.needsClientUpdate = true;
    }

    public ItemStack getHeldItem() {
        return heldItem.copy();
    }

    public void setHeldItem(ItemStack stack) {
        if (stack.isEmpty()) {
            this.heldItem = ItemStack.EMPTY;
        } else {
            this.heldItem = stack.copy();
            this.heldItem.setCount(1);
        }
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation % 8;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!heldItem.isEmpty()) {
            CompoundTag itemTag = new CompoundTag();
            heldItem.save(itemTag);
            tag.put("Item", itemTag);
        }
        tag.putInt("Rotation", rotation);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Item")) {
            this.heldItem = ItemStack.of(tag.getCompound("Item"));
        } else {
            this.heldItem = ItemStack.EMPTY;
        }
        this.rotation = tag.getInt("Rotation");
    }

    // Для синхронизации при загрузке чанка
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    // Для пакетов обновления (блочные обновления)
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        if (needsClientUpdate) {
            needsClientUpdate = false;
            return ClientboundBlockEntityDataPacket.create(this);
        }
        return null;
    }
}