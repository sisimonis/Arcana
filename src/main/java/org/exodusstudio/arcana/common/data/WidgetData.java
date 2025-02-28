package org.exodusstudio.arcana.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.UUID;

public class WidgetData {
    private double relativeX; // Percentage of screen width (0.0 to 1.0)
    private double relativeY; // Percentage of screen height (0.0 to 1.0)
    private final UUID uuid;

    public WidgetData(double relativeX, double relativeY, UUID uuid) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.uuid = uuid;
    }

    public double getRelativeX() {
        return relativeX;
    }

    public double getRelativeY() {
        return relativeY;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setRelativeX(double relativeX) {
        this.relativeX = relativeX;
    }

    public void setRelativeY(double relativeY) {
        this.relativeY = relativeY;
    }

    public static final Codec<WidgetData> CODEC = RecordCodecBuilder.create(widgetDataInstance ->
            widgetDataInstance.group(
                    Codec.DOUBLE.fieldOf("relativeX").forGetter(WidgetData::getRelativeX),
                    Codec.DOUBLE.fieldOf("relativeY").forGetter(WidgetData::getRelativeY),
                    Codec.STRING.xmap(UUID::fromString, UUID::toString).fieldOf("uuid").forGetter(WidgetData::getUuid)
            ).apply(widgetDataInstance, WidgetData::new)
    );

}
