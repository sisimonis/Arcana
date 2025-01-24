package dev.zestyblaze.arcana.datagen.provider;

import dev.zestyblaze.arcana.Arcana;
import dev.zestyblaze.arcana.event.ArcanaRegistries;
import dev.zestyblaze.arcana.world.States;
import dev.zestyblaze.arcana.world.Structures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ArcanaRegistrySetGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ArcanaRegistries.STATE_KEY, States::bootstrap)
            .add(ArcanaRegistries.STRUCTURE_KEY, Structures::bootstrap);

    public ArcanaRegistrySetGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Arcana.MODID));
    }
}
