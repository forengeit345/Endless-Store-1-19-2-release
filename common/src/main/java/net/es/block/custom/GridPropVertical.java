package net.es.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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

public class GridPropVertical extends HorizontalDirectionalBlock {
    public static final MapCodec<GridPropVertical> CODEC = simpleCodec(GridPropVertical::new);

    public GridPropVertical(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_N = Block.box(0, 0, 7.5, 16, 16, 8.5);
    private static final VoxelShape SHAPE_S = Block.box(0, 0, 7.5, 16, 16, 8.5);
    private static final VoxelShape SHAPE_W = Block.box(7.5, 0, 0, 8.5, 16, 16);
    private static final VoxelShape SHAPE_E = Block.box(7.5, 0, 0, 8.5, 16, 16);

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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
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