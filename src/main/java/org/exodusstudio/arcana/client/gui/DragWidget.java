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
import org.exodusstudio.arcana.common.data.WidgetData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.exodusstudio.arcana.client.gui.InteriumMemoriamScreen.savedWidgetData;


public class DragWidget extends AbstractWidget {
    private int offsetX, offsetY;
    private boolean dragging;
    private static final ResourceLocation WIDGET_TEXTURE = ResourceLocation.fromNamespaceAndPath(Arcana.MODID, "textures/gui/ancient_torn_note.png");
    private final UUID uuid;
    
    public DragWidget(int x, int y, int width, int height, Component message, UUID uuid) {
        super(x, y, width, height, message);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
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
        if (dragging && button == 0) { // Left mouse button released
            dragging = false;

            // Update saved position in savedWidgetData
            for (WidgetData data : savedWidgetData) {
                if (data.getUuid().equals(this.uuid)) { // Find the correct widget by ID
                    data.setX(this.getX());
                    data.setY(this.getY());
                    break;
                }
            }
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (dragging && button == 0) { // Check if left mouse button is pressed
            int newX = (int) mouseX - offsetX;
            int newY = (int) mouseY - offsetY;
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



    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, getMessage());
    }
}
