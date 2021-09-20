package eightsidedsquare.Illegal.mixin;

import eightsidedsquare.Illegal.IllegalBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FenceBlock.class)
public class FenceBlockMixin {

    @Inject(method = "getPlacementState", at = @At("HEAD"), cancellable = true)
    private void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        if(ctx.getSide().getAxis() != Direction.Axis.Y) {
            Block fenceType;
            if(ctx.getPlayer() != null && ctx.getPlayer().getActiveHand() != null && ctx.getPlayer().getStackInHand(ctx.getPlayer().getActiveHand()) != null){
                ItemStack fenceUsed = ctx.getPlayer().getStackInHand(ctx.getPlayer().getActiveHand());
                if(fenceUsed.isOf(Blocks.OAK_FENCE.asItem())) {
                    cir.setReturnValue(IllegalBlocks.SIDEWAYS_OAK_FENCE.getPlacementState(ctx));
                }else if(fenceUsed.isOf(Blocks.SPRUCE_FENCE.asItem())) {
                    cir.setReturnValue(IllegalBlocks.SIDEWAYS_SPRUCE_FENCE.getPlacementState(ctx));
                }else if(fenceUsed.isOf(Blocks.BIRCH_FENCE.asItem())) {
                    cir.setReturnValue(IllegalBlocks.SIDEWAYS_BIRCH_FENCE.getPlacementState(ctx));
                }else if(fenceUsed.isOf(Blocks.JUNGLE_FENCE.asItem())) {
                    cir.setReturnValue(IllegalBlocks.SIDEWAYS_JUNGLE_FENCE.getPlacementState(ctx));
                }else if(fenceUsed.isOf(Blocks.ACACIA_FENCE.asItem())) {
                    cir.setReturnValue(IllegalBlocks.SIDEWAYS_ACACIA_FENCE.getPlacementState(ctx));
                }else if(fenceUsed.isOf(Blocks.DARK_OAK_FENCE.asItem())) {
                    cir.setReturnValue(IllegalBlocks.SIDEWAYS_DARK_OAK_FENCE.getPlacementState(ctx));
                }else if(fenceUsed.isOf(Blocks.CRIMSON_FENCE.asItem())) {
                    cir.setReturnValue(IllegalBlocks.SIDEWAYS_CRIMSON_FENCE.getPlacementState(ctx));
                }else if(fenceUsed.isOf(Blocks.WARPED_FENCE.asItem())) {
                    cir.setReturnValue(IllegalBlocks.SIDEWAYS_WARPED_FENCE.getPlacementState(ctx));
                }
            }
        }
    }

}
