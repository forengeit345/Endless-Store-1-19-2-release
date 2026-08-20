package net.es.entity;

import net.es.entity.custom.*;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class EndlessStoreEntities {
    public static Supplier<EntityType<PlateEntity>> PLATE = () -> {
        throw new IllegalStateException("PlateEntity type not initialized");
    };

    public static Supplier<EntityType<EmployeeEntity>> EMPLOYEE = () -> {
        throw new IllegalStateException("Employee entity not registered");
    };

    public static Supplier<EntityType<JackEntity>> JACK = () -> {
        throw new IllegalStateException("Employee entity not registered");
    };

    public static Supplier<EntityType<SecurityEntity>> SECURITY = () -> {
        throw new IllegalStateException("Employee entity not registered");
    };

    public static Supplier<EntityType<WatcherEntity>> WATCHER = () -> {
        throw new IllegalStateException("Employee entity not registered");
    };

    public static Supplier<EntityType<WaterStriderEntity>> WATER_STRIDER = () -> {
        throw new IllegalStateException("Employee entity not registered");
    };

    public static void init() {}
}