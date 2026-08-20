package net.es.entity.custom;

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
import net.minecraft.world.entity.monster.Monster;
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

public class SecurityEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public SecurityEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 35.0)
                .add(Attributes.ATTACK_DAMAGE, 5.5)
                .add(Attributes.ATTACK_SPEED, 1.3)
                .add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.8, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.75, 1));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public int getBaseExperienceReward() {
        return super.getBaseExperienceReward();
    }

    // --- Анимации ---
    private PlayState predicate(AnimationState<SecurityEntity> state) {
        if (state.isMoving() && !this.swinging && this.isAggressive()) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.security.run"));
            return PlayState.CONTINUE;
        }
        if (state.isMoving() && !this.swinging && !this.isAggressive()) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.security.walk"));
            return PlayState.CONTINUE;
        }
        if (!state.isMoving() && !this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.security.idle"));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationState<SecurityEntity> state) {
        if (this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenPlay("animation.security.attack"));
            state.getController().forceAnimationReset();
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllers.add(new AnimationController<>(this, "attackController", 0, this::attackPredicate));
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

    // --- Спавн ---
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        float f = difficultyInstance.getSpecialMultiplier();
        this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * f);
        return spawnGroupData;
    }
}