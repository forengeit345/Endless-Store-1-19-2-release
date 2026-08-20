package net.es.entity.custom;

import net.es.entity.mob.CustomHostileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;

public class WatcherEntity extends CustomHostileEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public WatcherEntity(EntityType<? extends CustomHostileEntity> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return CustomHostileEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 65.0)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .add(Attributes.ATTACK_SPEED, 1.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.21);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.65, false));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public int getBaseExperienceReward() {
        return super.getBaseExperienceReward();
    }

    // --- Анимации ---
    private PlayState predicate(AnimationState<WatcherEntity> state) {
        // Бег, если есть цель и движется
        if (state.isMoving() && !this.swinging && this.getTarget() != null) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.watcher.run"));
            return PlayState.CONTINUE;
        }
        // Idle2 (с атакой? проверяем swinging) - но т.к. урон 0, атака не играется, можно оставить idle
        if (!state.isMoving() && !this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.watcher.idle"));
            return PlayState.CONTINUE;
        }
        // Альтернативная idle2, если когда-то будет атака
        // if (!state.isMoving() && this.swinging) { ... }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // --- Звуки ---
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.15f, 1.0f);
    }

    // --- Игнорирование света при спавне ---
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        float f = difficultyInstance.getSpecialMultiplier();
        this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * f);
        return spawnGroupData;
    }
}