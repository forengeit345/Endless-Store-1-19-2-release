package net.es.endless_store_mod.entity;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EndlessStoreEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EndlessStoreMod.MOD_ID);

    public static final RegistryObject<EntityType<SecurityEntity>> SECURITY = ENTITY_TYPES.register("security",
            () -> EntityType.Builder.of(SecurityEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 1.85f)
                    .build(EndlessStoreMod.MOD_ID + ":security"));

    public static final RegistryObject<EntityType<WatcherEntity>> WATCHER = ENTITY_TYPES.register("watcher",
            () -> EntityType.Builder.of(WatcherEntity::new, MobCategory.MONSTER)
                    .sized(0.85f, 3.25f)
                    .build(EndlessStoreMod.MOD_ID + ":watcher"));

    public static final RegistryObject<EntityType<JackEntity>> JACK = ENTITY_TYPES.register("jack",
            () -> EntityType.Builder.of(JackEntity::new, MobCategory.MONSTER)
                    .sized(0.4f, 1.1f)
                    .build(EndlessStoreMod.MOD_ID + ":jack"));

    public static final RegistryObject<EntityType<WaterStriderEntity>> WATER_STRIDER = ENTITY_TYPES.register("water_strider",
            () -> EntityType.Builder.of(WaterStriderEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 0.7f)
                    .build(EndlessStoreMod.MOD_ID + ":water_strider"));

    public static final RegistryObject<EntityType<EmployeeEntity>> EMPLOYEE = ENTITY_TYPES.register("employee",
            () -> EntityType.Builder.of(EmployeeEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 1.85f)
                    .build(EndlessStoreMod.MOD_ID + ":employee"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
