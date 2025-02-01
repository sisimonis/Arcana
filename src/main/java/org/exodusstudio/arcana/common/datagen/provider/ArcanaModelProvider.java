package org.exodusstudio.arcana.common.datagen.provider;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.registry.BlockRegistry;
import org.exodusstudio.arcana.common.registry.ItemRegistry;

import java.util.stream.Stream;

public class ArcanaModelProvider extends ModelProvider {

    public ArcanaModelProvider(PackOutput output) {
        super(output, Arcana.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        //Items
        itemModels.generateFlatItem(ItemRegistry.SCRIBBLING_TOOL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemRegistry.SCRIBBLED_NOTE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemRegistry.NHIL_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BlockRegistry.RESEARCH_TABLE.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemRegistry.NHIL_POWDER.get(), ModelTemplates.FLAT_ITEM);

        //Blocks
        //blockModels.createTrivialCube(BlockRegistry.RESEARCH_TABLE.get());
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return Stream.empty(); //Todo
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return Stream.empty();
    }
}
