package mrsterner.seraph.mixin;


import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.interfaces.entity.BloodAccessor;
import mrsterner.seraph.common.entity.interfaces.AngelDealAccessor;
import mrsterner.seraph.common.registry.*;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements AngelDealAccessor {
    private final List<AngelDeal.Instance> angelDeals = new ArrayList<>();


    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public List<AngelDeal.Instance> getAngelDeals() {
        return angelDeals;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo callbackInfo) {
        if (!world.isClient) {
            int level = 0;
            for (int i = angelDeals.size() - 1; i >= 0; i--) {
                AngelDeal.Instance instance = angelDeals.get(i);
                level += instance.cost;
                instance.angelDeal.tick((PlayerEntity) (Object) this);
                instance.duration--;
                if (instance.duration <= 0) {
                    angelDeals.remove(i);
                }
            }
            if (level > 0) {
                addStatusEffect(new StatusEffectInstance(SeraphStatusEffects.URIEL, 10, 1, true, false));
            }
        }
        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        ItemStack chest = playerEntity.getEquippedStack(EquipmentSlot.CHEST);

        if (hasAngelDeal(SeraphAngelDeals.WINGS) && chest.getItem() != SeraphObjects.URIEL_WINGS && !world.isClient) {
            playerEntity.giveItemStack(chest.copy());
            chest.setCount(0);
            ItemStack stack = new ItemStack(SeraphObjects.URIEL_WINGS);
            stack.hasTag();
            stack.addEnchantment(Enchantments.BINDING_CURSE, 1);
            stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);
            playerEntity.equipStack(EquipmentSlot.CHEST, stack);
        }
        if (chest.getItem() == SeraphObjects.URIEL_WINGS) {
            playerEntity.abilities.allowFlying = true;
            if (!hasAngelDeal(SeraphAngelDeals.WINGS)) {
                playerEntity.abilities.allowFlying = false;
                playerEntity.abilities.flying = false;
                System.out.println("Acc");
                chest.setCount(0);
                playerEntity.sendAbilitiesUpdate();
            }
        }

    }


    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    private void readCustomDataFromTag(CompoundTag tag, CallbackInfo callbackInfo) {
        ListTag angelDeals = tag.getList("AngelDeals", NbtType.COMPOUND);
        for (int i = 0; i < angelDeals.size(); i++) {
            CompoundTag angelDeal = angelDeals.getCompound(i);
            addAngelDeal(new AngelDeal.Instance(SeraphRegisters.ANGEL_DEALS.get(new Identifier(angelDeal.getString("AngelDeal"))), angelDeal.getInt("Duration"), angelDeal.getInt("Cost")));
        }
    }

    @Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
    private void writeCustomDataToTag(CompoundTag tag, CallbackInfo callbackInfo) {
        tag.put("AngelDeals", toTagAngelDeal());
    }
}
