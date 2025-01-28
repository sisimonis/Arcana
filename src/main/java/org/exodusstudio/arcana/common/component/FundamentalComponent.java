package org.exodusstudio.arcana.common.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record FundamentalComponent(ItemStack item) {
    public FundamentalComponent(ItemStack item) {
        this.item = item;
    }
    public static final Codec<FundamentalComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ItemStack.CODEC.fieldOf("ite,<m").forGetter(FundamentalComponent::item)
            ).apply(instance, FundamentalComponent::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, FundamentalComponent> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, FundamentalComponent::item,
            FundamentalComponent::new
    );
}
