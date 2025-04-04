package org.exodusstudio.arcana.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.component.WidgetData;

import java.util.List;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class DragWidget extends AbstractWidget {
    private int offsetX, offsetY;
    private boolean dragging;
    private boolean hasDragged;
    private static final ResourceLocation widget_texture = ResourceLocation.fromNamespaceAndPath(Arcana.MODID, "textures/gui/interior_memoriam/ancient_torn_note.png");
    private final UUID uuid;
    private final ResourceLocation hovering_texture = ResourceLocation.fromNamespaceAndPath(Arcana.MODID,
            "textures/gui/interior_memoriam/highlights.png");
    private final List<WidgetData> savedWidgetData;
    private final int textureIndex;
    public float alpha = 1.0f;
    private final InteriumMemoriamScreen parentScreen;

    public DragWidget(int x, int y, int width, int height, Component message, UUID uuid, List<WidgetData> savedWidgetData, int textureIndex, InteriumMemoriamScreen parentscreen) {
        super(x, y, width, height, message);
        this.uuid = uuid;
        this.savedWidgetData = savedWidgetData;
        this.textureIndex = textureIndex;
        this.parentScreen = parentscreen;
    }

    public UUID getUuid() { return uuid; }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) { this.alpha = Math.clamp(alpha, 0, 1); }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
        int color = ARGB.color((int)(alpha * 255), 255, 255, 255);
        ResourceLocation currentTexture = isMouseOver(x, y) ? hovering_texture : widget_texture;
        guiGraphics.blit(RenderType::guiTextured,currentTexture, getX(), getY(),0,0,width, height, width, height,color);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY) && button == 0) { // Left-click
            dragging = true;
            hasDragged = false;
            offsetX = (int) mouseX - getX();
            offsetY = (int) mouseY - getY();
            return true;
        }
        return false;
    }




    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (dragging && button == 0) { // Left mouse button released
            dragging = false;

            if(!hasDragged){
                parentScreen.openPopup(this.textureIndex);
                Minecraft mc = Minecraft.getInstance();
                if(mc.screen != null){
                    int centerX = mc.screen.width/2 - this.width/2;
                    int centerY = mc.screen.height/2 - this.height/2;
                    setPosition(centerX, centerY);
                }
            }

            // Update saved position in savedWidgetData
            Minecraft mc = Minecraft.getInstance();
            if(mc.screen != null){
                int screenWidth = mc.screen.width;
                int screenHeight = mc.screen.height;
                for (WidgetData data : savedWidgetData) {
                    if (data.getUuid().equals(this.uuid)) { // Find the correct widget by ID
                        double relativeX = (double) this.getX() / screenWidth;
                        double relativeY = (double) this.getY() / screenHeight;
                        data.setRelativeX(relativeX);
                        data.setRelativeY(relativeY);
                        break;
                    }
                }
            }

        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (dragging && button == 0) { // Check if left mouse button is pressed
            hasDragged = true;
            int newX = (int) mouseX - offsetX;
            int newY = (int) mouseY - offsetY;
            setPosition(newX, newY);
            return true;
        }
        return false;
    }


    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, getMessage());
    }

    public int getTextureIndex() {
        return textureIndex;
    }
}
