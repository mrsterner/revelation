package mrsterner.seraph.client.model.armor;

import mrsterner.seraph.Seraph;
import mrsterner.seraph.common.item.WingsArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WingsArmorModel extends AnimatedGeoModel<WingsArmorItem> {
    @Override
    public Identifier getModelLocation(WingsArmorItem object) {
        return new Identifier(Seraph.MODID, "geo/wings.geo.json");
    }

    @Override
    public Identifier getTextureLocation(WingsArmorItem object) {
        return new Identifier(Seraph.MODID, "textures/entity/living/angel/male_1.png");
    }

    @Override
    public Identifier getAnimationFileLocation(WingsArmorItem animatable) {
        return new Identifier(Seraph.MODID, "animations/angel.animation.json");
    }
}