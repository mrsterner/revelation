package mrsterner.seraph.common.registry;

import mrsterner.seraph.Seraph;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class SeraphAngelDeals {
    private static final Map<AngelDeal, Identifier> ANGEL_DEALS = new LinkedHashMap<>();

    public static final AngelDeal WINGS = create("wings", new AngelDeal());
    public static final AngelDeal HOLY_MANTEL = create("holy_mantel", new AngelDeal());
    public static final AngelDeal WINGS3 = create("wings3", new AngelDeal());

    private static <T extends AngelDeal> T create(String name, T angelDeal) {
        ANGEL_DEALS.put(angelDeal, new Identifier(Seraph.MODID, name));
        return angelDeal;
    }

    public static void init() {
        ANGEL_DEALS.keySet().forEach(angelDeal -> Registry.register(SeraphRegisters.ANGEL_DEALS, ANGEL_DEALS.get(angelDeal), angelDeal));
    }

    private static Item.Settings gen() {
        return new Item.Settings().group(Seraph.MOD_GROUP);
    }
}
