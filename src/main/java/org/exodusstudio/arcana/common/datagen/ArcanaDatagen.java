package org.exodusstudio.arcana.common.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.exodusstudio.arcana.Arcana;

@EventBusSubscriber(modid = Arcana.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ArcanaDatagen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event)
    {
        // TODO : fix this
        /*PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new ArcanaEnUsGenerator(output));
        event.addProvider(new ArcanaItemModelProvider(output));


        var prov = gen.addProvider(event.includeServer(), new ArcanaRegistrySetGenerator(gen.getPackOutput(), event.getLookupProvider())).getRegistryProvider();
        gen.addProvider(event.includeServer(), new ArcanaDataMapGenerator(gen.getPackOutput(), prov));*/
    }
}
