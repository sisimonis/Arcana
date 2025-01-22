package dev.zestyblaze.arcana.datagen;

import dev.zestyblaze.arcana.Arcana;
import dev.zestyblaze.arcana.datagen.provider.ArcanaDataMapGenerator;
import dev.zestyblaze.arcana.datagen.provider.ArcanaEnUsGenerator;
import dev.zestyblaze.arcana.datagen.provider.ArcanaRegistrySetGenerator;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Arcana.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ArcanaDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        gen.addProvider(event.includeClient(), new ArcanaEnUsGenerator(gen.getPackOutput()));

        var prov = gen.addProvider(event.includeServer(), new ArcanaRegistrySetGenerator(gen.getPackOutput(), event.getLookupProvider())).getRegistryProvider();
        gen.addProvider(event.includeServer(), new ArcanaDataMapGenerator(gen.getPackOutput(), prov));
    }
}
