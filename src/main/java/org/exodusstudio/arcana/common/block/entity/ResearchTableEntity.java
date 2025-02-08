package org.exodusstudio.arcana.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.exodusstudio.arcana.common.registry.BlockEntityRegistry;

public class ResearchTableEntity extends BlockEntity {

    public ResearchTableEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistry.RESEARCH_TABLE_BE.get(), pos, blockState);
    }


}
