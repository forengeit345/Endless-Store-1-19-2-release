package net.es.endless_store_mod.entity;

import net.es.endless_store_mod.EndlessStoreMod;
import net.es.endless_store_mod.entity.custom.EmployeeEntity;
import net.es.endless_store_mod.entity.custom.JackEntity;
import net.es.endless_store_mod.entity.custom.SecurityEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EndlessStoreEntities {
    public static final EntityType<EmployeeEntity> EMPLOYEE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(EndlessStoreMod.MOD_ID, "employee"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EmployeeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 1.8f)).build());
    public static final EntityType<JackEntity> JACK = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(EndlessStoreMod.MOD_ID, "jack"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, JackEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 1.1f)).build());
    public static final EntityType<SecurityEntity> SECURITY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(EndlessStoreMod.MOD_ID, "security"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SecurityEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 1.8f)).build());
}
