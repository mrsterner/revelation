package mrsterner.seraph;

import mrsterner.seraph.common.registry.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;

public class Seraph implements ModInitializer {


    public static final String MODID = "seraph";
    public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, MODID), () -> new ItemStack(SeraphObjects.SACRED_HEART));

    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        SeraphObjects.init();
        SeraphEntities.init();
        SeraphAngelDeals.init();
        CommandRegistrationCallback.EVENT.register(SeraphCommands::init);
        SeraphCommands.registerArgumentTypes();
        SeraphStatusEffects.init();

    }


}