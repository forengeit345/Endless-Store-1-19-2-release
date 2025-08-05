package net.es.endless_store_mod.block.custom;

import net.es.endless_store_mod.world.dimension.EndlessStoreDimension;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Computer extends HorizontalFacingBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public Computer(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE_N = Block.createCuboidShape(1, 0, 0, 15, 14, 16);
    private static final VoxelShape SHAPE_S = Block.createCuboidShape(1, 0, 0, 15, 14, 16);
    private static final VoxelShape SHAPE_W = Block.createCuboidShape(0, 0, 1, 16, 14, 15);
    private static final VoxelShape SHAPE_E = Block.createCuboidShape(0, 0, 1, 16, 14, 15);

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && hand == Hand.MAIN_HAND) {
            this.handleTeleportation((ServerPlayerEntity) player, pos);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private void handleTeleportation(ServerPlayerEntity player, BlockPos pos) {
        RegistryKey<World> targetDimension = player.getWorld().getRegistryKey() == EndlessStoreDimension.ESDIM_DIMENSION_KEY ? World.OVERWORLD : EndlessStoreDimension.ESDIM_DIMENSION_KEY;
        ServerWorld targetWorld = Objects.requireNonNull(player.getServer()).getWorld(targetDimension);
        assert targetWorld != null;
        BlockPos targetPos = this.findSafePosition(targetWorld, pos);
        player.teleport(
                targetWorld,
                targetPos.getX() + 0.5,
                targetPos.getY() + 0.1, // Небольшое смещение для предотвращения падения
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

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> SHAPE_S;
            case SOUTH -> SHAPE_N;
            case WEST -> SHAPE_E;
            case EAST -> SHAPE_W;
            default -> SHAPE_N;
        };
    }

    private boolean isValidSpawnPosition(World world, BlockPos basePos) {
        BlockPos groundPos = basePos.down();
        BlockState groundState = world.getBlockState(groundPos);
        boolean validGround = groundState.isSolidBlock(world, groundPos); // Исключаем стекло, листья и т.д.
        boolean validAir = world.getBlockState(basePos).isAir()
                && world.getBlockState(basePos.up()).isAir()
                && world.getBlockState(basePos.up(2)).isAir();
        return validGround && validAir;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
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