package net.es.endless_store_mod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlateEntity extends BlockEntity {
    private ItemStack heldItem = ItemStack.EMPTY;
    private int rotation = 0;

    // Флаг для отслеживания изменений
    private boolean needsClientUpdate = false;

    public PlateEntity(BlockPos pos, BlockState state) {
        super(CustomBlockEntities.PLATE_ENTITY, pos, state);
    }

    public boolean interact(PlayerEntity player, Hand hand) {
        ItemStack playerStack = player.getStackInHand(hand);
        World world = getWorld();
        if (world == null) return false;

        if (!world.isClient) { // Только на сервере
            if (!this.heldItem.isEmpty() && playerStack.isEmpty()) {
                // Игрок забирает предмет
                player.setStackInHand(hand, this.heldItem.copy());
                setHeldItem(ItemStack.EMPTY);
                markDirtyAndUpdate();
                return true;
            }
            else if (!playerStack.isEmpty() && this.heldItem.isEmpty()) {
                // Игрок кладет предмет
                ItemStack toPlace = playerStack.copy();
                toPlace.setCount(1);
                setHeldItem(toPlace);

                if (!player.isCreative()) {
                    playerStack.decrement(1);
                }
                markDirtyAndUpdate();
                return true;
            }
            else if (playerStack.isEmpty() && !this.heldItem.isEmpty() && hand == Hand.MAIN_HAND) {
                // Вращение предмета по пустой ПКМ
                setRotation((rotation + 1) % 8);
                markDirtyAndUpdate();
                return true;
            }
        }

        return false;
    }

    public void dropItem(World world, BlockPos pos) {
        if (world.isClient) return; // Только на сервере

        if (!this.heldItem.isEmpty()) {
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    pos.getX() + 0.5,
                    pos.getY() + 0.1, // Чуть выше тарелки
                    pos.getZ() + 0.5,
                    this.heldItem.copy()
            );
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);

            // ОЧЕНЬ ВАЖНО: очищаем предмет и обновляем клиент
            setHeldItem(ItemStack.EMPTY);
            markDirtyAndUpdate();
        }
    }

    private void markDirtyAndUpdate() {
        this.markDirty();
        this.needsClientUpdate = true;

        if (world != null && !world.isClient) {
            // Принудительное обновление блока для клиентов
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    @Override
    public void markDirty() {
        super.markDirty();
        this.needsClientUpdate = true;
    }

    public ItemStack getHeldItem() {
        return heldItem.copy(); // Возвращаем копию
    }

    public void setHeldItem(ItemStack stack) {
        if (stack.isEmpty()) {
            this.heldItem = ItemStack.EMPTY;
        } else {
            this.heldItem = stack.copy();
            this.heldItem.setCount(1);
        }
    }

    public int getRotation() { return rotation; }

    public void setRotation(int rotation) {
        this.rotation = rotation % 8;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!heldItem.isEmpty()) {
            NbtCompound itemNbt = new NbtCompound();
            heldItem.writeNbt(itemNbt);
            nbt.put("Item", itemNbt);
        }
        nbt.putInt("Rotation", rotation);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("Item")) {
            this.heldItem = ItemStack.fromNbt(nbt.getCompound("Item"));
        } else {
            this.heldItem = ItemStack.EMPTY; // Явное очищение
        }
        this.rotation = nbt.getInt("Rotation");
    }

    // Критически важный метод для синхронизации с клиентом
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbt = super.toInitialChunkDataNbt();
        writeNbt(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        if (needsClientUpdate) {
            needsClientUpdate = false;
            return BlockEntityUpdateS2CPacket.create(this);
        }
        return null;
    }
}