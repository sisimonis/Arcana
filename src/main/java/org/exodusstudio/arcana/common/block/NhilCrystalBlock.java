package org.exodusstudio.arcana.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.exodusstudio.arcana.common.block.entity.NhilCrystalBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NhilCrystalBlock extends BaseEntityBlock {
    public static final MapCodec<NhilCrystalBlock> CODEC = simpleCodec(NhilCrystalBlock::new);
    public NhilCrystalBlock(Properties properties) {
        super(properties);

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NhilCrystalBlockEntity(blockPos, blockState);
    }


    @Override
    protected @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }


    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
