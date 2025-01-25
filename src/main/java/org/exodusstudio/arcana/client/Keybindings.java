package org.exodusstudio.arcana.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.exodusstudio.arcana.Arcana;

public final class Keybindings {

    public static final Keybindings INSTANCE = new Keybindings();

    private Keybindings() {}

    private static final String CATEGORY = "key.categories." + Arcana.MODID;

    public final KeyMapping InteriorMemoriam = new KeyMapping(
            "key." + Arcana.MODID + ".memoriam",
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_L, -1),
            CATEGORY
    );

}
