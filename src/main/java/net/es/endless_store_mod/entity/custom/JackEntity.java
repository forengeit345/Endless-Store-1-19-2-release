package net.es.endless_store_mod.entity.custom;

import net.es.endless_store_mod.entity.mob.CustomHostileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class JackEntity extends CustomHostileEntity implements GeoEntity {
    public float targetStretch;
    public float stretch;
    public float lastStretch;
    private boolean onGroundLastTick;

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public JackEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new JackMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 2.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.45F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimmingGoal(this));
        this.goalSelector.addGoal(2, new FaceTowardTargetGoal(this));
        this.goalSelector.addGoal(3, new RandomLookGoal(this));
        this.goalSelector.addGoal(5, new MoveGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false,
                (livingEntity) -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0));
    }

    @Override
    public int getExperienceReward() {
        if (this.isBaby()) {
            this.xpReward = (int) ((double) this.xpReward * 2.5);
        }
        return super.getExperienceReward();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving() && !this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().then("animation.jack.jump", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        // TODO: починить анимации джэка чтобы он перестал вертеть головой
        else if (!state.isMoving() && !this.swinging && !(this.isAggressive())) {
            state.getController().setAnimation(RawAnimation.begin().then("animation.jack.idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        } else {
            return PlayState.STOP;
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void tick() {
        this.stretch += (this.targetStretch - this.stretch) * 0.5F;
        this.lastStretch = this.stretch;
        super.tick();
        if (this.onGround() && !this.onGroundLastTick) {
            this.targetStretch = -0.5F;
        } else if (!this.onGround() && this.onGroundLastTick) {
            this.targetStretch = 1.0F;
        }

        this.onGroundLastTick = this.onGround();
        this.updateStretch();
    }

    protected void updateStretch() {
        this.targetStretch *= 0.6F;
    }

    protected int getTicksUntilNextJump() {
        return this.random.nextInt(40) + 10;
    }

    @Override
    public void refreshDimensions() {
        double d = this.getX();
        double e = this.getY();
        double f = this.getZ();
        super.refreshDimensions();
        this.setPos(d, e, f);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        this.refreshDimensions();
        this.setYRot(this.yHeadRot);
        this.yBodyRot = this.yHeadRot;
        if (this.isInWater() && this.random.nextInt(20) == 0) {
            this.doWaterSplashEffect();
        }
        super.onSyncedDataUpdated(data);
    }

    @Override
    public void push(Entity entity) {
        super.push(entity);
        if (entity instanceof IronGolem && this.canAttack()) {
            this.dealDamage((LivingEntity) entity);
        }
    }

    @Override
    public void playerTouch(Player player) {
        if (this.canAttack()) {
            this.dealDamage(player);
        }
    }

    protected void dealDamage(LivingEntity target) {
        if (this.isAlive()) {
            if (this.distanceToSqr(target) < 0.8 * 0.8 && this.hasLineOfSight(target) && target.hurt(this.damageSources().mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE))) {
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.doEnchantDamageEffects(this, target);
            }
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 0.625F * dimensions.height;
    }

    protected boolean canAttack() {
        return this.isEffectiveAi();
    }

    @Override
    protected void jumpFromGround() {
        Vec3 vec3d = this.getDeltaMovement();
        this.setDeltaMovement(vec3d.x, this.getJumpPower(), vec3d.z);
        this.hasImpulse = true;
    }

    private static class JackMoveControl extends MoveControl {
        private float targetYaw;
        private int ticksUntilJump;
        private final JackEntity jack;
        private boolean jumpOften;

        public JackMoveControl(JackEntity jack) {
            super(jack);
            this.jack = jack;
            this.targetYaw = 180.0F * jack.getYRot() / 3.1415927F;
        }

        public void look(float targetYaw, boolean jumpOften) {
            this.targetYaw = targetYaw;
            this.jumpOften = jumpOften;
        }

        public void setWantedMovement(double speed) {
            this.speedModifier = speed;
            this.operation = Operation.MOVE_TO;
        }

        @Override
        public void tick() {
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.targetYaw, 90.0F));
            this.mob.yHeadRot = this.mob.getYRot();
            this.mob.yBodyRot = this.mob.getYRot();
            if (this.operation != Operation.MOVE_TO) {
                this.mob.setZza(0.0F);
            } else {
                this.operation = Operation.WAIT;
                if (this.mob.onGround()) {
                    this.mob.setSpeed((float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    if (this.ticksUntilJump-- <= 0) {
                        this.ticksUntilJump = this.jack.getTicksUntilNextJump();
                        if (this.jumpOften) {
                            this.ticksUntilJump /= 3;
                        }
                        this.jack.getJumpControl().jump();
                    } else {
                        this.jack.xxa = 0.0F;
                        this.jack.zza = 0.0F;
                        this.mob.setSpeed(0.0F);
                    }
                } else {
                    this.mob.setSpeed((float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                }
            }
        }
    }

    static class SwimmingGoal extends Goal {
        private final JackEntity jack;

        public SwimmingGoal(JackEntity jack) {
            this.jack = jack;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
            jack.getNavigation().setCanFloat(true);
        }

        @Override
        public boolean canUse() {
            return (this.jack.isInWater() || this.jack.isInLava()) && this.jack.getMoveControl() instanceof JackMoveControl;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            if (this.jack.getRandom().nextFloat() < 0.8F) {
                this.jack.getJumpControl().jump();
            }
            ((JackMoveControl) this.jack.getMoveControl()).setWantedMovement(1.2);
        }
    }

    static class FaceTowardTargetGoal extends Goal {
        private final JackEntity jack;
        private int ticksLeft;

        public FaceTowardTargetGoal(JackEntity jack) {
            this.jack = jack;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.jack.getTarget();
            if (target == null) {
                return false;
            }
            return this.jack.canAttack(target) && this.jack.getMoveControl() instanceof JackMoveControl;
        }

        @Override
        public void start() {
            this.ticksLeft = adjustedTickDelay(300);
            super.start();
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = this.jack.getTarget();
            if (target == null) return false;
            if (!this.jack.canAttack(target)) return false;
            return --this.ticksLeft > 0;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity target = this.jack.getTarget();
            if (target != null) {
                // Поворачиваем голову к цели (для визуала)
                this.jack.getLookControl().setLookAt(target, 10.0F, 10.0F);

                // Вычисляем горизонтальный угол от моба к цели
                double dx = target.getX() - this.jack.getX();
                double dz = target.getZ() - this.jack.getZ();
                float yawToTarget = (float) (Math.atan2(dz, dx) * (180D / Math.PI)) - 90.0F;

                // Передаём правильное направление в MoveControl
                ((JackMoveControl) this.jack.getMoveControl()).look(yawToTarget, this.jack.canAttack());
            }
        }
    }

    static class RandomLookGoal extends Goal {
        private final JackEntity jack;
        private float targetYaw;
        private int timer;

        public RandomLookGoal(JackEntity jack) {
            this.jack = jack;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return this.jack.getTarget() == null && (this.jack.onGround() || this.jack.isInWater() || this.jack.isInLava() || this.jack.hasEffect(MobEffects.LEVITATION)) && this.jack.getMoveControl() instanceof JackMoveControl;
        }

        @Override
        public void tick() {
            if (--this.timer <= 0) {
                this.timer = this.adjustedTickDelay(40 + this.jack.getRandom().nextInt(60));
                this.targetYaw = this.jack.getRandom().nextInt(360);
            }
            ((JackMoveControl) this.jack.getMoveControl()).look(this.targetYaw, false);
        }
    }

    static class MoveGoal extends Goal {
        private final JackEntity jack;

        public MoveGoal(JackEntity jack) {
            this.jack = jack;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return !this.jack.isPassenger();
        }

        @Override
        public void tick() {
            ((JackMoveControl) this.jack.getMoveControl()).setWantedMovement(1.0);
        }
    }

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

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("wasOnGround", this.onGroundLastTick);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.onGroundLastTick = nbt.getBoolean("wasOnGround");
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        float f = difficulty.getSpecialMultiplier();
        this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * f);
        return spawnData;
    }
}