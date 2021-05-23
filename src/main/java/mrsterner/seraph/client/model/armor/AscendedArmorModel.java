package mrsterner.seraph.client.model.armor;


import mrsterner.seraph.Seraph;
import mrsterner.seraph.common.item.AscendedArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AscendedArmorModel extends AnimatedGeoModel<AscendedArmorItem> {
    @Override
    public Identifier getModelLocation(AscendedArmorItem object) {
        return new Identifier(Seraph.MODID, "geo/ascended.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AscendedArmorItem object) {
        return new Identifier(Seraph.MODID, "textures/entity/living/angel/male_1.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AscendedArmorItem animatable) {
        return new Identifier(Seraph.MODID, "animations/ascended.animation.json");
    }
}