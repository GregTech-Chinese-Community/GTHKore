package cn.gtcommunity.gthkore.common.harvest;

import cn.gtcommunity.gthkore.common.items.GTHMetaItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.oredict.OreDictionary;

public class HarvestChangeManager {

    public static void changeEmptyHandDrop(BlockEvent.HarvestDropsEvent event) {
        Block block = event.getState().getBlock();
        if (OreDictionary.getOres("gravel").contains(new ItemStack(block))) {
            event.getDrops().clear();
            event.getDrops().add(GTHMetaItems.COBBLE.getStackForm(3));
        }
        if (
                   !(OreDictionary.getOres("sand").contains(new ItemStack(block)))
                && !(OreDictionary.getOres("gravel").contains(new ItemStack(block)))
                && !(OreDictionary.getOres("dirt").contains(new ItemStack(block)) || OreDictionary.getOres("grass").contains(new ItemStack(block)))
                && !(OreDictionary.getOres("treeLeaves").contains(new ItemStack(block)))
                && !(OreDictionary.getOres("treeSaplings").contains(new ItemStack(block)))
        ) {
            event.getDrops().clear();
        }
    }

}
