package org.exodusstudio.arcana.client.gui;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.exodusstudio.arcana.Arcana;

import java.util.ArrayList;
import java.util.List;


public class DragWidget extends AbstractWidget {
    private int offsetX, offsetY;
    private boolean dragging;
    private final int screenWidth, screenHeight;
    private static final ResourceLocation WIDGET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Arcana.MODID, "textures/gui/ancient_torn_note.png");

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
        if (dragging && button == 0) { // Check if left mouse button is pressed
            int newX = Math.max(0, Math.min((int) mouseX - offsetX, screenWidth - width));
            int newY = Math.max(0, Math.min((int) mouseY - offsetY, screenHeight - height));
            setPosition(newX, newY);

            return true;
        }
        return false;
    }

    public void updatePosition(int newX, int newY) {
        this.setX(newX);
        this.setY(newY);
    }

    public int getCurrentX() {
        return this.getX();
    }

    public int getCurrentY() {
        return this.getY();
    }

    public static final Codec<DragWidget> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("x").forGetter(DragWidget::getCurrentX),
            Codec.INT.fieldOf("y").forGetter(DragWidget::getCurrentY),
            Codec.INT.fieldOf("width").forGetter(DragWidget::getWidth),
            Codec.INT.fieldOf("height").forGetter(DragWidget::getHeight)
    ).apply(instance, (x, y, width, height) -> new DragWidget(x, y, width, height, 0, 0, Component.literal("Drag me"))));



    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, getMessage());
    }
}
