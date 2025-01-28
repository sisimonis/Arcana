package org.exodusstudio.arcana.client.gui;

import net.minecraft.client.Minecraft;
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

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    }

    private static final ResourceLocation INTERIOR_MEMORIAM = ResourceLocation.fromNamespaceAndPath(Arcana.MODID,"textures/misc/memoriam_overlay.png");


    public InteriumMemoriamScreen() {
        super(Component.literal("InteriorMemoriam"));
    }

    private DragWidget dragWidget;
    @Override
    protected void init() {
        super.init();
        dragWidget = new DragWidget(
                this.width / 2 - 50, // Start X position
                this.height / 2 - 20, // Start Y position
                146, // Widget width
                180, // Widget height
                this.width, // Screen width
                this.height, // Screen height
                Component.literal("Drag me")
        );
        addRenderableWidget(dragWidget);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderTextureOverlay(graphics, INTERIOR_MEMORIAM, 1.5f);
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().gameRenderer.togglePostEffect();
        super.onClose();
    }
}
