package net.es.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

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

    /**
     * Проверяет, разрешён ли спавн моба на данной высоте.
     * Вызывается платформенными обработчиками при попытке спавна или загрузке.
     */
    public static boolean checkSpawn(Mob mob, double y) {
        ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
        if (id == null) return true;

        HeightRule rule = CUSTOM_MOB_RULES.get(id.toString());
        if (rule != null && !rule.isValidHeight(y)) {
            logRestrictedSpawn(id.toString(), y, rule);
            return false;
        }
        return true;
    }

    private static void logRestrictedSpawn(String mobId, double y, HeightRule rule) {
        System.out.printf(
                "Отменён спавн кастомного моба %s на высоте %.1f (разрешено: %d-%d)%n",
                mobId, y, rule.minHeight, rule.maxHeight
        );
    }

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
}