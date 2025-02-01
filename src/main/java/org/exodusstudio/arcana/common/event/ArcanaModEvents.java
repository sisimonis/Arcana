package org.exodusstudio.arcana.common.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.client.Keybindings;
import org.exodusstudio.arcana.client.gui.InteriumMemoriamScreen;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = Arcana.MODID)
public class ArcanaModEvents {

    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(Arcana.MODID, "interior_memoriam_shader" );




    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (Keybindings.INSTANCE.InteriorMemoriam.isDown() && player != null) {
            Keybindings.INSTANCE.InteriorMemoriam.consumeClick();
            player.playSound(SoundEvents.APPLY_EFFECT_BAD_OMEN);
            Minecraft.getInstance().gameRenderer.setPostEffect(BACKGROUND);
            minecraft.setScreen(new InteriumMemoriamScreen());
        }
    }

}
