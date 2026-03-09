package net.es.endless_store_mod;

import com.mojang.logging.LogUtils;
import net.es.endless_store_mod.block.EndlessStoreBlocks;
import net.es.endless_store_mod.block.entity.CustomBlockEntities;
import net.es.endless_store_mod.block.entity.PlateEntityRenderer;
import net.es.endless_store_mod.entity.EndlessStoreEntities;
import net.es.endless_store_mod.entity.client.*;
import net.es.endless_store_mod.entity.custom.JackEntity;
import net.es.endless_store_mod.entity.custom.SecurityEntity;
import net.es.endless_store_mod.entity.custom.WatcherEntity;
import net.es.endless_store_mod.fluid.EndlessStoreFluids;
import net.es.endless_store_mod.item.EndlessStoreItemGroup;
import net.es.endless_store_mod.item.EndlessStoreItems;
import net.es.endless_store_mod.registry.EndlessStoreFeatures;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(EndlessStoreMod.MOD_ID)
public class EndlessStoreMod {
    public static final String MOD_ID = "endless_store_mod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EndlessStoreMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EndlessStoreItemGroup.register(modEventBus);
        EndlessStoreItems.register(modEventBus);
        EndlessStoreBlocks.register(modEventBus);
        EndlessStoreFluids.register(modEventBus);
        EndlessStoreEntities.register(modEventBus);
        CustomBlockEntities.register(modEventBus);


        EndlessStoreFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerAttributes);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(
                    EndlessStoreEntities.WATER_STRIDER.get(),
                    SpawnPlacements.Type.NO_RESTRICTIONS,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    (type, world, reason, pos, random) -> {
                        boolean defaultMonsterCheck = Monster.checkMonsterSpawnRules(type, world, reason, pos, random);
                        if (!defaultMonsterCheck) return false;

                        int y = pos.getY();
                        boolean heightOk = y >= 31 && y <= 49;

                        return heightOk;
                    }
            );
        });
    }

    private void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(EndlessStoreEntities.SECURITY.get(), SecurityEntity.createAttributes().build());
        event.put(EndlessStoreEntities.WATCHER.get(), WatcherEntity.createAttributes().build());
        event.put(EndlessStoreEntities.JACK.get(), JackEntity.createAttributes().build());
        event.put(EndlessStoreEntities.WATER_STRIDER.get(), JackEntity.createAttributes().build());
        event.put(EndlessStoreEntities.EMPLOYEE.get(), JackEntity.createAttributes().build());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(
                        EndlessStoreBlocks.CHAIN_MAIL.get(),
                        RenderType.cutout()
                );
                ItemBlockRenderTypes.setRenderLayer(EndlessStoreFluids.ACID_BLOCK.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(EndlessStoreFluids.WASTE_WATER_BLOCK.get(), RenderType.translucent());
            });
        }

        @SubscribeEvent
        public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(
                    CustomBlockEntities.PLATE_ENTITY.get(),
                    PlateEntityRenderer::new
            );
            event.registerEntityRenderer(
                    EndlessStoreEntities.SECURITY.get(),
                    SecurityRenderer::new
            );
            event.registerEntityRenderer(
                    EndlessStoreEntities.WATCHER.get(),
                    WatcherRenderer::new
            );
            event.registerEntityRenderer(
                    EndlessStoreEntities.JACK.get(),
                    JackRenderer::new
            );
            event.registerEntityRenderer(
                    EndlessStoreEntities.WATER_STRIDER.get(),
                    WaterStriderRenderer::new
            );
            event.registerEntityRenderer(
                    EndlessStoreEntities.EMPLOYEE.get(),
                    EmployeeRenderer::new
            );
        }
    }
}