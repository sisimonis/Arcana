package dev.zestyblaze.arcana.event;

import dev.zestyblaze.arcana.Arcana;
import dev.zestyblaze.arcana.data.State;
import dev.zestyblaze.arcana.data.Structure;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(modid = Arcana.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ArcanaRegistries {
    public static final ResourceKey<Registry<State>> STATE_KEY = ResourceKey.createRegistryKey(Arcana.id("states"));
    public static final ResourceKey<Registry<Structure>> STRUCTURE_KEY = ResourceKey.createRegistryKey(Arcana.id("structures"));

    public static final RegistryFixedCodec<State> STATE_REGISTRY = RegistryFixedCodec.create(STATE_KEY);
    public static final RegistryFixedCodec<Structure> STRUCTURE_REGISTRY = RegistryFixedCodec.create(STRUCTURE_KEY);

    @SubscribeEvent
    public static void registerDatapacks(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(STATE_KEY, State.CODEC, State.CODEC);
        event.dataPackRegistry(STRUCTURE_KEY, Structure.CODEC, Structure.CODEC);
    }
}
