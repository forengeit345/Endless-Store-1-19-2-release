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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PlasticChair extends HorizontalDirectionalBlock {
    public static final MapCodec<PlasticChair> CODEC = simpleCodec(PlasticChair::new);

    public PlasticChair(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_N = Shapes.or(
            Block.box(2.5, 0, 2.5, 13.5, 9.1, 14.5),
            Block.box(2.5, 9.1, 11.4, 13.5, 22.4, 14.5)
    );
    private static final VoxelShape SHAPE_S = Shapes.or(
            Block.box(2.5, 0, 1.5, 13.5, 9.1, 13.5),
            Block.box(2.5, 9.1, 1.4, 13.5, 22.4, 4.5)
    );
    private static final VoxelShape SHAPE_E = Shapes.or(
            Block.box(1.5, 0, 2.5, 13.5, 9.1, 13.5),
            Block.box(1.4, 9.1, 2.5, 4.5, 22.4, 13.5)
    );
    private static final VoxelShape SHAPE_W = Shapes.or(
            Block.box(2.58, 0, 2.5, 14.5, 9.1, 13.5),
            Block.box(11.4, 9.1, 2.5, 14.5, 22.4, 13.5)

    );

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