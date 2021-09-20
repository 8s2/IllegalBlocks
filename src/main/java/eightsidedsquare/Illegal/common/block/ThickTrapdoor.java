package eightsidedsquare.Illegal.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ThickTrapdoor extends TrapdoorBlock {

    public static final IntProperty THICKNESS;
    public Block TRAPDOOR_BLOCK;

    public ThickTrapdoor(Settings settings, Block trapdoorBlock) {
        super(settings);
        TRAPDOOR_BLOCK = trapdoorBlock;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, HALF, POWERED, THICKNESS, WATERLOGGED);
    }

    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(TRAPDOOR_BLOCK);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.getStackInHand(hand).isOf(TRAPDOOR_BLOCK.asItem()) && state.get(THICKNESS) < 5) {
            world.setBlockState(pos, state.with(THICKNESS, state.get(THICKNESS) + 1), Block.NOTIFY_LISTENERS);
            world.playSound(null, pos, this.soundGroup.getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.2F);
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction direction = ctx.getSide();
        if (!ctx.canReplaceExisting() && direction.getAxis().isHorizontal()) {
            blockState = (BlockState)((BlockState)blockState.with(FACING, direction)).with(HALF, ctx.getHitPos().y - (double)ctx.getBlockPos().getY() > 0.5D ? BlockHalf.TOP : BlockHalf.BOTTOM);
        } else {
            blockState = (BlockState)((BlockState)blockState.with(FACING, ctx.getPlayerFacing().getOpposite())).with(HALF, direction == Direction.UP ? BlockHalf.BOTTOM : BlockHalf.TOP);
        }

        if (ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos())) {
            blockState = (BlockState)((BlockState)blockState.with(OPEN, true)).with(POWERED, true);
        }

        return (BlockState)blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int thickness = state.get(THICKNESS);
        if (!(Boolean)state.get(OPEN)) {
            return state.get(HALF) == BlockHalf.TOP ? Block.createCuboidShape(0.0D, 16.0D + (thickness * -3.0D), 0.0D, 16.0D, 16.0D, 16.0D)
                    : Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, thickness * 3.0D, 16.0D);
        } else {
            switch(state.get(FACING)) {
                case NORTH:
                default:
                    return Block.createCuboidShape(0.0D, 0.0D, 16.0D + (thickness * -3.0D), 16.0D, 16.0D, 16.0D);
                case SOUTH:
                    return Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, thickness * 3.0D);
                case WEST:
                    return Block.createCuboidShape(16.0D + (thickness * -3.0D), 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
                case EAST:
                    return Block.createCuboidShape(0.0D, 0.0D, 0.0D, thickness * 3.0D, 16.0D, 16.0D);
            }
        }
    }

    static {

        THICKNESS = IntProperty.of("thickness", 1, 5);
    }

}
