package org.exodusstudio.arcana.common.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.data.State;
import org.exodusstudio.arcana.common.data.Structure;
import org.exodusstudio.arcana.common.datamap.ArcanaDataHolder;
import org.exodusstudio.arcana.common.datamap.ArcanaDataMaps;
import org.exodusstudio.arcana.common.registry.TagRegistry;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = Arcana.MODID)
public class ItemEvents {
    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent event) {
        ArcanaDataHolder data = event.getItemStack().getItem().builtInRegistryHolder().getData(ArcanaDataMaps.DATA_HOLDER);
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

    @SubscribeEvent
    public static void entityLeaveLevelEvent(EntityLeaveLevelEvent event) {
        if (event.getEntity() instanceof ItemEntity itemEntity && event.getLevel().getBlockState(itemEntity.blockPosition()).is(Blocks.FIRE)) {
            if (itemEntity.getItem().is(TagRegistry.FUNDAMENTAL)) {
                // TODO : spawn entity
                Arcana.LOGGER.error("Entity throwed in fire : {}", itemEntity);
            }
        }
    }
}
