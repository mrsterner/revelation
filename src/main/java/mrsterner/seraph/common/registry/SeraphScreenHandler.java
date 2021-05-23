package mrsterner.seraph.common.registry;

import mrsterner.seraph.Seraph;
import mrsterner.seraph.client.screen.AngelScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class SeraphScreenHandler {
    public static ScreenHandlerType<AngelScreenHandler> ANGEL_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Seraph.MODID, "demon_screen"), (syncId, inventory) -> new AngelScreenHandler(syncId));
}
