package org.exodusstudio.arcana.common.registry;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.component.FundamentalComponent;

import java.util.function.Supplier;

public class DataComponentRegistry {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Arcana.MODID);

    public static final Supplier<DataComponentType<FundamentalComponent>> FUNDAMENTAL_COMPONENT = REGISTRAR.registerComponentType(
            "fundamental",
            builder -> builder.persistent(FundamentalComponent.CODEC).networkSynchronized(FundamentalComponent.STREAM_CODEC)
    );
}
