package net.es.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;

public class WaterStriderEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public WaterStriderEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_SPEED, 1.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.45)
                .add(Attributes.MOVEMENT_SPEED, 0.31);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.15, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.65, 1));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean canStandOnFluid(FluidState fluidState) {
        return fluidState.is(FluidTags.WATER);
    }

    protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {
        this.checkInsideBlocks();
        if (this.isInWater()) {
            this.resetFallDistance();
        } else {
            super.checkFallDamage(d, bl, blockState, blockPos);
        }
    }

    private void floatStrider() {
        if (this.isInWater()) {
            CollisionContext collisionContext = CollisionContext.of(this);
            if (collisionContext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level().getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                this.setOnGround(true);
            }
        }
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        if (level.getBlockState(pos).getFluidState().is(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return this.isInWater() ? Float.NEGATIVE_INFINITY : 0.0F;
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.floatStrider();
    }

//    private void updateFloating() {
//        if (!this.firstTick && this.fluidHeight.getDouble(FluidTags.WATER) > 10.0) {
//            CollisionContext context = CollisionContext.of(this);
//            BlockPos pos = this.blockPosition();
//            if (context.isAbove(net.minecraft.world.level.block.LiquidBlock.SHAPE, pos, true)
//                    && !this.level().getFluidState(pos.above()).is(FluidTags.WATER)) {
//                this.setOnGround(true);
//            } else {
//                this.setDeltaMovement(this.getDeltaMovement().scale(0.5).add(0.0, 0.05, 0.0));
//            }
//        }
//    }

    @Override
    public int getBaseExperienceReward() {
        return super.getBaseExperienceReward();
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (!super.doHurtTarget(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 0), this);
            }
            return true;
        }
    }

    // --- Анимации ---
    private PlayState predicate(AnimationState<WaterStriderEntity> state) {
        if (state.isMoving() && !this.swinging && this.getTarget() != null) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.water_strider.run"));
            return PlayState.CONTINUE;
        } else if (state.isMoving() && !this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.water_strider.walk"));
            return PlayState.CONTINUE;
        } else if (!state.isMoving() && !this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.water_strider.idle"));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationState<WaterStriderEntity> state) {
        if (this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenPlay("animation.water_strider.attack"));
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
        this.playSound(this.isInLava() ? SoundEvents.STRIDER_STEP_LAVA : SoundEvents.STRIDER_STEP, 1.0F, 1.0F);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        float f = difficultyInstance.getSpecialMultiplier();
        this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * f);
        return spawnGroupData;
    }
}