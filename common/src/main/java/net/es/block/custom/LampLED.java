package net.es.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class LampLED extends HorizontalDirectionalBlock {
    public static final MapCodec<LampLED> CODEC = simpleCodec(LampLED::new);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public LampLED(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide) {
            level.setBlockAndUpdate(pos, state.cycle(LIT));
        }
        return InteractionResult.SUCCESS;
    }

    private static final VoxelShape SHAPE_N = Block.box(7.25, 15.25, 2.5, 8.75, 16, 13.5);
    private static final VoxelShape SHAPE_S = Block.box(7.25, 15.25, 2.5, 8.75, 16, 13.5);
    private static final VoxelShape SHAPE_W = Block.box(2.5, 15.25, 7.25, 13.5, 16, 8.75);
    private static final VoxelShape SHAPE_E = Block.box(2.5, 15.25, 7.25, 13.5, 16, 8.75);

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
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        builder.add(FACING);
    }
}