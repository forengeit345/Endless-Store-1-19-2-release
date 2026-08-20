package net.es.neoforge.client;

import net.es.EndlessStoreMod;
import net.es.block.EndlessStoreBlocks;
import net.es.client.renderer.PlateEntityRenderer;
import net.es.entity.EndlessStoreEntities;
import net.es.entity.client.*;
import net.es.fluid.EndlessStoreFluids;
import net.es.neoforge.fluid.EndlessStoreFluidsNeoForge;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = EndlessStoreMod.MOD_ID)
public class EndlessStoreModNeoForgeClient {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EndlessStoreEntities.PLATE.get(), PlateEntityRenderer::new);
        event.registerEntityRenderer(EndlessStoreEntities.EMPLOYEE.get(), EmployeeRenderer::new);
        event.registerEntityRenderer(EndlessStoreEntities.JACK.get(), JackRenderer::new);
        event.registerEntityRenderer(EndlessStoreEntities.SECURITY.get(), SecurityRenderer::new);
        event.registerEntityRenderer(EndlessStoreEntities.WATCHER.get(), WatcherRenderer::new);
        event.registerEntityRenderer(EndlessStoreEntities.WATER_STRIDER.get(), WaterStriderRenderer::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(EndlessStoreFluids.ACID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(EndlessStoreFluids.FLOWING_ACID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(EndlessStoreFluids.WASTE_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(EndlessStoreFluids.FLOWING_WASTE_WATER.get(), RenderType.translucent());
            Block chainMail = BuiltInRegistries.BLOCK.get(
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CHAIN_MAIL)
            );
            ItemBlockRenderTypes.setRenderLayer(chainMail, RenderType.cutout());
        });
    }

    @SubscribeEvent
    static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return ResourceLocation.withDefaultNamespace("block/water_still");
            }
            @Override
            public ResourceLocation getFlowingTexture() {
                return ResourceLocation.withDefaultNamespace("block/water_flow");
            }
            @Override
            public int getTintColor() {
                return 0xA1E0FFFF;
            }
        }, EndlessStoreFluidsNeoForge.ACID_TYPE.get());

        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return ResourceLocation.withDefaultNamespace("block/water_still");
            }
            @Override
            public ResourceLocation getFlowingTexture() {
                return ResourceLocation.withDefaultNamespace("block/water_flow");
            }
            @Override
            public int getTintColor() {
                return 0xA148D1CC;
            }
        }, EndlessStoreFluidsNeoForge.WASTE_WATER_TYPE.get());
    }
}