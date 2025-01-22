package dev.zestyblaze.arcana.datagen.provider;

import dev.zestyblaze.arcana.Arcana;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ArcanaEnUsGenerator extends LanguageProvider {
    public ArcanaEnUsGenerator(PackOutput output) {
        super(output, Arcana.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
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
    }
}
