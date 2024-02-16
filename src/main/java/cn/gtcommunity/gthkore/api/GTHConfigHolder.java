package cn.gtcommunity.gthkore.api;

import cn.gtcommunity.gthkore.Tags;
import net.minecraftforge.common.config.Config;

@Config(
        modid = Tags.MOD_ID
)
public class GTHConfigHolder {

    @Config.Comment("Harder game changes.")
    @Config.Name("Harder Change")
    public static HarderChange harderChange = new HarderChange();

    public static class HarderChange {
        @Config.Comment("Enable change drop items.")
        @Config.RequiresMcRestart
        public boolean enableChangeDrop = true;

    }

}
