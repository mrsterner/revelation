package mrsterner.seraph.client.renderer.armor;


import mrsterner.seraph.client.model.armor.AscendedArmorModel;
import mrsterner.seraph.common.item.AscendedArmorItem;
import software.bernie.geckolib3.renderer.geo.GeoArmorRenderer;

public class AscendedArmorRenderer extends GeoArmorRenderer<AscendedArmorItem> {
    public AscendedArmorRenderer() {
        super(new AscendedArmorModel());

        this.bodyBone = "armorBody";
    }
}
