package org.exodusstudio.arcana.common.datagen.provider;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.registry.ItemRegistry;

public class ArcanaItemModelProvider extends ModelProvider {
    public ArcanaItemModelProvider(PackOutput output) {
        super(output, Arcana.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ItemRegistry.SCRIBBLING_TOOL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemRegistry.SCRIBBLED_NOTE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemRegistry.NHIL_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
    }
}
