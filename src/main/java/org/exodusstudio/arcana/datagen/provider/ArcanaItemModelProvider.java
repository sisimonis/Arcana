package org.exodusstudio.arcana.datagen.provider;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.item.ArcanaItems;

public class ArcanaItemModelProvider extends ModelProvider {
    public ArcanaItemModelProvider(PackOutput output) {
        super(output, Arcana.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ArcanaItems.SCRIBBLING_TOOL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ArcanaItems.SCRIBBLED_NOTE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ArcanaItems.NHIL_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
    }
}
