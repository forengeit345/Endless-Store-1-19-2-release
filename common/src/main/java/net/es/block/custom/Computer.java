package net.es.block.custom;

import com.mojang.serialization.MapCodec;
import net.es.world.dimension.EndlessStoreDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
    public static final MapCodec<Computer> CODEC = simpleCodec(Computer::new);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public Computer(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_N = Block.box(1, 0, 0, 15, 14, 16);
    private static final VoxelShape SHAPE_S = Block.box(1, 0, 0, 15, 14, 16);
    private static final VoxelShape SHAPE_W = Block.box(0, 0, 1, 16, 14, 15);
    private static final VoxelShape SHAPE_E = Block.box(0, 0, 1, 16, 14, 15);

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_N;
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            case EAST -> SHAPE_E;
            default -> SHAPE_N;
        };
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                            Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            handleTeleportation((ServerPlayer) player, pos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            handleTeleportation((ServerPlayer) player, pos);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
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
        for (int y = world.getMaxBuildHeight(); y >= world.getMinBuildHeight(); y--) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos testBase = new BlockPos(
                            origin.getX() + dx,
                            y,
                            origin.getZ() + dz
                    );
                    if (isValidSpawnPosition(world, testBase)) {
                        return testBase.above(); // позиция над твёрдым блоком
                    }
                }
            }
        }
        return world.getSharedSpawnPos(); // запасной вариант, если ничего не нашли
    }

    private boolean isValidSpawnPosition(Level level, BlockPos basePos) {
        BlockPos groundPos = basePos.below();
        BlockState groundState = level.getBlockState(groundPos);
        boolean validGround = groundState.isSolid(); // полный блок
        boolean validAir = level.getBlockState(basePos).isAir()
                && level.getBlockState(basePos.above()).isAir()
                && level.getBlockState(basePos.above(2)).isAir();
        return validGround && validAir;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return true;
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