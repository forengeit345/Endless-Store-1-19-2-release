package net.es.endless_store_mod.entity;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ConfigurableHeightSpawnRestriction {
    private static final Map<String, HeightRule> CUSTOM_MOB_RULES = new HashMap<>();

    static {
        CUSTOM_MOB_RULES.put("endless_store_mod:employee", new HeightRule(59, 65));
        CUSTOM_MOB_RULES.put("endless_store_mod:water_strider", new HeightRule(31, 49));
        CUSTOM_MOB_RULES.put("endless_store_mod:jack", new HeightRule(63, 81));
        CUSTOM_MOB_RULES.put("endless_store_mod:watcher", new HeightRule(63, 74));
    }

    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerWorld world) -> {
            if (entity instanceof MobEntity mob) {
                handleCustomMobSpawn(mob, world);
            }
        });
    }

    private static void handleCustomMobSpawn(MobEntity mob, ServerWorld world) {
        String mobId = getMobId(mob);

        // Проверяем, есть ли правило для этого моба
        HeightRule rule = CUSTOM_MOB_RULES.get(mobId);

        if (rule != null && isNaturalSpawn(mob, world)) {
            double y = mob.getY();

            // Применяем правило только если моб находится вне разрешенной высоты
            if (!rule.isValidHeight(y)) {
                mob.discard();
                logRestrictedSpawn(mobId, y, rule);
            }
        }
        // Для мобов без правил в CUSTOM_MOB_RULES ничего не делаем
    }

    private static boolean isNaturalSpawn(MobEntity mob, ServerWorld world) {
        // Проверяем только естественные причины спавна
        return mob.canSpawn(world, SpawnReason.NATURAL) ||
                mob.canSpawn(world, SpawnReason.SPAWNER) ||
                mob.canSpawn(world, SpawnReason.CHUNK_GENERATION) ||
                mob.canSpawn(world, SpawnReason.STRUCTURE);
    }

    private static String getMobId(MobEntity mob) {
        Identifier id = net.minecraft.registry.Registries.ENTITY_TYPE.getId(mob.getType());
        return id != null ? id.toString() : "unknown";
    }

    private static void logRestrictedSpawn(String mobId, double y, HeightRule rule) {
        System.out.println(String.format(
                "Отменен спавн кастомного моба %s на высоте %.1f (разрешено: %d-%d)",
                mobId, y, rule.minHeight, rule.maxHeight
        ));
    }

    // Вспомогательный класс для хранения правил высоты
    static class HeightRule {
        public final int minHeight;
        public final int maxHeight;

        public HeightRule(int minHeight, int maxHeight) {
            this.minHeight = minHeight;
            this.maxHeight = maxHeight;
        }

        public boolean isValidHeight(double y) {
            return y >= minHeight && y <= maxHeight;
        }
    }

    // Метод для динамического добавления правил (опционально)
    public static void addMobRule(String mobId, int minHeight, int maxHeight) {
        CUSTOM_MOB_RULES.put(mobId, new HeightRule(minHeight, maxHeight));
    }

    // Метод для удаления правил (опционально)
    public static void removeMobRule(String mobId) {
        CUSTOM_MOB_RULES.remove(mobId);
    }
}