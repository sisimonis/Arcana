package org.exodusstudio.arcana.common.event;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.client.Keybindings;
import org.exodusstudio.arcana.client.gui.InteriumMemoriamScreen;
import org.exodusstudio.arcana.common.registry.BlockRegistry;
import org.exodusstudio.arcana.common.registry.ItemRegistry;

@EventBusSubscriber(modid = Arcana.MODID)
public class ArcanaModEvents {


    @SubscribeEvent
    public static void onAdvancementGet (AdvancementEvent.AdvancementEarnEvent event){
        if(event.getEntity() instanceof ServerPlayer player){
            AdvancementHolder advancement = event.getAdvancement();
            if (advancement.id().toString().equals("arcana:arcana/root")) {
                player.sendSystemMessage(Component.translatable("message." + Arcana.MODID,
                        Component.translatable(Keybindings.INSTANCE.InteriorMemoriam.getKey().getDisplayName().getString())));
                ItemStack itemStack = new ItemStack(ItemRegistry.OLD_NOTE.get());
                // Get player's looking direction and calculate offset
                Vec3 lookVec = player.getLookAngle().normalize(); // Get normalized look direction
                double dropX = player.getX() + lookVec.x; // Offset by 1 block in X direction
                double dropY = player.getY() + 1.0; // Slightly above the player's feet
                double dropZ = player.getZ() + lookVec.z; // Offset by 1 block in Z direction
                // Create the item entity at the offset position
                ItemEntity itemEntity = new ItemEntity(player.level(), dropX, dropY, dropZ, itemStack);
                itemEntity.setPickUpDelay(40);
                player.level().addFreshEntity(itemEntity);

            }
        }
    }


    @SubscribeEvent
    public static void onDeathEvent(PlayerEvent.PlayerRespawnEvent event){
            Minecraft.getInstance().gameRenderer.togglePostEffect();

    }


    //Client side:



    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        InteriumMemoriamScreen.updateWidgetFading();
        if (player != null && Keybindings.INSTANCE.InteriorMemoriam.consumeClick()) {
            player.playSound(SoundEvents.APPLY_EFFECT_BAD_OMEN);
            minecraft.setScreen(new InteriumMemoriamScreen());
        }
    }

}