package eightsidedsquare.Illegal.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class SmallBlock extends Block {

    private final VoxelShape OUTLINE_SHAPE;

    public SmallBlock(Settings settings, double diameter) {
        super(settings);
        OUTLINE_SHAPE = Block.createCuboidShape(8.0D - diameter / 2.0D, 0, 8.0D - diameter / 2.0D, 8.0D + diameter / 2.0D, diameter, 8.0D + diameter / 2.0D);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.OUTLINE_SHAPE;
    }
}
