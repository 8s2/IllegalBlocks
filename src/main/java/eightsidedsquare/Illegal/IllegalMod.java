package eightsidedsquare.Illegal;

import net.fabricmc.api.ModInitializer;

public class IllegalMod implements ModInitializer {

    public static final String MODID = "illegal";

    @Override
    public void onInitialize() {

        IllegalBlocks.init();
        IllegalItems.init();

    }
}
