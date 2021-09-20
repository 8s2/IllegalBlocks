package eightsidedsquare.Illegal;

import eightsidedsquare.Illegal.common.item.WrenchItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class IllegalItems {

    public static Item WRENCH;

    public static void init() {

        WRENCH = registerItem(new WrenchItem(new Item.Settings().group(ItemGroup.MISC)), "wrench");

    }

    public static Item registerItem(Item item, String name) {

        Registry.register(Registry.ITEM, IllegalMod.MODID + ":" + name, item);

        return item;
    }

}
