package mrsterner.seraph.common.registry;

import moriyashiine.bewitchment.common.registry.BWMaterials;
import mrsterner.seraph.Seraph;
import mrsterner.seraph.common.item.AngelDealItem;
import mrsterner.seraph.common.item.AscendedArmorItem;
import mrsterner.seraph.common.item.WingsArmorItem;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.*;
import net.minecraft.tag.Tag;
import net.minecraft.util.Rarity;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

import static mrsterner.seraph.Seraph.MOD_GROUP;


public class SeraphObjects {
    private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    //Armor
    public static final Item URIEL_WINGS = create("wings", new WingsArmorItem(BWMaterials.BESMIRCHED_ARMOR, EquipmentSlot.CHEST));
    public static final Item ASCENDED_WINGS = create("ascended", new AscendedArmorItem(BWMaterials.BESMIRCHED_ARMOR, EquipmentSlot.CHEST));
    //Item
    public static final Item ANGEL_DEAL = create("angel_deal", new AngelDealItem(gen().rarity(Rarity.RARE).maxCount(1)));

    //Food
    public static final Item SACRED_HEART = create("sacred_heart", new Item(gen().food(new FoodComponent.Builder().hunger(1).saturationModifier(1).alwaysEdible().meat().build())));
    //Spawn Eggs
    public static final Item ANGEL_SPAWN_EGG = create("angel_spawn_egg", new SpawnEggItem(SeraphEntities.ANGEL_ENTITY, 0xf0e2df, 0xf0bf3a, gen()));



    //Register
    private static <T extends Block> T create(String name, T block, boolean createItem) {
        BLOCKS.put(block, new Identifier(Seraph.MODID, name));
        if (createItem) {
            ITEMS.put(new BlockItem(block, gen()), BLOCKS.get(block));
        }
        return block;
    }

    private static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, new Identifier(Seraph.MODID, name));
        return item;
    }

    private static Item.Settings gen() {
        return new Item.Settings().group(MOD_GROUP);
    }

    public static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
    }

}
