package org.exodusstudio.arcana.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.arcana.Arcana;

//no its not  in a drag queen/king
public class DragWidget extends AbstractWidget {
    private int offsetX, offsetY;
    private boolean dragging;
    private final int screenWidth, screenHeight;
    private static final ResourceLocation WIDGET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Arcana.MODID, "textures/gui/test_widget.png");


    public DragWidget(int x, int y, int width, int height, int screenWidth, int screenHeight, Component message) {
        super(x, y, width, height, message);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
        guiGraphics.blit(RenderType::guiTextured,
                WIDGET_TEXTURE,
                getX(), getY(),
                0, 0, width, height,
                width, height
        );

        // Optional: Draw text on top
        guiGraphics.drawCenteredString(
                Minecraft.getInstance().font,
                getMessage(),
                getX() + width / 2,
                getY() + height / 2 - 4,
                0xFFFFFF
        );
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY) && button == 0) { // Left-click to start dragging
            dragging = true;
            offsetX = (int) mouseX - getX();
            offsetY = (int) mouseY - getY();
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        dragging = false; // Stop dragging when released
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (dragging && button == 0) { // Follow mouse position within limits
            int newX = Math.max(0, Math.min((int) mouseX - offsetX, screenWidth - width));
            int newY = Math.max(0, Math.min((int) mouseY - offsetY, screenHeight - height));
            setPosition(newX, newY);
            return true;
        }
        return false;
    }



    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, getMessage());
    }
}
