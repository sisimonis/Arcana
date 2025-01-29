package org.exodusstudio.arcana.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.arcana.Arcana;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockRegistry {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Arcana.MODID);

    private static final Map<DeferredBlock<?>, Supplier<Item>> BLOCK_TO_ITEM = new HashMap<>();


    public static final DeferredBlock<Block> RESEARCH_TABLE = registerBasicBlock("research_table",
            BlockBehaviour.Properties.of().strength(5.0F, 6.0F).requiresCorrectToolForDrops(),
            new Item.Properties().stacksTo(1));

    private static DeferredBlock<Block> registerBasicBlock(String name, BlockBehaviour.Properties blockProperties, Item.Properties itemProperties) {
        Supplier<Block> block = () -> new Block(blockProperties.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Arcana.MODID, name))));
        DeferredBlock<Block> toReturn = BLOCKS.register(name, block);
        Supplier<Item> itemSupplier = registerBlockItem(name, toReturn, itemProperties);
        BLOCK_TO_ITEM.put(toReturn, itemSupplier);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, Item.Properties itemProperties) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, itemProperties);
        return toReturn;
    }

    private static <T extends Block> Supplier<Item> registerBlockItem(String name, DeferredBlock<T> block, Item.Properties itemProperties) {
        return ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), itemProperties.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Arcana.MODID, name)))));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }

    public static Supplier<Item> getItemFromBlock(DeferredBlock<?> block){
        return BLOCK_TO_ITEM.getOrDefault(block, () -> null);
    }
}
