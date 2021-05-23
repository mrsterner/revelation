package mrsterner.seraph.common.entity.interfaces;

import mrsterner.seraph.common.registry.AngelDeal;
import mrsterner.seraph.common.registry.SeraphRegisters;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.List;


@SuppressWarnings("ConstantConditions")
public interface AngelDealAccessor {
    List<AngelDeal.Instance> getAngelDeals();

    default boolean hasAngelDeal(AngelDeal angelDeal) {
        return getAngelDeals().stream().anyMatch(instance -> instance.angelDeal == angelDeal);
    }

    default void addAngelDeal(AngelDeal.Instance instance) {
        if (hasAngelDeal(instance.angelDeal)) {
            for (AngelDeal.Instance angelDeal : getAngelDeals()) {
                if (angelDeal.angelDeal == instance.angelDeal) {
                    angelDeal.duration = instance.duration;
                    return;
                }
            }
        }
        getAngelDeals().add(instance);
    }

    default void removeAngelDeal(AngelDeal angelDeal) {
        if (hasAngelDeal(angelDeal)) {
            for (AngelDeal.Instance instance : getAngelDeals()) {
                if (instance.angelDeal == angelDeal) {
                    instance.duration = 0;
                }
            }
        }
    }

    default ListTag toTagAngelDeal() {
        ListTag angelDeals = new ListTag();
        for (AngelDeal.Instance instance : getAngelDeals()) {
            CompoundTag angelDealTag = new CompoundTag();
            angelDealTag.putString("AngelDeal", SeraphRegisters.ANGEL_DEALS.getId(instance.angelDeal).toString());
            angelDealTag.putInt("Duration", instance.duration);
            angelDealTag.putInt("Cost", instance.cost);
            angelDeals.add(angelDealTag);
        }
        return angelDeals;
    }
}