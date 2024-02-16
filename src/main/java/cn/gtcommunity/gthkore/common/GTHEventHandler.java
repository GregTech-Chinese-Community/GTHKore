package cn.gtcommunity.gthkore.common;

import cn.gtcommunity.gthkore.Tags;
import cn.gtcommunity.gthkore.api.GTHConfigHolder;
import cn.gtcommunity.gthkore.api.capability.CapabilityPlayerItem;
import cn.gtcommunity.gthkore.api.capability.IPlayerItem;
import cn.gtcommunity.gthkore.common.harvest.HarvestChangeManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(
        modid = Tags.MOD_ID
)
public class GTHEventHandler {

    @SubscribeEvent
    public static void playerBreakingBlock(BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        if (player != null) {
            IPlayerItem cap = player.getCapability(CapabilityPlayerItem.CAPABILITY, null);
            if (cap != null) {
                cap.setStack(player.getHeldItemMainhand());
            }
        }

        if (event.getState().getBlock() == Blocks.GRAVEL) {
            event.getPlayer().attackEntityFrom(DamageSource.MAGIC, 2);
        }
    }

    @SubscribeEvent
    public static void blockDrop(BlockEvent.HarvestDropsEvent event) {
        EntityPlayer player = event.getHarvester();
        if (!event.getWorld().isRemote && player != null && !(player instanceof FakePlayer) && !player.isCreative() && GTHConfigHolder.harderChange.enableChangeDrop) {
            IBlockState state = event.getState();
            IPlayerItem cap = player.getCapability(CapabilityPlayerItem.CAPABILITY, null);
            ItemStack stack = ItemStack.EMPTY;

            if (cap != null) {
                stack = cap.getStack();
            }
            if (stack.isEmpty()) {
                stack = player.getHeldItemMainhand();
            }

            if (stack.getItem() == Items.AIR) {
                HarvestChangeManager.changeEmptyHandDrop(event);
            }
        }
    }

}
