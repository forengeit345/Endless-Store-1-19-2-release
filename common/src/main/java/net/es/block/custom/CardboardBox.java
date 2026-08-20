package net.es.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CardboardBox extends FallingBlock {
    public static final MapCodec<CardboardBox> CODEC = simpleCodec(CardboardBox::new);

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public CardboardBox(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_N = Block.box(2.5, 0, 4.4, 11.5, 8.9, 13.3);
    private static final VoxelShape SHAPE_S = Block.box(4.5, 0, 2.4, 13.5, 8.9, 11.3);
    private static final VoxelShape SHAPE_W = Block.box(2.5, 0, 2.4, 11.5, 8.9, 11.3);
    private static final VoxelShape SHAPE_E = Block.box(4.5, 0, 4.4, 13.5, 8.9, 13.3);

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_S;
            case SOUTH -> SHAPE_N;
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