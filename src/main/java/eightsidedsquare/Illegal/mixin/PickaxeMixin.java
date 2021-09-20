package eightsidedsquare.Illegal.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PickaxeItem.class)
public class PickaxeMixin {

    @Unique
    public ActionResult useOnBlock(ItemUsageContext context) {

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        if(blockState.getBlock() instanceof OreBlock oreState) {
            BlockState toBlock = Blocks.STONE.getDefaultState();

            BlockSoundGroup soundGroup = oreState.getSoundGroup(blockState);

            if(soundGroup.equals(BlockSoundGroup.DEEPSLATE)) {
                toBlock = Blocks.DEEPSLATE.getDefaultState();
            }else if(soundGroup.equals(BlockSoundGroup.NETHER_GOLD_ORE) || soundGroup.equals(BlockSoundGroup.NETHER_ORE)) {
                toBlock = Blocks.NETHERRACK.getDefaultState();
            }else if(soundGroup.equals(BlockSoundGroup.GILDED_BLACKSTONE)) {
                toBlock = Blocks.BLACKSTONE.getDefaultState();
            }

            world.playSound(playerEntity, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);

            world.setBlockState(blockPos, toBlock, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

}
