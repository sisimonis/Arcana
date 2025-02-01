package org.exodusstudio.arcana.common.registry;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.item.ArcanaScribbledNoteItem;
import org.exodusstudio.arcana.common.item.ArcanaScribblingToolItem;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Arcana.MODID);

    public static final DeferredItem<Item> SCRIBBLING_TOOL = ITEMS.registerItem(
            "scribbling_tool",
            ArcanaScribblingToolItem::new,
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> SCRIBBLED_NOTE = ITEMS.registerItem(
            "scribbled_note",
            ArcanaScribbledNoteItem::new,
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> NHIL_CRYSTAL = ITEMS.registerSimpleItem("nhil_crystal");
    public static final DeferredItem<Item> NHIL_POWDER = ITEMS.registerSimpleItem("nhil_powder");


    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
