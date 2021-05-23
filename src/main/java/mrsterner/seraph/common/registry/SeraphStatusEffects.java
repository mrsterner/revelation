package mrsterner.seraph.common.registry;

import moriyashiine.bewitchment.common.statuseffect.EmptyStatusEffect;
import mrsterner.seraph.Seraph;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class SeraphStatusEffects {
    private static final Map<StatusEffect, Identifier> STATUS_EFFECTS = new LinkedHashMap<>();

    public static final StatusEffect URIEL = create("urieleffect", new EmptyStatusEffect(StatusEffectType.NEUTRAL, 0x2f5e7b));


    private static <T extends StatusEffect> T create(String name, T effect) {
        STATUS_EFFECTS.put(effect, new Identifier(Seraph.MODID, name));
        return effect;
    }

    public static void init() {
        STATUS_EFFECTS.keySet().forEach(effect -> Registry.register(Registry.STATUS_EFFECT, STATUS_EFFECTS.get(effect), effect));
    }
}
