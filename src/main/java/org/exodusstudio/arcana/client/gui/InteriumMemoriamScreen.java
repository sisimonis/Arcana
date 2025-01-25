package org.exodusstudio.arcana.client.gui;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import org.exodusstudio.arcana.Arcana;

public class InteriumMemoriamScreen extends Screen  {

    private void renderTextureOverlay(GuiGraphics guiGraphics, ResourceLocation shaderLocation, float alpha) {
        int i = ARGB.white(alpha);
        guiGraphics.blit(RenderType::guiTexturedOverlay, shaderLocation, 0, 0, 0.0F, 0.0F, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight(), i);
    }


    private static final ResourceLocation POWDER_SNOW_OUTLINE_LOCATION = ResourceLocation.withDefaultNamespace("textures/misc/powder_snow_outline.png");


    public InteriumMemoriamScreen() {
        super(Component.literal("InteriorMemoriam"));
    }


    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderTextureOverlay(graphics, POWDER_SNOW_OUTLINE_LOCATION, 3f);
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().gameRenderer.togglePostEffect();
        super.onClose();
    }
}
