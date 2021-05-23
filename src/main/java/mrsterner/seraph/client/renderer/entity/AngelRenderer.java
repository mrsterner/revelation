package mrsterner.seraph.client.renderer.entity;

import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import mrsterner.seraph.Seraph;
import mrsterner.seraph.client.model.entity.AngelModel;
import mrsterner.seraph.common.entity.AngelEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class AngelRenderer extends GeoEntityRenderer<AngelEntity> {
    private static Identifier[] MALE_TEXTURES, FEMALE_TEXTURES;

    public AngelRenderer(EntityRenderDispatcher renderManager) {
        super(renderManager, new AngelModel());
        this.shadowRadius = 0.7F; //change 0.7 to the desired shadow size.
    }


    @Override
    public Identifier getTexture(AngelEntity entity) {
        if (MALE_TEXTURES == null) {
            int variants = entity.getVariants();
            MALE_TEXTURES = new Identifier[variants];
            FEMALE_TEXTURES = new Identifier[variants];
            for (int i = 0; i < variants; i++) {
                MALE_TEXTURES[i] = new Identifier(Seraph.MODID, "textures/entity/living/angel/male_" + i + ".png");
                FEMALE_TEXTURES[i] = new Identifier(Seraph.MODID, "textures/entity/living/angel/female_" + i + ".png");
            }
        }
        int variant = entity.getDataTracker().get(BWHostileEntity.VARIANT);
        return entity.getDataTracker().get(AngelEntity.MALE) ? MALE_TEXTURES[variant] : FEMALE_TEXTURES[variant];
    }
}