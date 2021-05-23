package mrsterner.seraph;

import mrsterner.seraph.client.network.SyncAngelDealPacket;
import mrsterner.seraph.client.network.SyncAngelTradesPacket;
import mrsterner.seraph.client.renderer.armor.AscendedArmorRenderer;
import mrsterner.seraph.client.renderer.armor.WingsArmorRenderer;
import mrsterner.seraph.client.renderer.entity.AngelRenderer;
import mrsterner.seraph.client.screen.AngelScreen;
import mrsterner.seraph.client.screen.AngelScreenHandler;
import mrsterner.seraph.common.entity.AngelEntity;
import mrsterner.seraph.common.item.AscendedArmorItem;
import mrsterner.seraph.common.item.WingsArmorItem;
import mrsterner.seraph.common.registry.SeraphEntities;
import mrsterner.seraph.common.registry.SeraphScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.screen.ScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import software.bernie.geckolib3.renderer.geo.GeoArmorRenderer;

import java.util.List;

@Environment(EnvType.CLIENT)
public class SeraphClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientPlayNetworking.registerGlobalReceiver(SyncAngelDealPacket.ID, SyncAngelDealPacket::handle);
        ClientPlayNetworking.registerGlobalReceiver(SyncAngelTradesPacket.ID, (client, network, buf, sender) -> {
            int syncId = buf.readInt();
            List<AngelEntity.AngelTradeOffer> offers = AngelEntity.AngelTradeOffer.fromPacket(buf);
            int traderId = buf.readInt();
            client.execute(() -> {
                if (client.player != null) {
                    ScreenHandler screenHandler = client.player.currentScreenHandler;
                    if (syncId == screenHandler.syncId && screenHandler instanceof AngelScreenHandler) {
                        ((AngelScreenHandler) screenHandler).angelMerchant.setCurrentCustomer(client.player);
                        ((AngelScreenHandler) screenHandler).angelMerchant.setOffersClientside(offers);
                        ((AngelScreenHandler) screenHandler).angelMerchant.setAngelTraderClientside((LivingEntity) client.world.getEntityById(traderId));
                    }
                }
            });
        });
        ScreenRegistry.register(SeraphScreenHandler.ANGEL_SCREEN_HANDLER, AngelScreen::new);

        EntityRendererRegistry.INSTANCE.register(SeraphEntities.ANGEL_ENTITY, (entityRenderDispatcher, context) -> new AngelRenderer(entityRenderDispatcher));
        GeoArmorRenderer.registerArmorRenderer(WingsArmorItem.class, new WingsArmorRenderer());
        WingsArmorRenderer.registerArmorRenderer(WingsArmorItem.class, new WingsArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(AscendedArmorItem.class, new AscendedArmorRenderer());
        AscendedArmorRenderer.registerArmorRenderer(AscendedArmorItem.class, new AscendedArmorRenderer());

    }
}