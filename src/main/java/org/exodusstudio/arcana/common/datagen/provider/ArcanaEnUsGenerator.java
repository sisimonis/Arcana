package org.exodusstudio.arcana.common.datagen.provider;

import org.exodusstudio.arcana.Arcana;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.exodusstudio.arcana.common.registry.BlockRegistry;
import org.exodusstudio.arcana.common.registry.ItemRegistry;

public class ArcanaEnUsGenerator extends LanguageProvider {
    public ArcanaEnUsGenerator(PackOutput output) {
        super(output, Arcana.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("message.arcana", "Press %1$s to perform a custom action!");
        add("key.categories.arcana", "Arcana");

        add("advancements.arcana.root.description", "The Start of A adventure");
        add("advancements.arcana.root.title", "Arcana");

        add("arcana.state.solid", "Solid");
        add("arcana.state.liquid", "Liquid");
        add("arcana.state.gaseous", "Gaseous");

        add("arcana.structure.amorphous", "Amorphous");
        add("arcana.structure.pulverized", "Pulverized");
        add("arcana.structure.pulverized_rough", "Pulverized (Rough)");
        add("arcana.structure.non_newtonian", "Non-Newtonian");
        add("arcana.structure.crystalline", "Crystalline");
        add("arcana.structure.super_saturated", "Super-Saturated");
        add("arcana.structure.highly_viscous", "Highly Viscous");

        add("tooltip.arcana.shift_to_view", "Press shift to view item structure");
        add("itemGroup.arcana.arcana_creative_tab", "Arcana");
        add("arcana.message.scribbling_tool_no_map", "You need more paper to take notes");
        add("arcana.message.research_table_not_found", "Place two research tables nearby");
        add("arcana.message.research_table_note_found", "You already studied this");
        addItem(ItemRegistry.SCRIBBLING_TOOL, "Scribbling Tool");
        addItem(ItemRegistry.SCRIBBLED_NOTE, "Scribbled Note");
        addItem(ItemRegistry.NHIL_CRYSTAL, "Nhil Crystal");
        addItem(ItemRegistry.NHIL_POWDER, "Nhil Powder");
        addItem(ItemRegistry.OLD_NOTE, "Old Note");
        addItem(ItemRegistry.INK_BOTTLE, "Ink Bottle");
        addItem(ItemRegistry.ANCIENT_FEATHER, "Ancient Feather");
        addBlock(BlockRegistry.RESEARCH_TABLE, "Research Table");
        addItem(BlockRegistry.getItemFromBlock(BlockRegistry.RESEARCH_TABLE), "Research Table");
    }
}
