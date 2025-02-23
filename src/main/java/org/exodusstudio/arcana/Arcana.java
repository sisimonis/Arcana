package org.exodusstudio.arcana;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.IdMappingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exodusstudio.arcana.client.Keybindings;
import org.exodusstudio.arcana.client.gui.ResearchTableScreen;
import org.exodusstudio.arcana.common.data.theorie.TheoryRegistry;
import org.exodusstudio.arcana.common.item.ArcanaCreativeModeTabs;
import org.exodusstudio.arcana.common.registry.*;

import javax.xml.crypto.Data;

@Mod(Arcana.MODID)
public class Arcana {
    public static final String MODID = "arcana";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation id(String value) {
        return ResourceLocation.fromNamespaceAndPath(MODID, value);
    }

    public Arcana(IEventBus modEventBus, ModContainer modContainer) {
        ItemRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        ArcanaCreativeModeTabs.register(modEventBus);
        BlockEntityRegistry.register(modEventBus);
        DataComponentRegistry.register(modEventBus);
        DataAttachmentRegistry.register(modEventBus);
        MenuRegistry.register(modEventBus);
        TheoryRegistry.RegisterTheories();
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{

        @SubscribeEvent
        public static void registerKey(RegisterKeyMappingsEvent event){
            event.register(Keybindings.INSTANCE.InteriorMemoriam);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event)
        {
            event.register(MenuRegistry.RESEARCH_TABLE_MENU.get(), ResearchTableScreen::new);
        }
    }
}
