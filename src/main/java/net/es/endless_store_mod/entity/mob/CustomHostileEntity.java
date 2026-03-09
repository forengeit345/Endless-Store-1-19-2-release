package net.es.endless_store_mod.entity.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class CustomHostileEntity extends Monster {
    public CustomHostileEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    protected boolean isAffectedByDaylight() {
        return false;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (spawnReason == MobSpawnType.NATURAL) {
            return level.noCollision(this) &&
                    !level.containsAnyLiquid(this.getBoundingBox()) &&
                    level.getBlockState(this.blockPosition().below()).isValidSpawn(level, this.blockPosition().below(), this.getType());
        }
        return super.checkSpawnRules(level, spawnReason);
    }
}