package net.es.fabric;

import net.es.EndlessStoreMod;
import net.es.block.EndlessStoreBlocks;
import net.es.block.custom.BedsideTable;
import net.es.block.custom.CardboardBoxHugeTape;
import net.es.block.custom.CustomFurnaceBlock;
import net.es.block.custom.OldSafe;
import net.es.block.entity.BedsideTableEntity;
import net.es.block.entity.CardboardBoxHugeTapeEntity;
import net.es.block.entity.CustomFurnaceBlockEntity;
import net.es.block.entity.OldSafeEntity;
import net.es.entity.ConfigurableHeightSpawnRestriction;
import net.es.entity.EndlessStoreEntities;
import net.es.entity.custom.*;
import net.es.fluid.CustomFluidBlock;
import net.es.fluid.CustomWaterFluidBlock;
import net.es.fluid.EndlessStoreFluids;
import net.es.item.EndlessStoreItemGroup;
import net.es.item.EndlessStoreItems;
import net.es.registry.EndlessStoreFeatures;
import net.es.registry.EndlessStoreFuel;
import net.es.screen.DimensionEnterHandler;
import net.es.screen.PlayerDeathHandler;
import net.es.world.dimension.EndlessStoreDimension;
import net.es.world.features.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import java.util.LinkedHashMap;
import java.util.Map;

public class EndlessStoreModFabric implements ModInitializer {
    private static final Map<String, Item> ITEMS = new LinkedHashMap<>();

    @Override
    public void onInitialize() {

        ServerTickEvents.START_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                DimensionEnterHandler.onPlayerTick(player);
            }
        });

        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            PlayerDeathHandler.handleRespawn(oldPlayer, newPlayer);
        });

//        Фичи для генерации мира
        Feature<NoneFeatureConfiguration> hillsFeature = new HillsOfBoxes();
        Registry.register(
                BuiltInRegistries.FEATURE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "hills_of_boxes"),
                hillsFeature
        );
        EndlessStoreFeatures.HILLS_OF_BOXES = () -> hillsFeature;

        Feature<NoneFeatureConfiguration> sinWaveHillFeature = new SinWaveHillFeature();
        Registry.register(
                BuiltInRegistries.FEATURE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "sin_wave_hill"),
                sinWaveHillFeature
        );
        EndlessStoreFeatures.SIN_WAVE_HILL_FEATURE = () -> sinWaveHillFeature;

        Feature<NoneFeatureConfiguration> toxicLakeFeature = new ToxicLake();
        Registry.register(
                BuiltInRegistries.FEATURE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "toxic_lake"),
                toxicLakeFeature
        );
        EndlessStoreFeatures.TOXIC_LAKE = () -> toxicLakeFeature;

        Feature<NoneFeatureConfiguration> trashLandfillFeature = new TrashLandfill();
        Registry.register(
                BuiltInRegistries.FEATURE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "trash_landfill"),
                trashLandfillFeature
        );
        EndlessStoreFeatures.TRASH_LANDFILL = () -> trashLandfillFeature;

        Feature<NoneFeatureConfiguration> chainMailTreesFeature = new ChainMailTrees();
        Registry.register(
                BuiltInRegistries.FEATURE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "chain_mail_trees"),
                chainMailTreesFeature
        );
        EndlessStoreFeatures.CHAIN_MAIL_TREES = () -> chainMailTreesFeature;

        Feature<NoneFeatureConfiguration> abandonedHillsFeature = new AbandonedHills();
        Registry.register(
                BuiltInRegistries.FEATURE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "abandoned_hills"),
                abandonedHillsFeature
        );
        EndlessStoreFeatures.ABANDONED_HILLS = () -> abandonedHillsFeature;

//        Работничек

        EntityType<EmployeeEntity> employeeType = EntityType.Builder.of(
                        EmployeeEntity::new,
                        MobCategory.MONSTER)
                .sized(0.8f, 1.85f)
                .build("employee");

        Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "employee"),
                employeeType
        );
        EndlessStoreEntities.EMPLOYEE = () -> employeeType;

//        Джэк

        EntityType<JackEntity> jackType = EntityType.Builder.of(
                        JackEntity::new,
                        MobCategory.MONSTER)
                .sized(0.4f, 1.1f)
                .build("jack");

        Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "jack"),
                jackType
        );
        EndlessStoreEntities.JACK = () -> jackType;

//        Охранник

        EntityType<SecurityEntity> securityType = EntityType.Builder.of(
                        SecurityEntity::new,
                        MobCategory.MONSTER)
                .sized(0.8f, 1.85f)
                .build("security");

        Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "security"),
                securityType
        );
        EndlessStoreEntities.SECURITY = () -> securityType;

        //        Смотритель

        EntityType<WatcherEntity> watcherType = EntityType.Builder.of(
                        WatcherEntity::new,
                        MobCategory.MONSTER)
                .sized(0.85f, 3.25f)
                .build("watcher");

        Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "watcher"),
                watcherType
        );
        EndlessStoreEntities.WATCHER = () -> watcherType;

        //        Водомерка

        EntityType<WaterStriderEntity> waterStriderType = EntityType.Builder.of(
                        WaterStriderEntity::new,
                        MobCategory.MONSTER)
                .sized(0.8f, 0.7f)
                .build("water_strider");

        Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "water_strider"),
                waterStriderType
        );
        EndlessStoreEntities.WATER_STRIDER = () -> waterStriderType;

        // 1. Сначала регистрируем БЛОКИ (они попадут в карту первыми)
        EndlessStoreBlocks.registerBlocks(
                (name, blockFactory) -> {
                    Block block = blockFactory.get();
                    Registry.register(
                            BuiltInRegistries.BLOCK,
                            ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name),
                            block
                    );
                    return () -> block;
                },
                (name, itemFactory) -> {
                    Item item = itemFactory.get();
                    Registry.register(
                            BuiltInRegistries.ITEM,
                            ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name),
                            item
                    );
                    ITEMS.put(name, item); // BlockItem вставляется в карту
                }
        );

        // 2. Затем регистрируем ОБЫЧНЫЕ ПРЕДМЕТЫ (добавляются после блоков)
        EndlessStoreItems.registerItems((name, factory) -> {
            Item item = factory.get();
            Registry.register(
                    BuiltInRegistries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name),
                    item
            );
            ITEMS.put(name, item);

            // Если это пластиковое ведро, сохраняем поставщик
            if (name.equals(EndlessStoreItems.PLASTIC_BUCKET)) {
                EndlessStoreItems.PLASTIC_BUCKET_ITEM = () -> item;
            }
        });

//        TODO: БлокЭнтити
        BlockEntityType<BedsideTableEntity> bedsideType = BlockEntityType.Builder.of(
                BedsideTableEntity::new,
                BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.BEDSIDE_TABLE)
                )
        ).build(null);

        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "bedside_table"), bedsideType);
        BedsideTable.TYPE = () -> bedsideType;

//        2

        BlockEntityType<CardboardBoxHugeTapeEntity> cardboardBoxHugeTapeEntityBlockEntityType = BlockEntityType.Builder.of(
                CardboardBoxHugeTapeEntity::new,
                BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX_HUGE_TAPE)
                )
        ).build(null);

        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "cardboard_box_huge_tape"), cardboardBoxHugeTapeEntityBlockEntityType);
        CardboardBoxHugeTape.TYPE = () -> cardboardBoxHugeTapeEntityBlockEntityType;

//        3

        BlockEntityType<CustomFurnaceBlockEntity> furnaceType = BlockEntityType.Builder.of(
                CustomFurnaceBlockEntity::new,
                BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CONCRETE_FURNACE)
                )
        ).build(null);

        Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "concrete_furnace"),
                furnaceType
        );

        CustomFurnaceBlock.TYPE = () -> furnaceType;

//        4

        EntityType<PlateEntity> plateType = EntityType.Builder.of(
                        (EntityType.EntityFactory<PlateEntity>) PlateEntity::new,
                        MobCategory.MISC)
                .sized(0.5F, 0.5F)
                .clientTrackingRange(10)
                .updateInterval(1)
                .build("plate");

        Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "plate"),
                plateType
        );
        EndlessStoreEntities.PLATE = () -> plateType;

//        5

        BlockEntityType<OldSafeEntity> oldSafeType = BlockEntityType.Builder.of(
                OldSafeEntity::new,
                BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.OLD_SAFE)
                )
        ).build(null);

        Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "old_safe"),
                oldSafeType
        );

        OldSafe.TYPE = () -> oldSafeType;

//        TODO: ИНИТ
        EndlessStoreFluids.init((name, stillFactory, flowingFactory, blockFactory, bucketFactory) -> {
            //            Регистарция мобов
            FabricDefaultAttributeRegistry.register(EndlessStoreEntities.EMPLOYEE.get(), EmployeeEntity.createAttributes());
            FabricDefaultAttributeRegistry.register(EndlessStoreEntities.JACK.get(), JackEntity.createAttributes());
            FabricDefaultAttributeRegistry.register(EndlessStoreEntities.SECURITY.get(), SecurityEntity.createAttributes());
            FabricDefaultAttributeRegistry.register(EndlessStoreEntities.WATCHER.get(), WatcherEntity.createAttributes());
            FabricDefaultAttributeRegistry.register(EndlessStoreEntities.WATER_STRIDER.get(), WaterStriderEntity.createAttributes());

            // Регистрируем стоячую жидкость
            Fluid still = Registry.register(
                    BuiltInRegistries.FLUID,
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name),
                    stillFactory.get()
            );

//            Регистрация высоты мобов
            ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
                if (entity instanceof Mob mob) {
                    // Для загруженных мобов проверяем их текущую высоту
                    if (!ConfigurableHeightSpawnRestriction.checkSpawn(mob, mob.getY())) {
                        mob.discard(); // удаляем, если высота не подходит
                    }
                }
            });

            // Регистрируем текучую жидкость
            FlowingFluid flowing = Registry.register(
                    BuiltInRegistries.FLUID,
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "flowing_" + name),
                    flowingFactory.get()
            );

            // Сразу назначаем поставщики, чтобы blockFactory.get() мог бы их использовать (но мы его не вызываем)
            if (name.equals("acid")) {
                EndlessStoreFluids.ACID = () -> still;
                EndlessStoreFluids.FLOWING_ACID = () -> flowing;
            } else if (name.equals("waste_water")) {
                EndlessStoreFluids.WASTE_WATER = () -> still;
                EndlessStoreFluids.FLOWING_WASTE_WATER = () -> flowing;
            }

            // Создаём блок жидкости напрямую, используя уже полученную текучую жидкость,
            // чтобы избежать обращения к ещё не инициализированному FLOWING_ACID.
            Block block = new CustomFluidBlock(flowing, Block.Properties.ofFullCopy(Blocks.WATER).replaceable());
            Registry.register(
                    BuiltInRegistries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name + "_block"),
                    block
            );

            Block blockWater = new CustomWaterFluidBlock(flowing, Block.Properties.ofFullCopy(Blocks.WATER).replaceable());
            Registry.register(
                    BuiltInRegistries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, name + "_block"),
                    blockWater
            );

            if (name.equals("acid")) {
                EndlessStoreFluids.ACID_BLOCK = () -> block;
            } else if (name.equals("waste_water")) {
                EndlessStoreFluids.WASTE_WATER_BLOCK = () -> blockWater;
            }

            // Ведро (если передано)
            if (bucketFactory != null) {
                Item bucket = bucketFactory.get();
                Registry.register(
                        BuiltInRegistries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "plastic_" + name + "_bucket"),
                        bucket
                );

                if (name.equals("acid")) {
                    EndlessStoreFluids.PLASTIC_ACID_BUCKET = () -> bucket;
                } else if (name.equals("waste_water")) {
                    EndlessStoreFluids.PLASTIC_WASTE_WATER_BUCKET = () -> bucket;
                }

                ITEMS.put("plastic_" + name + "_bucket", bucket);
            }
        });

//        Регистрация измерения
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            Registry<LevelStem> registry = server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
            if (!registry.containsKey(EndlessStoreDimension.ESDIM_KEY)) {
            }
        });

        // Творческая вкладка
        ResourceKey<CreativeModeTab> tabKey = ResourceKey.create(
                Registries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, "endless_store_tab")
        );

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, tabKey, EndlessStoreItemGroup.ENDLESS_STORE_TAB.get());

        ItemGroupEvents.modifyEntriesEvent(tabKey).register(content -> {
            ITEMS.values().forEach(content::accept);
        });

//        6 Топляк
        EndlessStoreFuel.FUEL_VALUES.forEach((name, burnTime) -> {
            Item item = ITEMS.get(name);
            if (item != null) {
                FuelRegistry.INSTANCE.add(item, burnTime);
            }
        });
    }
}