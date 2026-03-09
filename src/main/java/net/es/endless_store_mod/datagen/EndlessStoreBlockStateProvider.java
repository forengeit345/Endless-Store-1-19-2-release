package net.es.endless_store_mod.datagen;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class EndlessStoreBlockStateProvider extends BlockStateProvider {
    public EndlessStoreBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EndlessStoreMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(EndlessStoreBlocks.LAMINATE_FLOORING_LIGHT);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}