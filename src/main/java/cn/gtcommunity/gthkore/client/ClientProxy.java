package cn.gtcommunity.gthkore.client;

import cn.gtcommunity.gthkore.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(
        Side.CLIENT
)
public class ClientProxy extends CommonProxy {

    public void preLoad() {
        super.preLoad();
    }

}
