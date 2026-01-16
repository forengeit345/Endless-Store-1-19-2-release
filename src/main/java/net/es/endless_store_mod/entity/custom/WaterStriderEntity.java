package net.es.endless_store_mod.entity.custom;

import net.es.endless_store_mod.entity.mob.CustomHostileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;


public class WaterStriderEntity extends CustomHostileEntity implements GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public WaterStriderEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 14.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.45f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.31f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.15D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.65f, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

//    @Override
//    public void travel(Vec3d movement) {
//        if (this.getWorld().getTime() % 20 < 10) { // Движение 0.5 секунд, пауза 0.5 секунд
//            super.travel(movement);
//        } else {
//            this.setVelocity(this.getVelocity().multiply(0.1)); // Остановка
//        }
//    }

    public boolean canWalkOnFluid(FluidState state) {
        return state.isIn(FluidTags.WATER);
    }

    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        this.checkBlockCollision();
        if (!this.firstUpdate && this.fluidHeight.getDouble(FluidTags.WATER) > 10.0) {
            this.onLanding();
        } else {
            super.fall(heightDifference, onGround, state, landedPosition);
        }
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        if (world.getBlockState(pos).getFluidState().isIn(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return !this.firstUpdate && this.fluidHeight.getDouble(FluidTags.WATER) > 0.0 ? Float.NEGATIVE_INFINITY : 0.0F;
        }
    }

    public void tick() {
        super.tick();
        this.updateFloating();
        this.checkBlockCollision();
    }

    private void updateFloating() {
        if (!this.firstUpdate && this.fluidHeight.getDouble(FluidTags.WATER) > 10.0) {
            ShapeContext shapeContext = ShapeContext.of(this);
            if (shapeContext.isAbove(FluidBlock.COLLISION_SHAPE, this.getBlockPos(), true) && !this.getWorld().getFluidState(this.getBlockPos().up()).isIn(FluidTags.WATER)) {
                this.setOnGround(true);
            } else {
                this.setVelocity(this.getVelocity().multiply(0.5).add(0.0, 0.05, 0.0));
            }
        }

    }

    public int getXpToDrop() {
        return super.getXpToDrop();
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving() && !this.handSwinging && this.isAttacking()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.water_strider.run", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        else if (tAnimationState.isMoving() && !this.handSwinging) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.water_strider.walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        else if (!tAnimationState.isMoving() && !this.handSwinging) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.water_strider.idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    public boolean tryAttack(Entity target) {
        if (!super.tryAttack(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity) {
                ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 50), this);
            }

            return true;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private <T extends GeoAnimatable> PlayState attackPredicate(AnimationState<T> tAnimationState) {
        if (this.isAttacking() && this.handSwinging){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.water_strider.attack", Animation.LoopType.PLAY_ONCE));
            tAnimationState.getController().forceAnimationReset();
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackController", 0, this::attackPredicate));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(this.isInLava() ? SoundEvents.ENTITY_STRIDER_STEP_LAVA : SoundEvents.ENTITY_STRIDER_STEP, 1.0F, 1.0F);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }


    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        float f = difficulty.getClampedLocalDifficulty();
        this.setCanPickUpLoot(random.nextFloat() < 0.55F * f);
        return entityData;
    }
}
