package eightsidedsquare.Illegal;

import eightsidedsquare.Illegal.common.block.*;
import eightsidedsquare.Illegal.common.entity.RotatableBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

import java.util.LinkedHashMap;
import java.util.Map;

public class IllegalBlocks {

    private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    public static BlockEntityType<RotatableBlockEntity> ROTATABLE_BLOCK_ENTITY;

    public static final Block THICK_OAK_TRAPDOOR = create("thick_oak_trapdoor", new ThickTrapdoor(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR), Blocks.OAK_TRAPDOOR), null);
    public static final Block THICK_SPRUCE_TRAPDOOR = create("thick_spruce_trapdoor", new ThickTrapdoor(FabricBlockSettings.copy(Blocks.SPRUCE_TRAPDOOR), Blocks.SPRUCE_TRAPDOOR), null);
    public static final Block THICK_BIRCH_TRAPDOOR = create("thick_birch_trapdoor", new ThickTrapdoor(FabricBlockSettings.copy(Blocks.BIRCH_TRAPDOOR), Blocks.BIRCH_TRAPDOOR), null);
    public static final Block THICK_JUNGLE_TRAPDOOR = create("thick_jungle_trapdoor", new ThickTrapdoor(FabricBlockSettings.copy(Blocks.JUNGLE_TRAPDOOR), Blocks.JUNGLE_TRAPDOOR), null);
    public static final Block THICK_ACACIA_TRAPDOOR = create("thick_acacia_trapdoor", new ThickTrapdoor(FabricBlockSettings.copy(Blocks.ACACIA_TRAPDOOR), Blocks.ACACIA_TRAPDOOR), null);
    public static final Block THICK_DARK_OAK_TRAPDOOR = create("thick_dark_oak_trapdoor", new ThickTrapdoor(FabricBlockSettings.copy(Blocks.DARK_OAK_TRAPDOOR), Blocks.DARK_OAK_TRAPDOOR), null);
    public static final Block THICK_CRIMSON_TRAPDOOR = create("thick_crimson_trapdoor", new ThickTrapdoor(FabricBlockSettings.copy(Blocks.CRIMSON_TRAPDOOR), Blocks.CRIMSON_TRAPDOOR), null);
    public static final Block THICK_WARPED_TRAPDOOR = create("thick_warped_trapdoor", new ThickTrapdoor(FabricBlockSettings.copy(Blocks.WARPED_TRAPDOOR), Blocks.WARPED_TRAPDOOR), null);
    public static final Block THICK_IRON_TRAPDOOR = create("thick_iron_trapdoor", new ThickTrapdoor(FabricBlockSettings.copy(Blocks.IRON_TRAPDOOR), Blocks.IRON_TRAPDOOR), null);
    public static final Block ROTATABLE_BLOCK = create("rotatable_block", new RotateableBlock(FabricBlockSettings.copy(Blocks.BARRIER).solidBlock(IllegalBlocks::never)), null);
    public static final Block SALB = create("salb", new SalbBlock(FabricBlockSettings.copy(Blocks.SMOOTH_STONE_SLAB)), ItemGroup.BUILDING_BLOCKS);
    public static final Block SIDEWAYS_OAK_FENCE = create("sideways_oak_fence", new SidewaysFenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE)), null);
    public static final Block SIDEWAYS_SPRUCE_FENCE = create("sideways_spruce_fence", new SidewaysFenceBlock(FabricBlockSettings.copy(Blocks.SPRUCE_FENCE)), null);
    public static final Block SIDEWAYS_BIRCH_FENCE = create("sideways_birch_fence", new SidewaysFenceBlock(FabricBlockSettings.copy(Blocks.BIRCH_FENCE)), null);
    public static final Block SIDEWAYS_JUNGLE_FENCE = create("sideways_jungle_fence", new SidewaysFenceBlock(FabricBlockSettings.copy(Blocks.JUNGLE_FENCE)), null);
    public static final Block SIDEWAYS_ACACIA_FENCE = create("sideways_acacia_fence", new SidewaysFenceBlock(FabricBlockSettings.copy(Blocks.ACACIA_FENCE)), null);
    public static final Block SIDEWAYS_DARK_OAK_FENCE = create("sideways_dark_oak_fence", new SidewaysFenceBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_FENCE)), null);
    public static final Block SIDEWAYS_CRIMSON_FENCE = create("sideways_crimson_fence", new SidewaysFenceBlock(FabricBlockSettings.copy(Blocks.CRIMSON_FENCE)), null);
    public static final Block SIDEWAYS_WARPED_FENCE = create("sideways_warped_fence", new SidewaysFenceBlock(FabricBlockSettings.copy(Blocks.WARPED_FENCE)), null);
    public static final Block STONE_CHUNK = create("stone_chunk", new SmallBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque(), 8.0D), ItemGroup.BUILDING_BLOCKS);
    public static final Block STONE_FRAGMENT = create("stone_fragment", new SmallBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque(), 4.0D), ItemGroup.BUILDING_BLOCKS);
    public static final Block STONE_BIT = create("stone_bit", new SmallBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque(), 2.0D), ItemGroup.BUILDING_BLOCKS);
    public static final Block STONE_IOTA = create("stone_iota", new SmallBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque(), 1.0D), ItemGroup.BUILDING_BLOCKS);
    public static final Block STONE_PARTICLE = create("stone_particle", new SmallBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque(), 0.5D), ItemGroup.BUILDING_BLOCKS);
    public static final Block STONE_CRUMB = create("stone_crumb", new SmallBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque(), 0.25D), ItemGroup.BUILDING_BLOCKS);
    public static final Block STONE_DOT = create("stone_dot", new SmallBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque(), 0.125D), ItemGroup.BUILDING_BLOCKS);

    private static <T extends Block> T create(String name, T block, ItemGroup itemGroup) {

        BLOCKS.put(block, new Identifier(IllegalMod.MODID, name));
        if (itemGroup != null) {
            ITEMS.put(new BlockItem(block, new Item.Settings().group(itemGroup)), BLOCKS.get(block));
        }
        return block;
    }

    public static void init() {

        ROTATABLE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "illegal:rotatable_block_entity", FabricBlockEntityTypeBuilder.create(RotatableBlockEntity::new, ROTATABLE_BLOCK).build(null));

        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));

        registerFlammableBlocks();

    }

    private static Block registerBlock(Block block, String name) {
        return Registry.register(Registry.BLOCK, new Identifier(IllegalMod.MODID, name), block);
    }

    public static Item registerItem(Item item, String name) {

        Registry.register(Registry.ITEM, new Identifier(IllegalMod.MODID, name), item);

        return item;
    }

    public static void registerFlammableBlocks() {

//        FlammableBlockRegistry.getDefaultInstance().add(APPLE_BRANCH, 5, 5);

    }

    private static boolean always(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    private static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }
    private static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}
