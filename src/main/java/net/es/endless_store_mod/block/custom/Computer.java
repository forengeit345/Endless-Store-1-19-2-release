package net.es.endless_store_mod.block.custom;

import net.es.endless_store_mod.world.dimension.EndlessStoreDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Computer extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Block.box(1, 0, 0, 15, 14, 16);
    private static final VoxelShape SHAPE_S = Block.box(1, 0, 0, 15, 14, 16);
    private static final VoxelShape SHAPE_W = Block.box(0, 0, 1, 16, 14, 15);
    private static final VoxelShape SHAPE_E = Block.box(0, 0, 1, 16, 14, 15);

    public Computer(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            this.handleTeleportation((ServerPlayer) player, pos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private void handleTeleportation(ServerPlayer player, BlockPos pos) {
        ResourceKey<Level> targetDimension = player.level().dimension() == EndlessStoreDimension.ESDIM_LEVEL_KEY ?
                Level.OVERWORLD : EndlessStoreDimension.ESDIM_LEVEL_KEY;
        ServerLevel targetWorld = Objects.requireNonNull(player.getServer()).getLevel(targetDimension);
        if (targetWorld == null) return;
        BlockPos targetPos = this.findSafePosition(targetWorld, pos);
        player.teleportTo(targetWorld,
                targetPos.getX() + 0.5,
                targetPos.getY() + 0.1,
                targetPos.getZ() + 0.5,
                player.getYRot(),
                player.getXRot());
        player.setDeltaMovement(Vec3.ZERO);
        player.setOnGround(true);
        player.playSound(SoundEvents.PORTAL_TRAVEL, 1.0F, 1.0F);
    }

    private BlockPos findSafePosition(ServerLevel level, BlockPos origin) {
        int radius = 16;
        for (int y = level.getMinBuildHeight(); y <= level.getMaxBuildHeight(); y++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos testBase = new BlockPos(
                            origin.getX() + dx,
                            y,
                            origin.getZ() + dz
                    );
                    if (isValidSpawnPosition(level, testBase)) {
                        return testBase.above();
                    }
                }
            }
        }
        return level.getSharedSpawnPos();
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
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_S;
            case SOUTH -> SHAPE_N;
            case WEST -> SHAPE_E;
            case EAST -> SHAPE_W;
            default -> SHAPE_N;
        };
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return true;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}