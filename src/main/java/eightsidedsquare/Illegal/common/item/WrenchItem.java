package eightsidedsquare.Illegal.common.item;

import eightsidedsquare.Illegal.IllegalBlocks;
import eightsidedsquare.Illegal.IllegalItems;
import eightsidedsquare.Illegal.common.entity.RotatableBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WrenchItem extends Item {

    public WrenchItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {

        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(pos);

        if(playerEntity == null || playerEntity.getItemCooldownManager().isCoolingDown(IllegalItems.WRENCH)) {
            return ActionResult.PASS;
        }

        if(!blockState.isOf(IllegalBlocks.ROTATABLE_BLOCK)) {
            BlockState state = IllegalBlocks.ROTATABLE_BLOCK.getDefaultState();
            if(blockState.contains(Properties.WATERLOGGED)) {
                state = state.with(Properties.WATERLOGGED, blockState.get(Properties.WATERLOGGED));
            }
            world.setBlockState(pos, state, Block.FORCE_STATE);
            if (world.getBlockEntity(pos) instanceof RotatableBlockEntity entity) {
                entity.displayBlock = blockState;
            }
        }
        if (world.getBlockEntity(pos) instanceof RotatableBlockEntity entity){
            Vec3d hitPos = context.getHitPos();
            double hitX = (hitPos.x - pos.getX());
            double hitY = (hitPos.y - pos.getY());
            double hitZ = (hitPos.z - pos.getZ());
            if(!playerEntity.isSneaking()) {
                if (context.getSide().equals(Direction.NORTH)) {
                    entity.yaw = hitX < 0.3D ? entity.yaw + 5 : hitX > 0.7D ? entity.yaw - 5 : entity.yaw;
                    entity.pitch = hitY < 0.3D ? entity.pitch - 5 : hitY > 0.7D ? entity.pitch + 5 : entity.pitch;
                } else if (context.getSide().equals(Direction.SOUTH)) {
                    entity.yaw = hitX < 0.3D ? entity.yaw - 5 : hitX > 0.7D ? entity.yaw + 5 : entity.yaw;
                    entity.pitch = hitY < 0.3D ? entity.pitch + 5 : hitY > 0.7D ? entity.pitch - 5 : entity.pitch;
                } else if (context.getSide().equals(Direction.WEST)) {
                    entity.yaw = hitZ < 0.3D ? entity.yaw - 5 : hitZ > 0.7D ? entity.yaw + 5 : entity.yaw;
                    entity.roll = hitY < 0.3D ? entity.roll + 5 : hitY > 0.7D ? entity.roll - 5 : entity.roll;
                } else if (context.getSide().equals(Direction.EAST)) {
                    entity.yaw = hitZ < 0.3D ? entity.yaw + 5 : hitZ > 0.7D ? entity.yaw - 5 : entity.yaw;
                    entity.roll = hitY < 0.3D ? entity.roll - 5 : hitY > 0.7D ? entity.roll + 5 : entity.roll;
                } else if (context.getSide().equals(Direction.UP)) {
                    entity.pitch = hitZ < 0.3D ? entity.pitch - 5 : hitZ > 0.7D ? entity.pitch + 5 : entity.pitch;
                    entity.roll = hitX < 0.3D ? entity.roll + 5 : hitX > 0.7D ? entity.roll - 5 : entity.roll;
                } else if (context.getSide().equals(Direction.DOWN)) {
                    entity.pitch = hitZ < 0.3D ? entity.pitch + 5 : hitZ > 0.7D ? entity.pitch - 5 : entity.pitch;
                    entity.roll = hitX < 0.3D ? entity.roll - 5 : hitX > 0.7D ? entity.roll + 5 : entity.roll;
                }
            }else {
                if (context.getSide().equals(Direction.NORTH)) {
                    entity.offsetZ += 0.0625D;
                } else if (context.getSide().equals(Direction.SOUTH)) {
                    entity.offsetZ -= 0.0625D;
                } else if (context.getSide().equals(Direction.WEST)) {
                    entity.offsetX += 0.0625D;
                } else if (context.getSide().equals(Direction.EAST)) {
                    entity.offsetX -= 0.0625D;
                } else if (context.getSide().equals(Direction.UP)) {
                    entity.offsetY -= 0.0625D;
                } else if (context.getSide().equals(Direction.DOWN)) {
                    entity.offsetY += 0.0625D;
                }
            }
            playerEntity.playSound(SoundEvents.ITEM_SPYGLASS_USE, SoundCategory.BLOCKS, 1.0f, 1.2f);
            playerEntity.getItemCooldownManager().set(IllegalItems.WRENCH, 2);
        }
        return ActionResult.success(world.isClient);
    }
}
