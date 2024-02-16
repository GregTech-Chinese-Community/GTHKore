package cn.gtcommunity.gthkore.api.capability;

import net.minecraft.item.ItemStack;

public interface IPlayerItem {

    ItemStack getStack();

    void setStack(ItemStack stack);

}
