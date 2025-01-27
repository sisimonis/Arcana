package org.exodusstudio.arcana.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.arcana.Arcana;

public class ArcanaItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Arcana.MODID);

    public static final DeferredItem<Item> SCRIBBLING_TOOL = ITEMS.registerItem(
            "scribbling_tool",
            ArcanaScribblingToolItem::new,
            new Item.Properties());
    public static final DeferredItem<Item> SCRIBBLED_NOTE = ITEMS.registerSimpleItem("scribbled_note");
    public static final DeferredItem<Item> NHIL_CRYSTAL = ITEMS.registerSimpleItem("nhil_crystal");

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
