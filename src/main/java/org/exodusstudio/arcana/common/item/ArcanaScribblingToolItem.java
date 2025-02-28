package org.exodusstudio.arcana.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.component.ScribbledNoteData;
import org.exodusstudio.arcana.common.data.theorie.Theory;
import org.exodusstudio.arcana.common.data.theorie.TheoryRegistry;
import org.exodusstudio.arcana.common.registry.DataComponentRegistry;
import org.exodusstudio.arcana.common.registry.ItemRegistry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class ArcanaScribblingToolItem extends Item {
    public ArcanaScribblingToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = world.getBlockState(pos);

        if(!world.isClientSide)
        {
            Inventory playerInventory = player.getInventory();

            boolean itemFound = false;
            ItemStack itemStack = ItemStack.EMPTY;
            int itemStackIndex = 0;

            for(int i = 0; i < playerInventory.getContainerSize(); i++) {
                ItemStack currentItemStack = playerInventory.getItem(i);
                if (currentItemStack.getItem().equals(Items.PAPER)) {
                    itemFound = true;
                    itemStack = currentItemStack;
                    itemStackIndex = i;
                    break;
                }
            }

            if(itemFound) {
                //Find the theory that correspond the block clicked
                LinkedHashMap<Integer, Theory> theories = TheoryRegistry.getAllTheories();
                Theory matchingTheory = null;
                for(Map.Entry<Integer, Theory> entry : theories.entrySet())
                {
                    Theory theory = entry.getValue();
                    if(theory.getRelatedBlock() == blockState.getBlock())
                    {
                        matchingTheory = theory;
                        break;
                    }
                }

                if(matchingTheory != null)
                {
                    playerInventory.removeItem(itemStackIndex, 1);
                    ItemStack scribbledNote = new ItemStack(ItemRegistry.SCRIBBLED_NOTE.get());
                    scribbledNote.set(DataComponentRegistry.SCRIBBLED_NOTE_DATA.get(), new ScribbledNoteData(matchingTheory.getResearchName()));
                    playerInventory.add(scribbledNote);
                    Arcana.LOGGER.info("New research : " + matchingTheory.getResearchName());
                }
            } else {
                //No empty map on his inventory
                player.displayClientMessage(Component.translatable("arcana.message.scribbling_tool_no_map"), true);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
