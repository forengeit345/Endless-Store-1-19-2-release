package net.es.neoforge;

import net.es.EndlessStoreMod;
import net.es.block.EndlessStoreBlocks;
import net.es.block.custom.*;
import net.es.block.entity.*;
import net.es.entity.ConfigurableHeightSpawnRestriction;
import net.es.entity.EndlessStoreEntities;
import net.es.entity.custom.*;
import net.es.fluid.CustomFluidBlock;
import net.es.fluid.EndlessStoreFluids;
import net.es.item.CustomBucketItem;
import net.es.item.EndlessStoreItems;
import net.es.neoforge.fluid.*;
import net.es.registry.EndlessStoreFeatures;
import net.es.registry.EndlessStoreFuel;
import net.es.screen.DimensionEnterHandler;
import net.es.screen.PlayerDeathHandler;
import net.es.world.dimension.EndlessStoreDimension;
import net.es.world.features.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod(EndlessStoreMod.MOD_ID)
public class EndlessStoreModNeoForge {

    // DeferredRegister'ы (статик)
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, EndlessStoreMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, EndlessStoreMod.MOD_ID);
    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndlessStoreMod.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, EndlessStoreMod.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, EndlessStoreMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, EndlessStoreMod.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, EndlessStoreMod.MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, EndlessStoreMod.MOD_ID);

    public static final Supplier<Feature<NoneFeatureConfiguration>> HILLS_OF_BOXES =
            FEATURES.register("hills_of_boxes", HillsOfBoxes::new);
    public static final Supplier<Feature<NoneFeatureConfiguration>> SIN_WAVE_HILL_FEATURE =
            FEATURES.register("sin_wave_hill", SinWaveHillFeature::new);
    public static final Supplier<Feature<NoneFeatureConfiguration>> TOXIC_LAKE =
            FEATURES.register("toxic_lake", ToxicLake::new);
    public static final Supplier<Feature<NoneFeatureConfiguration>> TRASH_LANDFILL =
            FEATURES.register("trash_landfill", TrashLandfill::new);
    public static final Supplier<Feature<NoneFeatureConfiguration>> CHAIN_MAIL_TREES =
            FEATURES.register("chain_mail_trees", ChainMailTrees::new);
    public static final Supplier<Feature<NoneFeatureConfiguration>> ABANDONED_HILLS =
            FEATURES.register("abandoned_hills", AbandonedHills::new);



    // Карты для блоков и предметов
    public static final Map<String, Supplier<Block>> REGISTERED_BLOCKS = new LinkedHashMap<>();
    public static final Map<String, Supplier<Item>> ITEM_SUPPLIERS = new LinkedHashMap<>();

    // БлокЭнтити типы (статик)
    public static final Supplier<BlockEntityType<BedsideTableEntity>> BEDSIDE_TABLE_TYPE =
            BLOCK_ENTITIES.register("bedside_table",
                    () -> BlockEntityType.Builder.of(
                            BedsideTableEntity::new,
                            BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.BEDSIDE_TABLE))
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<CardboardBoxHugeTapeEntity>> CARDBOARD_BOX_HUGE_TAPE_TYPE =
            BLOCK_ENTITIES.register("cardboard_box_huge_tape",
                    () -> BlockEntityType.Builder.of(
                            CardboardBoxHugeTapeEntity::new,
                            BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CARDBOARD_BOX_HUGE_TAPE))
                    ).build(null)
            );
    public static final Supplier<BlockEntityType<CustomFurnaceBlockEntity>> CONCRETE_FURNACE_TYPE =
            BLOCK_ENTITIES.register("concrete_furnace",
                    () -> BlockEntityType.Builder.of(
                            CustomFurnaceBlockEntity::new,
                            BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.CONCRETE_FURNACE))
                    ).build(null)
            );
    public static final Supplier<BlockEntityType<OldSafeEntity>> OLD_SAFE_TYPE =
            BLOCK_ENTITIES.register("old_safe",
                    () -> BlockEntityType.Builder.of(
                            OldSafeEntity::new,
                            BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(EndlessStoreMod.MOD_ID, EndlessStoreBlocks.OLD_SAFE))
                    ).build(null)
            );
    public static final Supplier<EntityType<PlateEntity>> PLATE =
            ENTITY_TYPES.register("plate",
                    () -> EntityType.Builder.of(
                                    (EntityType.EntityFactory<PlateEntity>) PlateEntity::new,
                                    MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(10)
                            .updateInterval(1)
                            .build("plate")
            );
//    Мобы
    public static final Supplier<EntityType<EmployeeEntity>> EMPLOYEE =
            ENTITY_TYPES.register("employee",
                    () -> EntityType.Builder.of(EmployeeEntity::new, MobCategory.MONSTER)
                            .sized(0.8f, 1.85f)
                            .build("employee")
            );
    public static final Supplier<EntityType<JackEntity>> JACK =
            ENTITY_TYPES.register("jack",
                    () -> EntityType.Builder.of(JackEntity::new, MobCategory.MONSTER)
                            .sized(0.4f, 1.1f)
                            .build("jack")
            );
    public static final Supplier<EntityType<SecurityEntity>> SECURITY =
            ENTITY_TYPES.register("security",
                    () -> EntityType.Builder.of(SecurityEntity::new, MobCategory.MONSTER)
                            .sized(0.8f, 1.85f)
                            .build("security")
            );
    public static final Supplier<EntityType<WatcherEntity>> WATCHER =
            ENTITY_TYPES.register("watcher",
                    () -> EntityType.Builder.of(WatcherEntity::new, MobCategory.MONSTER)
                            .sized(0.85f, 3.25f)
                            .build("watcher")
            );
    public static final Supplier<EntityType<WaterStriderEntity>> WATER_STRIDER =
            ENTITY_TYPES.register("water_strider",
                    () -> EntityType.Builder.of(WaterStriderEntity::new, MobCategory.MONSTER)
                            .sized(0.8f, 0.7f)
                            .build("water_strider")
            );

    // Статическая инициализация жидкостей (выполняется один раз)
    static {
        // Присваиваем типы BlockEntity
        BedsideTable.TYPE = BEDSIDE_TABLE_TYPE;
        CardboardBoxHugeTape.TYPE = CARDBOARD_BOX_HUGE_TAPE_TYPE;
        CustomFurnaceBlock.TYPE = CONCRETE_FURNACE_TYPE;
        OldSafe.TYPE = OLD_SAFE_TYPE;

        EndlessStoreFeatures.HILLS_OF_BOXES = HILLS_OF_BOXES;
        EndlessStoreFeatures.SIN_WAVE_HILL_FEATURE = SIN_WAVE_HILL_FEATURE;
        EndlessStoreFeatures.TOXIC_LAKE = TOXIC_LAKE;
        EndlessStoreFeatures.TRASH_LANDFILL = TRASH_LANDFILL;
        EndlessStoreFeatures.CHAIN_MAIL_TREES = CHAIN_MAIL_TREES;
        EndlessStoreFeatures.ABANDONED_HILLS = ABANDONED_HILLS;

        EndlessStoreEntities.EMPLOYEE = EMPLOYEE;
        EndlessStoreEntities.JACK = JACK;
        EndlessStoreEntities.SECURITY = SECURITY;
        EndlessStoreEntities.WATCHER = WATCHER;
        EndlessStoreEntities.WATER_STRIDER = WATER_STRIDER;

        EndlessStoreEntities.PLATE = PLATE;

        EndlessStoreFluidsNeoForge.ACID_TYPE = FLUID_TYPES.register("acid_type", AcidFluidType::new);
        EndlessStoreFluidsNeoForge.WASTE_WATER_TYPE = FLUID_TYPES.register("waste_water_type", WasteWaterFluidType::new);

        // Регистрируем жидкости (используем NeoForge-подклассы)
        Supplier<Fluid> acidStill = FLUIDS.register("acid", AcidFluidNeoForge.Still::new);
        Supplier<Fluid> acidFlowing = FLUIDS.register("flowing_acid", AcidFluidNeoForge.Flowing::new);
        EndlessStoreFluids.ACID = acidStill;
        EndlessStoreFluids.FLOWING_ACID = () -> (FlowingFluid) acidFlowing.get();

        Supplier<Fluid> wasteStill = FLUIDS.register("waste_water", WasteWaterFluidNeoForge.Still::new);
        Supplier<Fluid> wasteFlowing = FLUIDS.register("flowing_waste_water", WasteWaterFluidNeoForge.Flowing::new);
        EndlessStoreFluids.WASTE_WATER = wasteStill;
        EndlessStoreFluids.FLOWING_WASTE_WATER = () -> (FlowingFluid) wasteFlowing.get();

        // Блоки жидкостей
        Supplier<Block> acidBlock = BLOCKS.register("acid_block",
                () -> new CustomFluidBlock(EndlessStoreFluids.FLOWING_ACID.get(), Block.Properties.ofFullCopy(Blocks.WATER).replaceable()));
        Supplier<Block> wasteBlock = BLOCKS.register("waste_water_block",
                () -> new LiquidBlock(EndlessStoreFluids.FLOWING_WASTE_WATER.get(), Block.Properties.ofFullCopy(Blocks.WATER).replaceable()));
        EndlessStoreFluids.ACID_BLOCK = acidBlock;
        EndlessStoreFluids.WASTE_WATER_BLOCK = wasteBlock;

        // Вёдра
        EndlessStoreFluids.PLASTIC_ACID_BUCKET = ITEMS.register("plastic_acid_bucket",
                () -> new CustomBucketItem(EndlessStoreFluids.ACID.get(), new Item.Properties().stacksTo(1)));
        EndlessStoreFluids.PLASTIC_WASTE_WATER_BUCKET = ITEMS.register("plastic_waste_water_bucket",
                () -> new CustomBucketItem(EndlessStoreFluids.WASTE_WATER.get(), new Item.Properties().stacksTo(1)));

        ITEM_SUPPLIERS.put("plastic_acid_bucket", EndlessStoreFluids.PLASTIC_ACID_BUCKET);
        ITEM_SUPPLIERS.put("plastic_waste_water_bucket", EndlessStoreFluids.PLASTIC_WASTE_WATER_BUCKET);
    }

    public EndlessStoreModNeoForge(IEventBus modEventBus) {
        // 1. Регистрируем блоки
        EndlessStoreBlocks.registerBlocks(
                (name, blockFactory) -> {
                    Supplier<Block> blockSupplier = BLOCKS.register(name, blockFactory);
                    REGISTERED_BLOCKS.put(name, blockSupplier);
                    return blockSupplier;
                },
                (name, itemFactory) -> {
                    Supplier<Item> itemSupplier = ITEMS.register(name, itemFactory);
                    ITEM_SUPPLIERS.put(name, itemSupplier);
                }
        );

        // 2. Регистрируем обычные предметы
        EndlessStoreItems.registerItems((name, factory) -> {
            Supplier<Item> supplier = ITEMS.register(name, factory);
            ITEM_SUPPLIERS.put(name, supplier);
            if (name.equals(EndlessStoreItems.PLASTIC_BUCKET)) {
                EndlessStoreItems.PLASTIC_BUCKET_ITEM = supplier;
            }
        });

        // 3. Творческая вкладка
        TABS.register("endless_store_tab", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .icon(() -> new ItemStack(ITEM_SUPPLIERS.get(EndlessStoreItems.WOODEN_BOARD).get()))
                .title(Component.translatable("itemGroup." + EndlessStoreMod.MOD_ID + ".endless_store_tab"))
                .displayItems((params, output) -> {
                    ITEM_SUPPLIERS.values().forEach(s -> output.accept(s.get()));
                })
                .build());

//        Мобы?
        NeoForge.EVENT_BUS.addListener(EntityJoinLevelEvent.class, event -> {
            if (event.getEntity() instanceof Mob mob) {
                if (!ConfigurableHeightSpawnRestriction.checkSpawn(mob, mob.getY())) {
                    event.setCanceled(true);
                }
            }
        });

        modEventBus.addListener(EntityAttributeCreationEvent.class, event -> {
            event.put(EndlessStoreEntities.EMPLOYEE.get(), EmployeeEntity.createAttributes().build());
        });
        modEventBus.addListener(EntityAttributeCreationEvent.class, event -> {
            event.put(EndlessStoreEntities.JACK.get(), JackEntity.createAttributes().build());
        });
        modEventBus.addListener(EntityAttributeCreationEvent.class, event -> {
            event.put(EndlessStoreEntities.SECURITY.get(), SecurityEntity.createAttributes().build());
        });
        modEventBus.addListener(EntityAttributeCreationEvent.class, event -> {
            event.put(EndlessStoreEntities.WATCHER.get(), WatcherEntity.createAttributes().build());
        });
        modEventBus.addListener(EntityAttributeCreationEvent.class, event -> {
            event.put(EndlessStoreEntities.WATER_STRIDER.get(), WaterStriderEntity.createAttributes().build());
        });

        //        Регистрация измерения
        NeoForge.EVENT_BUS.addListener(ServerAboutToStartEvent.class, event -> {
            Registry<LevelStem> registry = event.getServer().registryAccess().registryOrThrow(Registries.LEVEL_STEM);
            if (!registry.containsKey(EndlessStoreDimension.ESDIM_KEY)) {
                // Авторегистрация из JSON – ничего не делаем.
            }
        });

        NeoForge.EVENT_BUS.addListener(PlayerEvent.Clone.class, event -> {
            if (event.isWasDeath()
                    && event.getOriginal() instanceof ServerPlayer oldPlayer
                    && event.getEntity() instanceof ServerPlayer newPlayer) {
                PlayerDeathHandler.handleRespawn(oldPlayer, newPlayer);
            }
        });

        NeoForge.EVENT_BUS.addListener(ServerTickEvent.Post.class, event -> {
            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                DimensionEnterHandler.onPlayerTick(player);
            }
        });

        NeoForge.EVENT_BUS.addListener(FurnaceFuelBurnTimeEvent.class, event -> {
            EndlessStoreFuel.FUEL_VALUES.forEach((name, burnTime) -> {
                Supplier<Item> supplier = ITEM_SUPPLIERS.get(name);
                if (supplier != null && event.getItemStack().getItem() == supplier.get()) {
                    event.setBurnTime(burnTime);
                }
            });
        });

        // 4. Отправляем регистры в шину
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        FLUID_TYPES.register(modEventBus);
        TABS.register(modEventBus);
        FEATURES.register(modEventBus);
    }
}