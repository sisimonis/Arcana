package org.exodusstudio.arcana.common.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.registry.BlockRegistry;
import org.exodusstudio.arcana.common.registry.ItemRegistry;

import java.util.function.Supplier;

public class ArcanaCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Arcana.MODID);

    public static final Supplier<CreativeModeTab> ARCANA_CREATIVE_TAB =
            CREATIVE_MODE_TABS.register("arcana_creative_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.arcana.arcana_creative_tab"))
                    .icon(()->new ItemStack(ItemRegistry.SCRIBBLING_TOOL.get()))
                    .displayItems((pParameters, pOutput)->{
                      pOutput.accept(ItemRegistry.SCRIBBLING_TOOL);
                      pOutput.accept(ItemRegistry.SCRIBBLED_NOTE);
                      pOutput.accept(ItemRegistry.NHIL_CRYSTAL);
                      pOutput.accept(ItemRegistry.NHIL_POWDER);
                      pOutput.accept(BlockRegistry.RESEARCH_TABLE);
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
