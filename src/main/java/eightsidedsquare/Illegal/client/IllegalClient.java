package eightsidedsquare.Illegal.client;

import eightsidedsquare.Illegal.IllegalBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class IllegalClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        BlockEntityRendererRegistry.INSTANCE.register(IllegalBlocks.ROTATABLE_BLOCK_ENTITY, RotatableBlockEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.THICK_OAK_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.THICK_SPRUCE_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.THICK_BIRCH_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.THICK_JUNGLE_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.THICK_ACACIA_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.THICK_DARK_OAK_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.THICK_CRIMSON_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.THICK_WARPED_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.THICK_IRON_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IllegalBlocks.ROTATABLE_BLOCK, RenderLayer.getCutout());


    }
}
