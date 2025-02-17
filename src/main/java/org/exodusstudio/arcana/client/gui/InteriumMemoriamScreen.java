package org.exodusstudio.arcana.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import org.exodusstudio.arcana.Arcana;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InteriumMemoriamScreen extends Screen  {

    private void renderTextureOverlay(GuiGraphics guiGraphics) {
        int i = ARGB.white((float) 1.5);
        guiGraphics.blit(RenderType::guiTexturedOverlay, InteriumMemoriamScreen.INTERIOR_MEMORIAM, 0, 0, 0.0F, 0.0F, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight(), i);
    }

    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(Arcana.MODID, "interior_memoriam_shader" );

    @Override
    public void renderBackground(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

    }

    private static final ResourceLocation INTERIOR_MEMORIAM = ResourceLocation.fromNamespaceAndPath(Arcana.MODID,"textures/misc/memoriam_overlay.png");


    private static final List<DragWidget> customWidgets = new ArrayList<>();
    private static int lastX = -1;
    private static int lastY = -1;
    private static boolean shouldAddWidget = false;
    @Override
    protected void init() {
        super.init();
        for (DragWidget widget : customWidgets) {
            addRenderableWidget(widget);
            widget.updatePosition(widget.getCurrentX(), widget.getCurrentY());
        }

        if(shouldAddWidget){
            addCustomWidget();
            shouldAddWidget = false;
        }
    }

    public static void setShouldAddWidget(boolean value) {
        shouldAddWidget = value;
    }

    public void addCustomWidget(){
        int startX = lastX != -1 ? lastX : this.width/2-20;
        int startY = lastY != -1 ? lastY : this.height/2-20;
        DragWidget dragWidget = new DragWidget(
                startX, // Start X position
                startY, // Start Y position
                68, // Widget width
                83, // Widget height
                this.width, // Screen width
                this.height, // Screen height
                Component.literal("Drag me")
        );
        customWidgets.add(dragWidget);
        addRenderableWidget(dragWidget);
    }


    public InteriumMemoriamScreen() {
        super(Component.literal("InteriorMemoriam"));
        Minecraft.getInstance().gameRenderer.setPostEffect(BACKGROUND);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderTextureOverlay(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
    }




    @Override
    public void onClose() {
        Minecraft.getInstance().gameRenderer.clearPostEffect();
        if(!customWidgets.isEmpty()){
            DragWidget lastWidget = customWidgets.getLast();
            lastY = lastWidget.getY();
            lastX = lastWidget.getX();
        }

        super.onClose();
    }
}
