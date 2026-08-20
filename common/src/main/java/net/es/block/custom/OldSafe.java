package net.es.block.custom;

import com.mojang.serialization.MapCodec;
import net.es.block.entity.OldSafeEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class OldSafe extends BaseEntityBlock {
    public static final MapCodec<OldSafe> CODEC = simpleCodec(OldSafe::new);

    public static Supplier<BlockEntityType<OldSafeEntity>> TYPE = () -> {
        throw new IllegalStateException("OldSafe type not initialized");
    };

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public OldSafe(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    // Воксельные формы для разных направлений (размеры как в старом коде)
    private static final VoxelShape SHAPE_N = Block.box(0, 0, 0, 16, 16, 14.4);
    private static final VoxelShape SHAPE_S = Block.box(0, 0, 1.7, 16, 16, 16);
    private static final VoxelShape SHAPE_W = Block.box(0, 0, 0, 14.3, 16, 16);
    private static final VoxelShape SHAPE_E = Block.box(1.7, 0, 0, 16, 16, 16);

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
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            MenuProvider menuProvider = this.getMenuProvider(state, level, pos);
            if (menuProvider != null) {
                player.openMenu(menuProvider);
                player.awardStat(Stats.OPEN_CHEST);
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof net.minecraft.world.Container container) {
                Containers.dropContents(level, pos, container);
            }
            super.onRemove(state, level, pos, newState, moved);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof OldSafeEntity) {
            ((OldSafeEntity) blockEntity).tick();
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OldSafeEntity(pos, state);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
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