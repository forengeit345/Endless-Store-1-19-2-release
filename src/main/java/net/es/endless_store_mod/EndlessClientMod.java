package net.es.endless_store_mod;

import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.es.endless_store_mod.block.entity.CustomBlockEntities;
import net.es.endless_store_mod.block.entity.PlateEntityRenderer;
import net.es.endless_store_mod.entity.EndlessStoreEntities;
import net.es.endless_store_mod.entity.client.*;
import net.es.endless_store_mod.fluid.EndlessStoreFluids;
import net.es.endless_store_mod.item.EndlessStoreItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.Identifier;

public class EndlessClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EndlessStoreEntities.EMPLOYEE, EmployeeRenderer::new);
        EntityRendererRegistry.register(EndlessStoreEntities.JACK, JackRenderer::new);
        EntityRendererRegistry.register(EndlessStoreEntities.SECURITY, SecurityRenderer::new);
        EntityRendererRegistry.register(EndlessStoreEntities.WATCHER, WatcherRenderer::new);
        EntityRendererRegistry.register(EndlessStoreEntities.WATER_STRIDER, WaterStriderRenderer::new);

//        GeoArmorRenderer.registerArmorRenderer(new EmployeeShirtRenderer(), EndlessStoreItems.EMPLOYEES_SHIRT);
//        GeoArmorRenderer.registerArmorRenderer(new SecurityArmorRenderer(), EndlessStoreItems.SECURITY_CAP, EndlessStoreItems.SECURITY_SHIRT);

        BlockRenderLayerMap.INSTANCE.putBlock(EndlessStoreBlocks.GRID_VERTICAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EndlessStoreBlocks.FIRE_CRANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EndlessStoreBlocks.BOTTLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EndlessStoreBlocks.CHAIN_MAIL, RenderLayer.getCutout());

        FluidRenderHandlerRegistry.INSTANCE.register(EndlessStoreFluids.ACID, EndlessStoreFluids.FLOWING_ACID, new SimpleFluidRenderHandler(new Identifier("minecraft:block/water_still"), new Identifier("minecraft:block/water_flow"), 0xA1E0FFFF));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), EndlessStoreFluids.ACID, EndlessStoreFluids.FLOWING_ACID);
        FluidRenderHandlerRegistry.INSTANCE.register(EndlessStoreFluids.WASTE_WATER, EndlessStoreFluids.FLOWING_WASTE_WATER, new SimpleFluidRenderHandler(new Identifier("minecraft:block/water_still"), new Identifier("minecraft:block/water_flow"), 0xA148D1CC));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), EndlessStoreFluids.WASTE_WATER, EndlessStoreFluids.FLOWING_WASTE_WATER);

        BlockEntityRendererFactories.register(CustomBlockEntities.PLATE_ENTITY, PlateEntityRenderer::new);
//        BlockRenderLayerMap.INSTANCE.putBlock(EndlessStoreBlocks.BLACK_DOOR, RenderLayer.getCutout());
    }
}
