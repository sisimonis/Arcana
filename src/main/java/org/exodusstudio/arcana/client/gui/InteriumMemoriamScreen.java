package org.exodusstudio.arcana.client.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.storage.LevelResource;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.data.WidgetData;
import org.jetbrains.annotations.NotNull;
import org.jline.reader.Widget;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


    private final List<DragWidget> customWidgets = new ArrayList<>();
    private static final int lastX = -1;
    private static final int lastY = -1;
    private static boolean shouldAddWidget = false;
    private boolean isDataLoaded = false;
    private final List<WidgetData> savedWidgetData = new ArrayList<>();
    private void checkWidgetCount() {
        if (customWidgets.size() == 5) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                StringBuilder message = new StringBuilder("Widget UUIDs: ");
                for (int i = 0; i < customWidgets.size(); i++) {
                    if (i > 0) {
                        message.append(", ");
                    }
                    message.append(customWidgets.get(i).getUuid());
                }
                minecraft.player.displayClientMessage(
                        Component.literal(message.toString()),
                        false
                );
            }
        }
    }
    @Override
    protected void init() {
        super.init();
        customWidgets.clear();
        this.clearWidgets();

        if (!customWidgets.isEmpty()) {
            savedWidgetData.clear();
            for (DragWidget widget : customWidgets) {
                savedWidgetData.add(new WidgetData(widget.getX(), widget.getY(), widget.getUuid()));
            }
        }

        loadWidgetData();
        isDataLoaded = true;

        for (WidgetData data : savedWidgetData) {
            // Convert relative coordinates to absolute coordinates
            int absoluteX = (int) (data.getRelativeX() * this.width);
            int absoluteY = (int) (data.getRelativeY() * this.height);

            DragWidget dragWidget = new DragWidget(
                    absoluteX, absoluteY,
                    68, 83,
                    Component.literal("Drag me"),
                    data.getUuid(),
                    this.savedWidgetData
            );

            checkWidgetCount();
            customWidgets.add(dragWidget);
            addRenderableWidget(dragWidget);
        }

        if (shouldAddWidget) {
            addCustomWidget();
            shouldAddWidget = false;
        }
    }


    private void loadWidgetData() {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        if (server == null) return; // Ensure we're in a singleplayer world

        Path worldPath = server.getWorldPath(LevelResource.ROOT).resolve("data");

        Path path = worldPath.resolve("widget_data.json");
        if (Files.exists(path)) {
            try {
                String json = Files.readString(path);
                DataResult<List<WidgetData>> result = WidgetData.CODEC.listOf().parse(JsonOps.INSTANCE, JsonParser.parseString(json));
                result.result().ifPresent(data -> {
                    savedWidgetData.clear();
                    savedWidgetData.addAll(data);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setShouldAddWidget(boolean value) {
        shouldAddWidget = value;
    }

    public void addCustomWidget() {
        int startX = this.width / 2 - 20;
        int startY = this.height / 2 - 20;
        UUID uuid = UUID.randomUUID(); // Generate a new UUID
        DragWidget dragWidget = new DragWidget(
                startX, // Start X position
                startY, // Start Y position
                68, // Widget width
                83, // Widget height
                Component.literal("Drag me"),
                uuid,
                this.savedWidgetData
        );
        customWidgets.add(dragWidget);
        addRenderableWidget(dragWidget);
        savedWidgetData.add(new WidgetData(startX, startY,uuid));
        checkWidgetCount();
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
        saveWidgetData();
        super.onClose();
    }

    private void saveWidgetData() {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        if (server == null) return;

        Path worldPath = server.getWorldPath(LevelResource.ROOT).resolve("data");

        try {
            Files.createDirectories(worldPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Convert absolute coordinates to relative coordinates before saving
        List<WidgetData> relativeData = new ArrayList<>();
        for (DragWidget widget : customWidgets) {
            double relativeX = (double) widget.getX() / this.width;
            double relativeY = (double) widget.getY() / this.height;
            relativeData.add(new WidgetData(relativeX, relativeY, widget.getUuid()));
        }

        // Save the relative data
        DataResult<JsonElement> result = WidgetData.CODEC.listOf().encodeStart(JsonOps.INSTANCE, relativeData);
        result.result().ifPresent(json -> {
            Path path = worldPath.resolve("widget_data.json");
            try {
                Files.writeString(path, json.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
