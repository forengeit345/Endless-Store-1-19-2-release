package net.es.endless_store_mod.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.EnumSet;

public class JackEntity extends HostileEntity implements IAnimatable {
    public float targetStretch;
    public float stretch;
    public float lastStretch;
    private boolean onGroundLastTick;

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public JackEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new JackMoveControl(this);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.45F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new JackEntity.SwimmingGoal(this));
        this.goalSelector.add(2, new JackEntity.FaceTowardTargetGoal(this));
        this.goalSelector.add(3, new JackEntity.RandomLookGoal(this));
        this.goalSelector.add(5, new JackEntity.MoveGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, (livingEntity) -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0));
    }

    public int getXpToDrop() {
        if (this.isBaby()) {
            this.experiencePoints = (int)((double)this.experiencePoints * 2.5);
        }

        return super.getXpToDrop();
    }

    protected void initDataTracker() {
        super.initDataTracker();
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (event.isMoving() && !this.handSwinging) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.jack.jump", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
//        TODO: починить анимации джэка чтобы он перестал вертеть головой
        else if (!event.isMoving() && !this.handSwinging && !(this.isAttacking())) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.jack.idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        else {
            return PlayState.STOP;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    public void tick() {
        this.stretch += (this.targetStretch - this.stretch) * 0.5F;
        this.lastStretch = this.stretch;
        super.tick();
        if (this.onGround && !this.onGroundLastTick) {
            this.targetStretch = -0.5F;
        } else if (!this.onGround && this.onGroundLastTick) {
            this.targetStretch = 1.0F;
        }

        this.onGroundLastTick = this.onGround;
        this.updateStretch();
    }

    protected void updateStretch() {
        this.targetStretch *= 0.6F;
    }

    protected int getTicksUntilNextJump() {
        return this.random.nextInt(40) + 10;
    }

    public void calculateDimensions() {
        double d = this.getX();
        double e = this.getY();
        double f = this.getZ();
        super.calculateDimensions();
        this.setPosition(d, e, f);
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        this.calculateDimensions();
        this.setYaw(this.headYaw);
        this.bodyYaw = this.headYaw;
        if (this.isTouchingWater() && this.random.nextInt(20) == 0) {
            this.onSwimmingStart();
        }

        super.onTrackedDataSet(data);
    }

    public void pushAwayFrom(Entity entity) {
        super.pushAwayFrom(entity);
        if (entity instanceof IronGolemEntity && this.canAttack()) {
            this.damage((LivingEntity)entity);
        }
    }

    public void onPlayerCollision(PlayerEntity player) {
        if (this.canAttack()) {
            this.damage(player);
        }
    }

    protected void damage(LivingEntity target) {
        if (this.isAlive()) {
            if (this.squaredDistanceTo(target) < 0.8 * 0.8 && this.canSee(target) && target.damage(DamageSource.mob(this), this.getDamageAmount())) {
                this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.applyDamageEffects(this, target);
            }
        }
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.625F * dimensions.height;
    }

    protected boolean canAttack() {
        return this.canMoveVoluntarily();
    }

    protected float getDamageAmount() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    protected void jump() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x, this.getJumpVelocity(), vec3d.z);
        this.velocityDirty = true;
    }

    private static class JackMoveControl extends MoveControl {
        private float targetYaw;
        private int ticksUntilJump;
        private final JackEntity jack;
        private boolean jumpOften;

        public JackMoveControl(JackEntity jack) {
            super(jack);
            this.jack = jack;
            this.targetYaw = 180.0F * jack.getYaw() / 3.1415927F;
        }

        public void look(float targetYaw, boolean jumpOften) {
            this.targetYaw = targetYaw;
            this.jumpOften = jumpOften;
        }

        public void move(double speed) {
            this.speed = speed;
            this.state = State.MOVE_TO;
        }

        public void tick() {
            this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), this.targetYaw, 90.0F));
            this.entity.headYaw = this.entity.getYaw();
            this.entity.bodyYaw = this.entity.getYaw();
            if (this.state != State.MOVE_TO) {
                this.entity.setForwardSpeed(0.0F);
            } else {
                this.state = State.WAIT;
                if (this.entity.isOnGround()) {
                    this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                    if (this.ticksUntilJump-- <= 0) {
                        this.ticksUntilJump = this.jack.getTicksUntilNextJump();
                        if (this.jumpOften) {
                            this.ticksUntilJump /= 3;
                        }

                        this.jack.getJumpControl().setActive();
                    } else {
                        this.jack.sidewaysSpeed = 0.0F;
                        this.jack.forwardSpeed = 0.0F;
                        this.entity.setMovementSpeed(0.0F);
                    }
                } else {
                    this.entity.setMovementSpeed((float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                }

            }
        }
    }

    static class SwimmingGoal extends Goal {
        private final JackEntity jack;

        public SwimmingGoal(JackEntity jack) {
            this.jack = jack;
            this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
            jack.getNavigation().setCanSwim(true);
        }

        public boolean canStart() {
            return (this.jack.isTouchingWater() || this.jack.isInLava()) && this.jack.getMoveControl() instanceof JackEntity.JackMoveControl;
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            if (this.jack.getRandom().nextFloat() < 0.8F) {
                this.jack.getJumpControl().setActive();
            }

            ((JackEntity.JackMoveControl)this.jack.getMoveControl()).move(1.2);
        }
    }

    static class FaceTowardTargetGoal extends Goal {
        private final JackEntity jack;
        private int ticksLeft;

        public FaceTowardTargetGoal(JackEntity jack) {
            this.jack = jack;
            this.setControls(EnumSet.of(Control.LOOK));
        }

        public boolean canStart() {
            LivingEntity livingEntity = this.jack.getTarget();
            if (livingEntity == null) {
                return false;
            } else {
                return this.jack.canTarget(livingEntity) && this.jack.getMoveControl() instanceof JackMoveControl;
            }
        }

        public void start() {
            this.ticksLeft = toGoalTicks(300);
            super.start();
        }

        public boolean shouldContinue() {
            LivingEntity livingEntity = this.jack.getTarget();
            if (livingEntity == null) {
                return false;
            } else if (!this.jack.canTarget(livingEntity)) {
                return false;
            } else {
                return --this.ticksLeft > 0;
            }
        }
        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingEntity = this.jack.getTarget();
            if (livingEntity != null) {
                this.jack.lookAtEntity(livingEntity, 10.0F, 10.0F);
            }

            ((JackEntity.JackMoveControl)this.jack.getMoveControl()).look(this.jack.getYaw(), this.jack.canAttack());
        }
    }

    static class RandomLookGoal extends Goal {
        private final JackEntity jack;
        private float targetYaw;
        private int timer;

        public RandomLookGoal(JackEntity jack) {
            this.jack = jack;
            this.setControls(EnumSet.of(Control.LOOK));
        }

        public boolean canStart() {
            return this.jack.getTarget() == null && (this.jack.onGround || this.jack.isTouchingWater() || this.jack.isInLava() || this.jack.hasStatusEffect(StatusEffects.LEVITATION)) && this.jack.getMoveControl() instanceof JackEntity.JackMoveControl;
        }

        public void tick() {
            if (--this.timer <= 0) {
                this.timer = this.getTickCount(40 + this.jack.getRandom().nextInt(60));
                this.targetYaw = (float)this.jack.getRandom().nextInt(360);
            }

            ((JackEntity.JackMoveControl)this.jack.getMoveControl()).look(this.targetYaw, false);
        }
    }

    static class MoveGoal extends Goal {
        private final JackEntity jack;

        public MoveGoal(JackEntity jack) {
            this.jack = jack;
            this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
        }

        public boolean canStart() {
            return !this.jack.hasVehicle();
        }

        public void tick() {
            ((JackEntity.JackMoveControl)this.jack.getMoveControl()).move(1.0);
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
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
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15f, 1.0f);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("wasOnGround", this.onGroundLastTick);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.onGroundLastTick = nbt.getBoolean("wasOnGround");
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        float f = difficulty.getClampedLocalDifficulty();
        this.setCanPickUpLoot(random.nextFloat() < 0.55F * f);
        return entityData;
    }
}
