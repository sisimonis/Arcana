package org.exodusstudio.arcana.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.client.Keybindings;
import org.exodusstudio.arcana.client.gui.InteriumMemoriamScreen;

@EventBusSubscriber(modid = Arcana.MODID)
public class ArcanaModEvents {



    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (Keybindings.INSTANCE.InteriorMemoriam.isDown() && player != null) {
            Keybindings.INSTANCE.InteriorMemoriam.consumeClick();
            player.playSound(SoundEvents.APPLY_EFFECT_BAD_OMEN);
            Minecraft.getInstance().gameRenderer.setPostEffect(ResourceLocation.fromNamespaceAndPath(Arcana.MODID,"interior_memoriam_shader"));
            minecraft.setScreen(new InteriumMemoriamScreen());

        }
    }

}
