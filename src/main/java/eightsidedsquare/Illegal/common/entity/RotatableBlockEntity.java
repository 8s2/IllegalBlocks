package eightsidedsquare.Illegal.common.entity;

import eightsidedsquare.Illegal.IllegalBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RotatableBlockEntity extends BlockEntity {

    public float pitch;
    public float yaw;
    public float roll;
    public float sizeX = 1.0f;
    public float sizeY = 1.0f;
    public float sizeZ = 1.0f;
    public float offsetX;
    public float offsetY;
    public float offsetZ;
    public BlockState displayBlock;

    public RotatableBlockEntity(BlockPos pos, BlockState state) {
        super(IllegalBlocks.ROTATABLE_BLOCK_ENTITY, pos, state);
    }

    public RotatableBlockEntity(BlockPos pos, BlockState state, BlockState displayBlock) {
        super(IllegalBlocks.ROTATABLE_BLOCK_ENTITY, pos, state);
        this.displayBlock = displayBlock;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {

        super.writeNbt(tag);
        if(displayBlock != null) {
            tag.put("BlockState", NbtHelper.fromBlockState(displayBlock));
        }

        tag.putFloat("Pitch", pitch);
        tag.putFloat("Yaw", yaw);
        tag.putFloat("Roll", roll);
        tag.putFloat("SizeX", sizeX);
        tag.putFloat("SizeY", sizeY);
        tag.putFloat("SizeZ", sizeZ);
        tag.putFloat("OffsetX", offsetX);
        tag.putFloat("OffsetY", offsetY);
        tag.putFloat("OffsetZ", offsetZ);

        return tag;
    }

    public BlockState getBlockState() {
        return this.displayBlock;
    }

    public World getWorldClient() {
        return world;
    }

    @Override
    public void readNbt(NbtCompound tag){
        super.readNbt(tag);
        if(tag.get("BlockState") instanceof NbtCompound blockState) {
            displayBlock = NbtHelper.toBlockState(blockState);
        }
        pitch = tag.getFloat("Pitch");
        yaw = tag.getFloat("Yaw");
        roll = tag.getFloat("Roll");
        sizeX = tag.getFloat("SizeX");
        sizeY = tag.getFloat("SizeY");
        sizeZ = tag.getFloat("SizeZ");
        offsetX = tag.getFloat("OffsetX");
        offsetY = tag.getFloat("OffsetY");
        offsetZ = tag.getFloat("OffsetZ");

    }

    public static void tick(World world, BlockPos pos, BlockState state, RotatableBlockEntity blockEntity) {
    }

    public NbtCompound toInitialChunkDataNbt() {
        return this.writeNbt(new NbtCompound());
    }

}
