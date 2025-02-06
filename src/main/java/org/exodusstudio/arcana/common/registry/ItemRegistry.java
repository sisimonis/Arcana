package org.exodusstudio.arcana.common.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.item.ArcanaOldNoteItem;
import org.exodusstudio.arcana.common.item.ArcanaScribbledNoteItem;
import org.exodusstudio.arcana.common.item.ArcanaScribblingToolItem;

import java.awt.*;

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
    public static final DeferredItem<Item> OLD_NOTE = ITEMS.registerItem("old_note",
            ArcanaOldNoteItem::new,
            new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> NHIL_CRYSTAL = ITEMS.registerSimpleItem("nhil_crystal");
    public static final DeferredItem<Item> INK_BOTTLE = ITEMS.registerSimpleItem("ink_bottle");
    public static final DeferredItem<Item> ANCIENT_FEATHER = ITEMS.registerSimpleItem("ancient_feather");
    public static final DeferredItem<Item> NHIL_POWDER = ITEMS.registerSimpleItem("nhil_powder");


    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
