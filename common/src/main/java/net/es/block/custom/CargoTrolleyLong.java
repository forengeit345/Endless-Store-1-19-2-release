package net.es.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CargoTrolleyLong extends HorizontalDirectionalBlock {
    public static final MapCodec<CargoTrolleyLong> CODEC = simpleCodec(CargoTrolleyLong::new);

    public CargoTrolleyLong(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_N = Block.box(-0.6, 0, -4.4, 16.2, 15.7, 20.4);
    private static final VoxelShape SHAPE_S = Block.box(-0.6, 0, -4.4, 16.2, 15.7, 20.4);
    private static final VoxelShape SHAPE_W = Block.box(-4.4, 0, -0.19, 20.4, 15.7, 16.6);
    private static final VoxelShape SHAPE_E = Block.box(-4.4, 0, -0.19, 20.4, 15.7, 16.6);

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
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
    protected BlockState rotate(BlockState state, Rotation rotation) {
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