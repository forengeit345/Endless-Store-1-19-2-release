package net.es.endless_store_mod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class PlasticChair extends HorizontalFacingBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public PlasticChair(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE_N = VoxelShapes.union(
            Block.createCuboidShape(2.5, 0, 2.5, 13.5, 9.1, 14.5),
            Block.createCuboidShape(2.5, 9.1, 11.4, 13.5, 22.4, 14.5)
    );
    private static final VoxelShape SHAPE_S = VoxelShapes.union(
            Block.createCuboidShape(2.5, 0, 1.5, 13.5, 9.1, 13.5),
            Block.createCuboidShape(2.5, 9.1, 1.4, 13.5, 22.4, 4.5)
    );
    private static final VoxelShape SHAPE_E = VoxelShapes.union(
            Block.createCuboidShape(1.5, 0, 2.5, 13.5, 9.1, 13.5),
            Block.createCuboidShape(1.4, 9.1, 2.5, 4.5, 22.4, 13.5)
    );
    private static final VoxelShape SHAPE_W = VoxelShapes.union(
            Block.createCuboidShape(2.58, 0, 2.5, 14.5, 9.1, 13.5),
            Block.createCuboidShape(11.4, 9.1, 2.5, 14.5, 22.4, 13.5)

    );

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> SHAPE_N;
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            case EAST -> SHAPE_E;
            default -> SHAPE_N;
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}