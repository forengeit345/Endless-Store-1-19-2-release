package net.es.block.entity;

import net.es.block.custom.CardboardBoxHugeTape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CardboardBoxHugeTapeEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> inventory;
    private final ContainerOpenersCounter openersCounter;

    private static BlockEntityType<CardboardBoxHugeTapeEntity> type() {
        return CardboardBoxHugeTape.TYPE.get();
    }

    public CardboardBoxHugeTapeEntity(BlockPos pos, BlockState state) {
        super(type(), pos, state);
        this.inventory = NonNullList.withSize(27, ItemStack.EMPTY);
        this.openersCounter = new ContainerOpenersCounter() {
            @Override
            protected void onOpen(Level level, BlockPos pos, BlockState state) {
                // Звук открытия (можно добавить позже)
            }

            @Override
            protected void onClose(Level level, BlockPos pos, BlockState state) {
                // Звук закрытия (можно добавить позже)
            }

            @Override
            protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
            }

            @Override
            protected boolean isOwnContainer(Player player) {
                if (player.containerMenu instanceof ChestMenu menu) {
                    Container container = menu.getContainer();
                    return container == CardboardBoxHugeTapeEntity.this;
                }
                return false;
            }
        };
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.inventory, registries);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.inventory, registries);
        }
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {
        this.inventory = list;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.cardboard_box_huge_tape");
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory) {
        return ChestMenu.threeRows(syncId, playerInventory, this);
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void tick() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }
}