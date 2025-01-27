package org.exodusstudio.arcana.item;

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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

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
                if (currentItemStack.getItem().equals(Items.MAP)) {
                    itemFound = true;
                    itemStack = currentItemStack;
                    itemStackIndex = i;
                    break;
                }
            }

            if(itemFound) {
                playerInventory.removeItem(itemStackIndex, 1);
                playerInventory.add(new ItemStack(ArcanaItems.SCRIBBLED_NOTE.get()));
            } else {
                //No empty map on his inventory
                player.displayClientMessage(Component.translatable("arcana.message.scribbling_tool_no_map"), true);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
