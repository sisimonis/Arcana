package org.exodusstudio.arcana;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exodusstudio.arcana.client.Keybindings;
import org.exodusstudio.arcana.item.ArcanaCreativeModeTabs;
import org.exodusstudio.arcana.item.ArcanaItems;

@Mod(Arcana.MODID)
public class Arcana {
    public static final String MODID = "arcana";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation id(String value) {
        return ResourceLocation.fromNamespaceAndPath(MODID, value);
    }

    public Arcana(IEventBus modEventBus, ModContainer modContainer) {
        ArcanaItems.register(modEventBus);
        ArcanaCreativeModeTabs.register(modEventBus);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{

        @SubscribeEvent
        public static void registerKey(RegisterKeyMappingsEvent event){
            event.register(Keybindings.INSTANCE.InteriorMemoriam);
        }
    }
}
