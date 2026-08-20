package net.es.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class Baseboard extends FallingBlock {
    public static final MapCodec<Baseboard> CODEC = simpleCodec(Baseboard::new);

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public Baseboard(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    private static final VoxelShape SHAPE_N = Block.box(0, 0, 0, 16, 1.5, 0.75);
    private static final VoxelShape SHAPE_S = Block.box(0, 0, 0, 0.75, 1.5, 16);   // Для западного направления (mirror)
    private static final VoxelShape SHAPE_W = Block.box(0, 0, 15.25, 16, 1.5, 16);
    private static final VoxelShape SHAPE_E = Block.box(15.25, 0, 0, 16, 1.5, 16);

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_N;
            case SOUTH -> SHAPE_W;
            case WEST -> SHAPE_S;
            case EAST -> SHAPE_E;
            default -> SHAPE_N;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos below = pos.below();
        return Block.canSupportCenter(level, below, Direction.UP);
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