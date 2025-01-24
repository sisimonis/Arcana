package dev.zestyblaze.arcana.event;

import dev.zestyblaze.arcana.Arcana;
import dev.zestyblaze.arcana.data.State;
import dev.zestyblaze.arcana.data.Structure;
import dev.zestyblaze.arcana.datamap.ArcanaDataMaps;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = Arcana.MODID)
public class ItemEvents {
    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent event) {
        var data = event.getItemStack().getItem().builtInRegistryHolder().getData(ArcanaDataMaps.DATA_HOLDER);
        if (data != null) {
            List<Holder<State>> states = data.states();
            List<String> stringList = new ArrayList<>();
            for (Holder<State> state : states) {
                stringList.add(Component.translatable(state.value().key()).getString());
            }
            event.getToolTip().add(Component.literal(String.join(", ", stringList)));

            List<Holder<Structure>> structures = data.structures();
            if (Screen.hasShiftDown()) {
                for (Holder<Structure> structure : structures) {
                    event.getToolTip().add(Component.literal(" -> " + Component.translatable(structure.value().key()).getString()));
                }
            } else if (!structures.isEmpty()) {
                event.getToolTip().add(Component.translatable("tooltip.arcana.shift_to_view").withStyle(ChatFormatting.DARK_GRAY));
            }
        }
    }
}
