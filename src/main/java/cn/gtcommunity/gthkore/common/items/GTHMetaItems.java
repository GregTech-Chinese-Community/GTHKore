package cn.gtcommunity.gthkore.common.items;

import cn.gtcommunity.gthkore.common.CommonProxy;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;

public class GTHMetaItems {
    public static final StandardMetaItem metaItem = new StandardMetaItem();

    public static MetaItem<?>.MetaValueItem COBBLE;

    public static void init() {
        addItems();
        metaItem.setRegistryName("gth_meta_item");
        metaItem.setCreativeTab(CommonProxy.TAB_GTH);
    }

    public static void addItems() {
        COBBLE = metaItem.addItem(1, "cobble");
    }
}
