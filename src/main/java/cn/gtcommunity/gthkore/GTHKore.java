package cn.gtcommunity.gthkore;

import cn.gtcommunity.gthkore.api.capability.CapabilityPlayerItem;
import cn.gtcommunity.gthkore.common.CommonProxy;
import gregtech.common.ConfigHolder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION
)
public class GTHKore {

    @SidedProxy(modId = Tags.MOD_ID,
            serverSide = "cn.gtcommunity.gthkore.common.CommonProxy",
            clientSide = "cn.gtcommunity.gthkore.client.ClientProxy")
    public static CommonProxy proxy;

    public GTHKore() {}

    @Mod.EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        ConfigHolder.machines.highTierContent = true;
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        proxy.preLoad();

        CapabilityPlayerItem.preInit();
    }

}
