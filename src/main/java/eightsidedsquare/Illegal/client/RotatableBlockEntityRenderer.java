package eightsidedsquare.Illegal.client;

import eightsidedsquare.Illegal.common.entity.RotatableBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.Random;

@Environment(EnvType.CLIENT)
public class RotatableBlockEntityRenderer implements BlockEntityRenderer<RotatableBlockEntity> {

    public RotatableBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(RotatableBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState blockState = blockEntity.displayBlock;
        if(blockState != null && blockState.getRenderType() != null && blockState.getRenderType().equals(BlockRenderType.MODEL)) {
            World world = blockEntity.getWorldClient();
            if (blockState.getRenderType() != BlockRenderType.INVISIBLE && world != null) {
                matrices.push();
                matrices.translate(0.5d, 0.5, 0.5d);
                matrices.multiply(Vec3f.POSITIVE_X.getRadialQuaternion((float) Math.toRadians(blockEntity.pitch)));
                matrices.multiply(Vec3f.POSITIVE_Y.getRadialQuaternion((float) Math.toRadians(blockEntity.yaw)));
                matrices.multiply(Vec3f.POSITIVE_Z.getRadialQuaternion((float) Math.toRadians(blockEntity.roll)));
                matrices.scale(blockEntity.sizeX, blockEntity.sizeY, blockEntity.sizeZ);
                matrices.translate(blockEntity.offsetX - 0.5, blockEntity.offsetY - 0.5, blockEntity.offsetZ - 0.5);
                BlockPos blockPos = blockEntity.getPos();
                BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
                blockRenderManager.getModelRenderer().render(world, blockRenderManager.getModel(blockState), blockState, blockPos, matrices, vertexConsumers.getBuffer(RenderLayers.getMovingBlockLayer(blockState)), false, new Random(), blockState.getRenderingSeed(blockEntity.getPos()), OverlayTexture.DEFAULT_UV);

                matrices.pop();
            }

        }
    }
}
