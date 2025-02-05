package org.exodusstudio.arcana.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import org.exodusstudio.arcana.Arcana;
import org.jline.reader.Widget;

public class InteriumMemoriamScreen extends Screen  {

    private void renderTextureOverlay(GuiGraphics guiGraphics, ResourceLocation shaderLocation, float alpha) {
        int i = ARGB.white(alpha);
        guiGraphics.blit(RenderType::guiTexturedOverlay, shaderLocation, 0, 0, 0.0F, 0.0F, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight(), i);
    }

    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(Arcana.MODID, "interior_memoriam_shader" );

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft.getInstance().gameRenderer.setPostEffect(BACKGROUND);
    }

    private static final ResourceLocation INTERIOR_MEMORIAM = ResourceLocation.fromNamespaceAndPath(Arcana.MODID,"textures/misc/memoriam_overlay.png");

    private DragWidget dragWidget;
    private static int lastX = -1;
    private static int lastY = -1;
    @Override
    protected void init() {
        super.init();
        int startX = lastX != -1 ? lastX : this.width/2-20;
        int startY = lastY != -1 ? lastY : this.width/2-20;
        dragWidget = new DragWidget(
                startX, // Start X position
                startY, // Start Y position
                146, // Widget width
                180, // Widget height
                this.width, // Screen width
                this.height, // Screen height
                Component.literal("Drag me")
        );
        addRenderableWidget(dragWidget);
    }


    public InteriumMemoriamScreen() {
        super(Component.literal("InteriorMemoriam"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderTextureOverlay(graphics, INTERIOR_MEMORIAM, 1.5f);
        super.render(graphics, mouseX, mouseY, partialTicks);
    }


    @Override
    public void onClose() {
        Minecraft.getInstance().gameRenderer.togglePostEffect();
        lastY = dragWidget.getY();
        lastX = dragWidget.getX();
        super.onClose();
    }
}
