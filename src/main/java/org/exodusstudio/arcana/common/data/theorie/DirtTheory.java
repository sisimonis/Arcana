package org.exodusstudio.arcana.common.data.theorie;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class DirtTheory extends Theory{
    public DirtTheory() {
        super(1, "Dirt Theory", () -> Blocks.DIRT, null);
    }
}
