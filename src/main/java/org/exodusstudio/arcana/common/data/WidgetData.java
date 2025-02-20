package org.exodusstudio.arcana.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.UUID;

public class WidgetData {
    private int x;
    private int y;
    private final UUID uuid;


    public WidgetData(int x, int y, UUID uuid) {
        this.x = x;
        this.y = y;
        this.uuid = uuid;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public UUID getUuid() {
        return uuid;
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public static final Codec<WidgetData> CODEC = RecordCodecBuilder.create(widgetDataInstance ->
            widgetDataInstance.group(
                    Codec.INT.fieldOf("x").forGetter(WidgetData::getX),
                    Codec.INT.fieldOf("y").forGetter(WidgetData::getY),
                    Codec.STRING.xmap(UUID::fromString, UUID::toString).fieldOf("uuid").forGetter(WidgetData::getUuid)
            ).apply(widgetDataInstance, WidgetData::new)

    );

}
