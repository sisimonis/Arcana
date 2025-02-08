package org.exodusstudio.arcana.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.exodusstudio.arcana.common.registry.BlockEntityRegistry;

public class NhilCrystalBlockEntity extends BlockEntity {
    private float offsetX;
    private float offsetY;
    private float offsetZ;


    public NhilCrystalBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistry.NHILCRYSTALBLOCK_ENTITY.get(), pos, blockState); // Pass the type parameter directly
        randomizeOffsets();
    }

    private void randomizeOffsets() {
        // Randomize offsets within the block boundaries (-0.5 to 0.5)
        this.offsetX = (float) (Math.random() - 0.5);
        this.offsetY = (float) (Math.random() - 0.5);
        this.offsetZ = (float) (Math.random() - 0.5);
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public float getOffsetZ() {
        return offsetZ;
    }


}
