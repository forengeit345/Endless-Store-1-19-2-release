package net.es.block.custom;

import net.es.world.dimension.EndlessStoreDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class LiterallyNothingPortal extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public LiterallyNothingPortal(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && entity instanceof ServerPlayer player) {
            handleTeleportation(player, pos);
        }
        super.stepOn(level, pos, state, entity);
    }

    private void handleTeleportation(ServerPlayer player, BlockPos pos) {
        ResourceKey<Level> targetDimension = player.level().dimension() == EndlessStoreDimension.ESDIM_LEVEL_KEY
                ? Level.OVERWORLD
                : EndlessStoreDimension.ESDIM_LEVEL_KEY;

        ServerLevel targetWorld = Objects.requireNonNull(player.getServer()).getLevel(targetDimension);
        assert targetWorld != null;
        BlockPos targetPos = findSafePosition(targetWorld, pos);
        player.teleportTo(
                targetWorld,
                targetPos.getX() + 0.5,
                targetPos.getY() + 0.1,
                targetPos.getZ() + 0.5,
                player.getYRot(),
                player.getXRot()
        );
        player.setDeltaMovement(Vec3.ZERO);
        player.setOnGround(true);
        player.playSound(SoundEvents.PORTAL_TRAVEL, 1.0F, 1.0F);
    }

    private BlockPos findSafePosition(ServerLevel world, BlockPos origin) {
        int radius = 16;
        for (int y = world.getMinBuildHeight(); y <= world.getMaxBuildHeight(); y++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos testBase = new BlockPos(
                            origin.getX() + dx,
                            y,
                            origin.getZ() + dz
                    );
                    if (isValidSpawnPosition(world, testBase)) {
                        return testBase.above();
                    }
                }
            }
        }
        return world.getSharedSpawnPos();
    }

    private boolean isValidSpawnPosition(Level level, BlockPos basePos) {
        BlockPos groundPos = basePos.below();
        BlockState groundState = level.getBlockState(groundPos);
        boolean validGround = groundState.isSolid();
        boolean validAir = level.getBlockState(basePos).isAir()
                && level.getBlockState(basePos.above()).isAir()
                && level.getBlockState(basePos.above(2)).isAir();
        return validGround && validAir;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}