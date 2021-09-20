package eightsidedsquare.Illegal.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

@SuppressWarnings("deprecation")
public class SidewaysFenceBlock extends Block implements Waterloggable {

    public static final DirectionProperty FACING;
    public static final BooleanProperty LEFT;
    public static final BooleanProperty RIGHT;
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final BooleanProperty WATERLOGGED;

    private static final VoxelShape POST_SHAPE_X;
    private static final VoxelShape LEFT_SHAPE_X;
    private static final VoxelShape RIGHT_SHAPE_X;
    private static final VoxelShape UP_SHAPE_X;
    private static final VoxelShape DOWN_SHAPE_X;
    private static final VoxelShape POST_SHAPE_Z;
    private static final VoxelShape LEFT_SHAPE_Z;
    private static final VoxelShape RIGHT_SHAPE_Z;
    private static final VoxelShape UP_SHAPE_Z;
    private static final VoxelShape DOWN_SHAPE_Z;

    public SidewaysFenceBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(LEFT, false).with(RIGHT, false).with(UP, false).with(DOWN, false).with(WATERLOGGED, false));
    }

    public boolean canConnect(BlockState selfState, BlockState connectingState, boolean neighborIsFullSquare, Direction dir) {
        Block block = connectingState.getBlock();
        boolean bl = this.canConnectToFence(selfState, connectingState);
        boolean bl2 = block instanceof FenceGateBlock && FenceGateBlock.canWallConnect(connectingState, dir);
        return !cannotConnect(connectingState) && neighborIsFullSquare || bl || bl2;
    }

    private boolean canConnectToFence(BlockState selfState, BlockState connectingState) {
        return connectingState.getBlock() instanceof SidewaysFenceBlock && selfState.get(FACING).equals(connectingState.get(FACING));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction side = ctx.getSide();
        World blockView = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        if(side.getAxis().equals(Direction.Axis.Y)) {
            side = Direction.NORTH;
        }
        BlockState state = getDefaultState();
        BlockPos northPos = pos.north();
        BlockPos southPos = pos.south();
        BlockPos eastPos = pos.east();
        BlockPos westPos = pos.west();
        BlockPos upPos = pos.up();
        BlockPos downPos = pos.down();
        BlockState northState = blockView.getBlockState(northPos);
        BlockState southState = blockView.getBlockState(southPos);
        BlockState eastState = blockView.getBlockState(eastPos);
        BlockState westState = blockView.getBlockState(westPos);
        BlockState upState = blockView.getBlockState(upPos);
        BlockState downState = blockView.getBlockState(downPos);
        state = state
                .with(UP, this.canConnect(state, upState, upState.isSideSolidFullSquare(blockView, downPos, Direction.UP), Direction.UP))
                .with(DOWN, this.canConnect(state, downState, downState.isSideSolidFullSquare(blockView, upPos, Direction.DOWN), Direction.DOWN));
        if(side.getAxis().equals(Direction.Axis.X)){
            state = state
                    .with(RIGHT, this.canConnect(state, northState, northState.isSideSolidFullSquare(blockView, southPos, Direction.NORTH), Direction.NORTH))
                    .with(LEFT, this.canConnect(state, southState, southState.isSideSolidFullSquare(blockView, northPos, Direction.SOUTH), Direction.SOUTH));
        }else {
            state = state
                    .with(LEFT, this.canConnect(state, eastState, eastState.isSideSolidFullSquare(blockView, westPos, Direction.EAST), Direction.EAST))
                    .with(RIGHT, this.canConnect(state, westState, westState.isSideSolidFullSquare(blockView, eastPos, Direction.WEST), Direction.WEST));
        }
        return state.with(FACING, side);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEFT, RIGHT, UP, DOWN, WATERLOGGED);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(FACING).getAxis().equals(Direction.Axis.Z) ?
                VoxelShapes.union(POST_SHAPE_Z,
                        state.get(LEFT) ? LEFT_SHAPE_Z : VoxelShapes.empty(),
                        state.get(RIGHT) ? RIGHT_SHAPE_Z : VoxelShapes.empty(),
                        state.get(UP) ? UP_SHAPE_Z : VoxelShapes.empty(),
                        state.get(DOWN) ? DOWN_SHAPE_Z : VoxelShapes.empty())
                :
                VoxelShapes.union(POST_SHAPE_X,
                        state.get(LEFT) ? LEFT_SHAPE_X : VoxelShapes.empty(),
                        state.get(RIGHT) ? RIGHT_SHAPE_X : VoxelShapes.empty(),
                        state.get(UP) ? UP_SHAPE_X : VoxelShapes.empty(),
                        state.get(DOWN) ? DOWN_SHAPE_X : VoxelShapes.empty());
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess blockView, BlockPos pos, BlockPos neighborPos) {
        BlockPos northPos = pos.north();
        BlockPos southPos = pos.south();
        BlockPos eastPos = pos.east();
        BlockPos westPos = pos.west();
        BlockPos upPos = pos.up();
        BlockPos downPos = pos.down();
        BlockState northState = blockView.getBlockState(northPos);
        BlockState southState = blockView.getBlockState(southPos);
        BlockState eastState = blockView.getBlockState(eastPos);
        BlockState westState = blockView.getBlockState(westPos);
        BlockState upState = blockView.getBlockState(upPos);
        BlockState downState = blockView.getBlockState(downPos);
        state = state
                .with(UP, this.canConnect(state, upState, upState.isSideSolidFullSquare(blockView, downPos, Direction.UP), Direction.UP))
                .with(DOWN, this.canConnect(state, downState, downState.isSideSolidFullSquare(blockView, upPos, Direction.DOWN), Direction.DOWN));
        if(state.get(FACING).getAxis().equals(Direction.Axis.X)){
            state = state
                    .with(RIGHT, this.canConnect(state, northState, northState.isSideSolidFullSquare(blockView, southPos, Direction.NORTH), Direction.NORTH))
                    .with(LEFT, this.canConnect(state, southState, southState.isSideSolidFullSquare(blockView, northPos, Direction.SOUTH), Direction.SOUTH));
        }else {
            state = state
                    .with(LEFT, this.canConnect(state, eastState, eastState.isSideSolidFullSquare(blockView, westPos, Direction.EAST), Direction.EAST))
                    .with(RIGHT, this.canConnect(state, westState, westState.isSideSolidFullSquare(blockView, eastPos, Direction.WEST), Direction.WEST));
        }
        return state;
    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
        LEFT = BooleanProperty.of("left");
        RIGHT = BooleanProperty.of("right");
        UP = ConnectingBlock.UP;
        DOWN = ConnectingBlock.DOWN;
        WATERLOGGED = Properties.WATERLOGGED;

        POST_SHAPE_X = Block.createCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
        LEFT_SHAPE_X = Block.createCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 16.0D);
        RIGHT_SHAPE_X = Block.createCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 10.0D);
        UP_SHAPE_X = Block.createCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 16.0D, 10.0D);
        DOWN_SHAPE_X = Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 10.0D, 10.0D);
        POST_SHAPE_Z = Block.createCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
        LEFT_SHAPE_Z = Block.createCuboidShape(6.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D);
        RIGHT_SHAPE_Z = Block.createCuboidShape(0.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
        UP_SHAPE_Z = Block.createCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 16.0D, 16.0D);
        DOWN_SHAPE_Z = Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 10.0D, 16.0D);
    }

}
