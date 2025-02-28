package org.exodusstudio.arcana.common.data.theorie;

import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class Theory {
    private final int id;
    private final String research_name;
    private final Supplier<Block> relatedBlock;

    public Theory(int id, String research_name, Supplier<Block> relatedBlock)
    {
        this.id = id;
        this.research_name = research_name;
        this.relatedBlock = relatedBlock;
    }

    public String getResearchName()
    {
        return research_name;
    }

    public int getId()
    {
        return id;
    }

    public Block getRelatedBlock()
    {
        return relatedBlock.get();
    }
}
