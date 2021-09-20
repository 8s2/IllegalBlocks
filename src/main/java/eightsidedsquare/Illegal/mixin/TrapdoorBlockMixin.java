package eightsidedsquare.Illegal.mixin;

import eightsidedsquare.Illegal.IllegalBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(TrapdoorBlock.class)
public class TrapdoorBlockMixin {

    @Unique
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify){

        if(state.isOf(Blocks.OAK_TRAPDOOR)) {
            world.setBlockState(pos, IllegalBlocks.THICK_OAK_TRAPDOOR.getStateWithProperties(state));
        }else if(state.isOf(Blocks.SPRUCE_TRAPDOOR)) {
            world.setBlockState(pos, IllegalBlocks.THICK_SPRUCE_TRAPDOOR.getStateWithProperties(state));
        }else if(state.isOf(Blocks.BIRCH_TRAPDOOR)) {
            world.setBlockState(pos, IllegalBlocks.THICK_BIRCH_TRAPDOOR.getStateWithProperties(state));
        }else if(state.isOf(Blocks.JUNGLE_TRAPDOOR)) {
            world.setBlockState(pos, IllegalBlocks.THICK_JUNGLE_TRAPDOOR.getStateWithProperties(state));
        }else if(state.isOf(Blocks.ACACIA_TRAPDOOR)) {
            world.setBlockState(pos, IllegalBlocks.THICK_ACACIA_TRAPDOOR.getStateWithProperties(state));
        }else if(state.isOf(Blocks.DARK_OAK_TRAPDOOR)) {
            world.setBlockState(pos, IllegalBlocks.THICK_DARK_OAK_TRAPDOOR.getStateWithProperties(state));
        }else if(state.isOf(Blocks.CRIMSON_TRAPDOOR)) {
            world.setBlockState(pos, IllegalBlocks.THICK_CRIMSON_TRAPDOOR.getStateWithProperties(state));
        }else if(state.isOf(Blocks.WARPED_TRAPDOOR)) {
            world.setBlockState(pos, IllegalBlocks.THICK_WARPED_TRAPDOOR.getStateWithProperties(state));
        }else if(state.isOf(Blocks.IRON_TRAPDOOR)) {
            world.setBlockState(pos, IllegalBlocks.THICK_IRON_TRAPDOOR.getStateWithProperties(state));
        }
    }

}
