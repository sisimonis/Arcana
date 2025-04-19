package org.exodusstudio.arcana.common.data.theorie;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class StoneTheory extends Theory{
    public StoneTheory() {
        super(2, "Stone Theory", () -> Blocks.STONE, null);
    }
}
