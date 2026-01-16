package net.es.endless_store_mod.entity.custom;

import net.es.endless_store_mod.entity.mob.CustomHostileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;


public class WatcherEntity extends CustomHostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public WatcherEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 65.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.21f);

    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.65D, false));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 15.0F, 1.0F));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public int getXpToDrop() {
        return super.getXpToDrop();
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving() && !this.handSwinging && this.isAttacking()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.watcher.run", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        else if (!tAnimationState.isMoving() && !this.handSwinging) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.watcher.idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        else if (!tAnimationState.isMoving() && !this.handSwinging && this.isAttacking()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.watcher.idle2", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
//        else if (!tAnimationState.isMoving() && !this.handSwinging) {
//            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.watcher.idle", Animation.LoopType.LOOP));
//            return PlayState.CONTINUE;
//        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    public static boolean canSpawnIgnoreLightLevel(EntityType<? extends HostileEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return true;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15f, 1.0f);
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
