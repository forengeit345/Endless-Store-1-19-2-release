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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
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

public class EmployeeEntity extends CustomHostileEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public EmployeeEntity(EntityType<? extends CustomHostileEntity> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return CustomHostileEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ATTACK_SPEED, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new EmployeeAttackGoal(this) {});
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.85, 1));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new EmployeeTargetGoal(this, Player.class) {});
    }

    @Override
    public int getBaseExperienceReward() {
        return super.getBaseExperienceReward();
    }

    private PlayState predicate(AnimationState<EmployeeEntity> state) {
        if (state.isMoving() && !this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.employee.walk"));
            return PlayState.CONTINUE;
        }
        if (!state.isMoving() && !this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.employee.idle"));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private PlayState attackPredicate(AnimationState<EmployeeEntity> state) {
        if (this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenPlay("animation.employee.attack"));
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


    static class EmployeeAttackGoal extends MeleeAttackGoal {
        public EmployeeAttackGoal(EmployeeEntity employee) {
            super(employee, (double)1.0F, true);
        }

        public boolean canUse() {
            return super.canUse() && !this.mob.isVehicle();
        }

        public boolean canContinueToUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget((LivingEntity)null);
                return false;
            } else {
                return super.canContinueToUse();
            }
        }
    }

    static class EmployeeTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public EmployeeTargetGoal(EmployeeEntity arg, Class<T> class_) {
            super(arg, class_, true);
        }

        public boolean canUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            return !(f >= 0.5F) && super.canUse();
        }
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
