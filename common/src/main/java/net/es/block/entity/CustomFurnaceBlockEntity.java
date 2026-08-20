package net.es.block.entity;

import net.es.block.custom.CustomFurnaceBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public CustomFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(CustomFurnaceBlock.TYPE.get(), pos, state, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory inventory) {
        return new FurnaceMenu(syncId, inventory, this, this.dataAccess);
    }
}