package mrsterner.seraph.client.model.entity;

import mrsterner.seraph.Seraph;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WingsEntityModel extends AnimatedGeoModel {
    @Override
    public Identifier getAnimationFileLocation(Object entity) {
        return new Identifier(Seraph.MODID, "animations/angel.animation.json");
    }

    @Override
    public Identifier getModelLocation(Object entity) {
        return new Identifier(Seraph.MODID, "geo/wings.geo.json");
    }

    @Override
    public Identifier getTextureLocation(Object entity) {
        return new Identifier(Seraph.MODID, "textures/models/entity/male_0.png");
    }
}