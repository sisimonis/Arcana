package org.exodusstudio.arcana.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.exodusstudio.arcana.Arcana;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class PopupWidget extends AbstractWidget {

    private ResourceLocation texture;
    private static boolean Corrupt = false;
    private final Runnable onClose;
    private final int textureIndex;

    public PopupWidget(int x, int y, int width, int height, Component message, int textureIndex, Runnable onClose) {
        super(x, y, width, height, message);
        this.textureIndex = textureIndex;
        this.onClose = onClose;
        updateTexture();
    }

    public int getTextureIndex() {
        return textureIndex;
    }

    private void updateTexture() {
        if (!Corrupt) {
            this.texture = ResourceLocation.fromNamespaceAndPath(
                    Arcana.MODID,
                    "textures/gui/interior_memoriam/widget_" + textureIndex + ".png"
            );
        } else {
            this.texture = ResourceLocation.fromNamespaceAndPath(
                    Arcana.MODID,
                    "textures/gui/interior_memoriam/corrupt_widget.png"
            );
        }
    }

    public void setCorrupt(boolean corrupt) {
        Corrupt = corrupt;
        updateTexture();
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int x, int y, float v) {
        if (texture != null) {
            guiGraphics.blit(RenderType::guiTextured,texture, getX(), getY(), 0, 0, width, height, width, height);
        }

        // Define X hitbox
        int closeX = getX() + width - 15;
        int closeY = getY() + 9;
        int closeSize = 18;

        // Check if mouse is over the X (not the whole widget)
        boolean isHoveringX = x >= closeX && y <= closeX + closeSize
                && x >= closeY && y <= closeY + closeSize;

        int normalColor = 0xFFFFFF;
        int hoveringColor = 0x000000;
        int wgColor = isHoveringX ? hoveringColor : normalColor;
        String Wtext = (!Corrupt) ? "X" :"̇/";


        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(
                getX() + width - 15, // Adjust X position
                getY() + 9,          // Y position
                0
        );
        guiGraphics.pose().scale(2F, 2F, 2F); //scale
        guiGraphics.drawString(
                Minecraft.getInstance().font,
                Wtext,
                0, 0, // Draw at translated position
                wgColor,
                false
        );
        guiGraphics.pose().popPose();

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(isMouseOver(mouseX, mouseY)){
            int closeX = getX() + width - 15;
            int closeY = getY() + 9;
            int closeSize = 18;

            if (mouseX >= closeX && mouseX <= closeX + closeSize &&
                    mouseY >= closeY && mouseY <= closeY + closeSize) {
                if (onClose != null) onClose.run();
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {

    }
}
