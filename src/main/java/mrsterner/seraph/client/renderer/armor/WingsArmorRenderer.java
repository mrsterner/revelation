package mrsterner.seraph.client.renderer.armor;


import mrsterner.seraph.client.model.armor.WingsArmorModel;
import mrsterner.seraph.common.item.WingsArmorItem;
import software.bernie.geckolib3.renderer.geo.GeoArmorRenderer;

public class WingsArmorRenderer extends GeoArmorRenderer<WingsArmorItem> {
    public WingsArmorRenderer() {
        super(new WingsArmorModel());

        this.bodyBone = "armorBody";
    }
}
