package net.es.endless_store_mod.block.custom;

import net.es.endless_store_mod.world.dimension.EndlessStoreDimension;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class LiterallyNothingPortal extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public LiterallyNothingPortal(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient && entity instanceof ServerPlayerEntity player) {
            this.handleTeleportation(player, pos);
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    private void handleTeleportation(ServerPlayerEntity player, BlockPos pos) {
        RegistryKey<World> targetDimension = player.getWorld().getRegistryKey() == EndlessStoreDimension.ESDIM_LEVEL_KEY ? World.OVERWORLD : EndlessStoreDimension.ESDIM_LEVEL_KEY;
        ServerWorld targetWorld = Objects.requireNonNull(player.getServer()).getWorld(targetDimension);
        assert targetWorld != null;
        BlockPos targetPos = this.findSafePosition(targetWorld, pos);
        player.teleport(
                targetWorld,
                targetPos.getX() + 0.5,
                targetPos.getY() + 0.1,
                targetPos.getZ() + 0.5,
                player.getYaw(),
                player.getPitch()
        );
        player.setVelocity(Vec3d.ZERO);
        player.setOnGround(true);
        player.playSound(SoundEvents.BLOCK_PORTAL_TRAVEL, 1.0F, 1.0F);
    }

    private BlockPos findSafePosition(ServerWorld world, BlockPos origin) {
        int radius = 16;
        for (int y = world.getBottomY(); y <= world.getTopY(); y++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos testBase = new BlockPos(
                            origin.getX() + dx,
                            y,
                            origin.getZ() + dz
                    );
                    if (isValidSpawnPosition(world, testBase)) {
                        return testBase.up();
                    }
                }
            }
        }
    return world.getSpawnPos();
    }

    private boolean isValidSpawnPosition(World world, BlockPos basePos) {
        BlockPos groundPos = basePos.down();
        BlockState groundState = world.getBlockState(groundPos);
        boolean validGround = groundState.isSolidBlock(world, groundPos);
        boolean validAir = world.getBlockState(basePos).isAir()
                && world.getBlockState(basePos.up()).isAir()
                && world.getBlockState(basePos.up(2)).isAir();
        return validGround && validAir;
    }


    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
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