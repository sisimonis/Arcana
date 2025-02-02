package org.exodusstudio.arcana.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.exodusstudio.arcana.Arcana;
import org.exodusstudio.arcana.common.registry.ItemRegistry;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class ResearchTable extends HorizontalDirectionalBlock {
    public static final EnumProperty<RT_State> RT_ACTIVATED;
    public static final MapCodec<ResearchTable> CODEC = simpleCodec(ResearchTable::new);


    public ResearchTable(Properties properties) {
        super(properties);
        registerDefaultState((BlockState)this.defaultBlockState()
                .setValue(RT_ACTIVATED, RT_State.OFF));
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide) return InteractionResult.SUCCESS;
        if(state.getValue(RT_ACTIVATED) != RT_State.OFF) return InteractionResult.FAIL;

        Item item = stack.getItem();
        if(item == ItemRegistry.SCRIBBLING_TOOL.get())
        {
            List<Object> listReturn = researchTableAround(level, pos, state);
            RT_State tableState = (RT_State) listReturn.getFirst();
            if(tableState != RT_State.OFF)
            {
                BlockPos neighBlockPos = (BlockPos) listReturn.get(1);
                Direction newDirection = (Direction) listReturn.get(2);
                if(newDirection != state.getValue(FACING))
                {
                    state = state.setValue(FACING, newDirection);
                }

                RT_State otherTableState = tableState == RT_State.ON_LEFT ? RT_State.ON_RIGHT : RT_State.ON_LEFT;
                BlockState selectedState, neighState;
                selectedState = (BlockState) state.setValue(RT_ACTIVATED, (RT_State)listReturn.getFirst());
                neighState = (BlockState) state.setValue(RT_ACTIVATED, otherTableState);
                level.setBlock(pos, selectedState, 3);
                level.setBlock(neighBlockPos, neighState, 3);
            }
            else
            {
                player.displayClientMessage(Component.translatable("arcana.message.research_table_not_found"), true);
            }
        }

        return InteractionResult.SUCCESS;
    }

    static {
        RT_ACTIVATED = EnumProperty.create("rt_activated", RT_State.class);
    }


    @Override
    public @NotNull BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        RT_State rtState = state.getValue(RT_ACTIVATED);

        if (rtState != RT_State.OFF) {
            Direction facing = state.getValue(FACING);

            BlockPos linkedPos = (rtState == RT_State.ON_LEFT) ? pos.relative(facing.getClockWise()) : pos.relative(facing.getCounterClockWise());
            BlockState linkedState = level.getBlockState(linkedPos);

            if (linkedState.getBlock() instanceof ResearchTable
                    && linkedState.getValue(RT_ACTIVATED) != RT_State.OFF
                    && linkedState.getValue(RT_ACTIVATED) != rtState) {

                //Remove the other block
                Arcana.LOGGER.info("Breaking the other block");
                level.setBlock(linkedPos, Blocks.AIR.defaultBlockState(), 3);
                Block.popResource(level, linkedPos, new ItemStack(this));
                level.gameEvent(GameEvent.BLOCK_DESTROY, linkedPos, GameEvent.Context.of(player, linkedState));
            }

        }

        //Then destroy the current one
        super.playerWillDestroy(level, pos, state, player);

        return state;
    }

    private List<Object> researchTableAround(Level level, BlockPos currentPos, BlockState blockState)
    {
        Direction facing = blockState.getValue(FACING);
        BlockPos[] blockPos;
        switch (facing)
        {
            case Direction.NORTH:
                if(isBlockResearchTable(level, currentPos.east()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.east(), Direction.NORTH);
                if(isBlockResearchTable(level, currentPos.west()))
                    return Arrays.asList(RT_State.ON_RIGHT, currentPos.west(), Direction.NORTH);
                if(isBlockResearchTable(level, currentPos.north()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.north(), Direction.WEST);
                if(isBlockResearchTable(level, currentPos.south()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.south(), Direction.EAST);

            case Direction.SOUTH:
                if(isBlockResearchTable(level, currentPos.east()))
                    return Arrays.asList(RT_State.ON_RIGHT, currentPos.east(), Direction.SOUTH);
                if(isBlockResearchTable(level, currentPos.west()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.west(), Direction.SOUTH);
                if(isBlockResearchTable(level, currentPos.north()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.north(), Direction.WEST);
                if(isBlockResearchTable(level, currentPos.south()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.south(), Direction.EAST);

            case Direction.EAST:
                if(isBlockResearchTable(level, currentPos.north()))
                    return Arrays.asList(RT_State.ON_RIGHT, currentPos.north(), Direction.EAST);
                if(isBlockResearchTable(level, currentPos.south()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.south(), Direction.EAST);
                if(isBlockResearchTable(level, currentPos.east()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.east(), Direction.NORTH);
                if(isBlockResearchTable(level, currentPos.west()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.west(), Direction.SOUTH);

            case Direction.WEST:
                if(isBlockResearchTable(level, currentPos.north()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.north(), Direction.WEST);
                if(isBlockResearchTable(level, currentPos.south()))
                    return Arrays.asList(RT_State.ON_RIGHT, currentPos.south(), Direction.WEST);
                if(isBlockResearchTable(level, currentPos.east()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.east(), Direction.NORTH);
                if(isBlockResearchTable(level, currentPos.west()))
                    return Arrays.asList(RT_State.ON_LEFT, currentPos.west(), Direction.SOUTH);

        }
        return  Arrays.asList(RT_State.OFF);
    }

    private boolean isBlockResearchTable(Level level, BlockPos pos)
    {
        BlockState adjacentState = level.getBlockState(pos);
        return  adjacentState.getBlock() instanceof ResearchTable && adjacentState.getValue(RT_ACTIVATED) == RT_State.OFF;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(RT_ACTIVATED, RT_State.OFF)
                .setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(RT_ACTIVATED);
        builder.add(FACING);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
