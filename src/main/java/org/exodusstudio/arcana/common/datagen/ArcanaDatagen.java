package org.exodusstudio.arcana.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.datagen.provider.ArcanaEnUsGenerator;
import org.exodusstudio.arcana.common.datagen.provider.ArcanaModelProvider;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Arcana.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ArcanaDatagen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event)
    {
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new ArcanaEnUsGenerator(output));
        event.addProvider(new ArcanaModelProvider(output));

        /*
        var prov = gen.addProvider(event.includeServer(), new ArcanaRegistrySetGenerator(gen.getPackOutput(), event.getLookupProvider())).getRegistryProvider();
        gen.addProvider(event.includeServer(), new ArcanaDataMapGenerator(gen.getPackOutput(), prov));*/
    }
}
