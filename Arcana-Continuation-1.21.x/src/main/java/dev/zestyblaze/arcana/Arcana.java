package dev.zestyblaze.arcana;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Arcana.MODID)
public class Arcana {
    public static final String MODID = "arcana";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation id(String value) {
        return ResourceLocation.fromNamespaceAndPath(MODID, value);
    }

    public Arcana(IEventBus modEventBus, ModContainer modContainer) {}
}
