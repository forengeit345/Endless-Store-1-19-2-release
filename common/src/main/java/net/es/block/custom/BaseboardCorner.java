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
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.BlockView;

import static javax.swing.SwingConstants.NORTH;
import static net.es.block.custom.Baseboard.FACING;

public class BaseboardCorner extends FallingBlock {
    public static final MapCodec<BaseboardCorner> CODEC = simpleCodec(BaseboardCorner::new);

    public BaseboardCorner(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_N = Shapes.or(
            Block.box(0, 0, 0, 0.75, 1.5, 16),
            Block.box(0.75, 0, 0, 16, 1.5, 0.75)
    );
    private static final VoxelShape SHAPE_E = Shapes.or(
            Block.box(0, 0, 0, 16, 1.5, 0.75),
            Block.box(15.25, 0, 0.75, 16, 1.5, 16)
    );
    private static final VoxelShape SHAPE_S = Shapes.or(
            Block.box(15.25, 0, 0, 16, 1.5, 16),
            Block.box(0, 0, 15.25, 15.25, 1.5, 16)
    );
    private static final VoxelShape SHAPE_W = Shapes.or(
            Block.box(0, 0, 15.25, 16, 1.5, 16),
            Block.box(0, 0, 0, 0.75, 1.5, 15.25)
    );

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
        return true;
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