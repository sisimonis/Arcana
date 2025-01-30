package org.exodusstudio.arcana.common.block;

import it.unimi.dsi.fastutil.booleans.BooleanPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.item.ArcanaScribbledNoteItem;
import org.exodusstudio.arcana.common.registry.ItemRegistry;

import javax.annotation.Nullable;

public class ResearchTable extends Block {
    public static final BooleanProperty RT_ACTIVATED;
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(RT_ACTIVATED);
    }

    public ResearchTable(Properties properties) {
        super(properties);
        registerDefaultState((BlockState)this.defaultBlockState().setValue(RT_ACTIVATED, false));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(RT_ACTIVATED, false);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide) return InteractionResult.SUCCESS;

        Item item = stack.getItem();
        if(item == ItemRegistry.SCRIBBLING_TOOL.get())
        {
            state = (BlockState) state.cycle(RT_ACTIVATED);
            level.setBlock(pos, state, 3);
            Arcana.LOGGER.info(state);
        }

        return InteractionResult.SUCCESS;
    }

    static {
        RT_ACTIVATED = BooleanProperty.create("rt_activated");
    }
}
