package net.es.endless_store_mod.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class CustomHostileEntity extends HostileEntity {
    public CustomHostileEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return false; // Игнорирует солнечный свет
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        // Пропускаем проверку освещения для естественного спавна
        if (spawnReason == SpawnReason.NATURAL) {
            // Проверяем все условия, кроме освещения
            return world.doesNotIntersectEntities(this) &&
                    !world.containsFluid(this.getBoundingBox()) &&
                    world.getBlockState(this.getBlockPos().down()).allowsSpawning(world, this.getBlockPos().down(), this.getType());
        }
        return super.canSpawn(world, spawnReason);
    }
}