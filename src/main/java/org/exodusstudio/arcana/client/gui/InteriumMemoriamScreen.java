package org.exodusstudio.arcana.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.arcana.Arcana;

public class InteriumMemoriamScreen extends Screen {

    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(Arcana.MODID,
            "post_effect/interiormemoriamshader.json");


    public InteriumMemoriamScreen() {
        super(Component.literal("InteriorMemoriam"));
    }


    @Override
    public void render(GuiGraphics Graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(Graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().gameRenderer.togglePostEffect();
        super.onClose();
    }
}
