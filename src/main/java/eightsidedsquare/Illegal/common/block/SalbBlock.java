package eightsidedsquare.Illegal.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SalbBlock extends SlabBlock {

    public SalbBlock(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        boolean cull = false;
        if(stateFrom.isOf(this)) {
            if((stateFrom.get(TYPE) == SlabType.DOUBLE) || (stateFrom.get(TYPE) == state.get(TYPE))) {
                cull = true;
            }else if(direction == Direction.DOWN && stateFrom.get(TYPE) != SlabType.BOTTOM) {
                cull = true;
            }
        }
        return cull || super.isSideInvisible(state, stateFrom, direction);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }
}
