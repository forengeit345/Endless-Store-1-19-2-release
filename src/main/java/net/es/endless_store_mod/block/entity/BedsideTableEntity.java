package net.es.endless_store_mod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BedsideTableEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private LazyOptional<IItemHandler> itemHandler = LazyOptional.of(() -> new InvWrapper(this));

    // Счетчик игроков, смотрящих контейнер (аналог ViewerCountManager из Fabric)
    private int openCount;

    public BedsideTableEntity(BlockPos pos, BlockState state) {
        super(CustomBlockEntities.BEDSIDE_TABLE.get(), pos, state);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items);
        }
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items);
        }
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return ChestMenu.threeRows(id, playerInventory, this);
    }

    @Override
    protected net.minecraft.network.chat.Component getDefaultName() {
        return net.minecraft.network.chat.Component.translatable("container.bedside_table");
    }

    // Методы для отслеживания открытия/закрытия (аналог ViewerCountManager)
    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            if (this.openCount++ < 0) {
                this.openCount = 0;
            }

            // Здесь можно добавить звуки открытия
            // this.playSound(state, SoundEvents.BARREL_OPEN);

            // Отметить, что данные изменились для синхронизации с клиентом
            this.setChanged();
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            if (this.openCount-- < 0) {
                this.openCount = 0;
            }

            // Здесь можно добавить звуки закрытия
            // this.playSound(state, SoundEvents.BARREL_CLOSE);

            this.setChanged();
        }
    }

    // Метод для тиков (вызывается из блока)
    public void tick() {
        // Если нужно обновлять счетчик или другие данные каждый тик
    }

    // Система синхронизации данных с клиентом
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    // Capability система Forge для инвентарей (для совместимости с воронками и трубами)
    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
        if (!this.remove && cap == net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER) {
            return this.itemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.itemHandler.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.itemHandler = LazyOptional.of(() -> new InvWrapper(this));
    }
}