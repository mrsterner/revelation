package mrsterner.seraph.common.registry;

import mrsterner.seraph.Seraph;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SeraphRegisters {
    public static final Registry<AngelDeal> ANGEL_DEALS = FabricRegistryBuilder.createSimple(AngelDeal.class, new Identifier(Seraph.MODID, "angel_deal")).buildAndRegister();
}
