package net.es.entity.custom;

import net.es.entity.mob.CustomHostileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;

import java.util.EnumSet;

public class JackEntity extends CustomHostileEntity implements GeoEntity {
    public float targetStretch;
    public float stretch;
    public float lastStretch;
    private boolean onGroundLastTick;

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public JackEntity(EntityType<? extends CustomHostileEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new JackMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_SPEED, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.45);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new JackSwimmingGoal(this));
        this.goalSelector.addGoal(2, new JackFaceTowardTargetGoal(this));
        this.goalSelector.addGoal(3, new JackRandomLookGoal(this));
        this.goalSelector.addGoal(5, new JackMoveGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false,
                (livingEntity) -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0));
    }

    @Override
    public int getBaseExperienceReward() {
        return super.getBaseExperienceReward();
    }

    // --- Анимации ---
    private PlayState predicate(AnimationState<JackEntity> state) {
        if (state.isMoving() && !this.swinging) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.jack.jump"));
            return PlayState.CONTINUE;
        }
        if (!state.isMoving() && !this.swinging && this.getTarget() == null) {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.jack.idle"));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // --- Логика прыжков и деформации ---
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
        this.decreaseSquish();
    }

    protected void decreaseSquish() {
        this.targetStretch *= 0.6F;
    }

    protected int getJumpDelay() {
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
        if (entity instanceof Player && this.isDealsDamage()) {
            this.dealDamage((LivingEntity) entity);
        }
    }

    @Override
    public void playerTouch(Player player) {
        if (this.isDealsDamage()) {
            this.dealDamage(player);
        }
    }

    protected void dealDamage(LivingEntity livingEntity) {
        if (this.isAlive() && this.isWithinMeleeAttackRange(livingEntity) && this.hasLineOfSight(livingEntity)) {
            DamageSource damageSource = this.damageSources().mobAttack(this);
            if (livingEntity.hurt(damageSource, this.getAttackDamage())) {
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                Level var4 = this.level();
                if (var4 instanceof ServerLevel serverLevel) {
                    EnchantmentHelper.doPostAttackEffects(serverLevel, livingEntity, damageSource);
                }
            }
        }

    }

    protected boolean isDealsDamage() {
        return this.isEffectiveAi();
    }

    protected float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    // --- Сохранение ---
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("wasOnGround", this.onGroundLastTick);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.onGroundLastTick = tag.getBoolean("wasOnGround");
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        float f = difficultyInstance.getSpecialMultiplier();
        this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * f);
        return spawnGroupData;
    }

    // --- Звуки ---
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    @NotNull
    @Override
    protected  SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.SKELETON_HURT;
    }

    @NotNull
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.15f, 1.0f);
    }

    // --- Внутренние классы управления и целей ---

    static class JackMoveControl extends MoveControl {
        private float targetYaw;
        private int ticksUntilJump;
        private final JackEntity jack;
        private boolean jumpOften;

        public JackMoveControl(JackEntity jack) {
            super(jack);
            this.jack = jack;
            this.targetYaw = 180.0F * jack.getYRot() / (float)Math.PI;
        }

        public void setDirection(float targetYaw, boolean jumpOften) {
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
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    if (this.ticksUntilJump-- <= 0) {
                        this.ticksUntilJump = this.jack.getJumpDelay();
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
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                }
            }
        }
    }

    static class JackSwimmingGoal extends Goal {
        private final JackEntity jack;

        public JackSwimmingGoal(JackEntity jack) {
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
            MoveControl control = this.jack.getMoveControl();
            if (control instanceof JackMoveControl moveControl) {
                moveControl.setWantedMovement(1.2);
            }
        }
    }

    static class JackFaceTowardTargetGoal extends Goal {
        private final JackEntity jack;
        private int ticksLeft;

        public JackFaceTowardTargetGoal(JackEntity jack) {
            this.jack = jack;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingEntity = this.jack.getTarget();
            if (livingEntity == null) {
                return false;
            }
            return this.jack.canAttack(livingEntity) && this.jack.getMoveControl() instanceof JackMoveControl;
        }

        @Override
        public void start() {
            this.ticksLeft = reducedTickDelay(300);
            super.start();
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity livingEntity = this.jack.getTarget();
            if (livingEntity == null) {
                return false;
            }
            if (!this.jack.canAttack(livingEntity)) {
                return false;
            }
            return --this.ticksLeft > 0;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.jack.getTarget();
            if (livingEntity != null) {
                this.jack.lookAt(livingEntity, 10.0F, 10.0F);
            }
            MoveControl control = this.jack.getMoveControl();
            if (control instanceof JackMoveControl moveControl) {
                moveControl.setDirection(this.jack.getYRot(), this.jack.isDealsDamage());
            }
        }
    }

    static class JackRandomLookGoal extends Goal {
        private final JackEntity jack;
        private float targetYaw;
        private int timer;

        public JackRandomLookGoal(JackEntity jack) {
            this.jack = jack;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return this.jack.getTarget() == null &&
                    (this.jack.onGround() || this.jack.isInWater() || this.jack.isInLava() || this.jack.hasEffect(MobEffects.LEVITATION)) &&
                    this.jack.getMoveControl() instanceof JackMoveControl;
        }

        @Override
        public void tick() {
            if (--this.timer <= 0) {
                this.timer = this.adjustedTickDelay(40 + this.jack.getRandom().nextInt(60));
                this.targetYaw = (float)this.jack.getRandom().nextInt(360);
            }
            MoveControl control = this.jack.getMoveControl();
            if (control instanceof JackMoveControl moveControl) {
                moveControl.setDirection(this.targetYaw, false);
            }
        }
    }

    static class JackMoveGoal extends Goal {
        private final JackEntity jack;

        public JackMoveGoal(JackEntity jack) {
            this.jack = jack;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return !this.jack.isPassenger();
        }

        @Override
        public void tick() {
            MoveControl control = this.jack.getMoveControl();
            if (control instanceof JackMoveControl moveControl) {
                moveControl.setWantedMovement(1.0);
            }
        }
    }
}