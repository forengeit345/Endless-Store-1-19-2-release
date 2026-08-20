package net.es.fabric.client;

import net.es.EndlessStoreMod;
import net.es.block.EndlessStoreBlocks;
import net.es.client.renderer.PlateEntityRenderer;
import net.es.entity.EndlessStoreEntities;
import net.es.entity.client.*;
import net.es.fluid.EndlessStoreFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public final class EndlessStoreModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Block chainMail = BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CHAIN_MAIL)
        );
        BlockRenderLayerMap.INSTANCE.putBlock(chainMail, RenderType.cutout());

        EntityRendererRegistry.register(EndlessStoreEntities.PLATE.get(), PlateEntityRenderer::new);

        FluidRenderHandlerRegistry.INSTANCE.register(
                EndlessStoreFluids.ACID.get(),
                EndlessStoreFluids.FLOWING_ACID.get(),

                new SimpleFluidRenderHandler(
                        ResourceLocation.withDefaultNamespace("block/water_still"),
                        ResourceLocation.withDefaultNamespace("block/water_flow"),
                        0xA1E0FFFF
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                EndlessStoreFluids.WASTE_WATER.get(),
                EndlessStoreFluids.FLOWING_WASTE_WATER.get(),

                new SimpleFluidRenderHandler(
                        ResourceLocation.withDefaultNamespace("block/water_still"),
                        ResourceLocation.withDefaultNamespace("block/water_flow"),
                        0xA148D1CC
                )
        );

        ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.addModels(
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "knife_3d"),
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "baton_3d"),
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "frying_pan_3d"),
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "extinguisher_3d")
            );
        });


        BlockRenderLayerMap.INSTANCE.putFluids(
                RenderType.translucent(),
                EndlessStoreFluids.ACID.get(),
                EndlessStoreFluids.FLOWING_ACID.get(),
                EndlessStoreFluids.WASTE_WATER.get(),
                EndlessStoreFluids.FLOWING_WASTE_WATER.get()
        );

        EntityRendererRegistry.register(EndlessStoreEntities.EMPLOYEE.get(), EmployeeRenderer::new);
        EntityRendererRegistry.register(EndlessStoreEntities.JACK.get(), JackRenderer::new);
        EntityRendererRegistry.register(EndlessStoreEntities.SECURITY.get(), SecurityRenderer::new);
        EntityRendererRegistry.register(EndlessStoreEntities.WATCHER.get(), WatcherRenderer::new);
        EntityRendererRegistry.register(EndlessStoreEntities.WATER_STRIDER.get(), WaterStriderRenderer::new);
    }
}
