package net.sknv.lightmeup.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// The idea is to create a block with the same texture as the overworld block below the player but
// with light level set to 15. Then we just need to keep replacing the blocks when the player walks.
// Based on https://github.com/TheGreyGhost/MinecraftByExample/tree/1-12-2-final/src/main/java/minecraftbyexample/mbe04_block_dynamic_block_model1
public class BlockLight extends BlockBase {

    // The block this will replace and copy
    public IBlockState replacedBlock;

    // the COPIEDBLOCK property is used to store the identity of the block that BlockCamouflage will copy
    public static final UnlistedPropertyCopiedBlock COPIEDBLOCK = new UnlistedPropertyCopiedBlock();

    public BlockLight() {
        super("light_block", Material.CIRCUITS);
        setLightLevel(1f);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    // createBlockState is used to define which properties your blocks possess
    // Vanilla BlockState is composed of listed properties only.
    // A variant is created for each combination of listed properties;
    // Forge adds ExtendedBlockState, which has two types of property:
    // - listed properties (like vanilla), and
    // - unlisted properties, which can be used to convey information but do not cause extra variants to be created.
    @Override
    protected BlockStateContainer createBlockState() {
        IProperty[] listedProperties = new IProperty[0]; // no listed properties
        IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] {COPIEDBLOCK};
        return new ExtendedBlockState(this, listedProperties, unlistedProperties);
    }

    // this method uses the block state and BlockPos to update the unlisted COPIEDBLOCK property in IExtendedBlockState based
    // on non-metadata information.  This is then conveyed to the IBakedModel#getQuads during rendering.
    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state instanceof IExtendedBlockState) {  // avoid crash in case of mismatch
            IExtendedBlockState retval = (IExtendedBlockState)state;
            IBlockState bestAdjacentBlock = selectBestAdjacentBlock(world, pos);
            retval = retval.withProperty(COPIEDBLOCK, bestAdjacentBlock);
            return retval;
        }
        return state;
    }

    // Select the best adjacent block to camouflage as.
    // Algorithm is:
    // 1) Ignore any blocks which are not solid (CUTOUTS or TRANSLUCENT).  Ignore adjacent camouflage.
    // 2) If there are more than one type of solid block, choose the type which is present on the greatest number of sides
    // 3) In case of a tie, prefer the type which span opposite sides of the blockpos, for example:
    //       up and down; east and west; north and south.
    // 4) If still a tie, look again for spans on both sides, counting adjacent camouflage blocks as a span
    // 5) If still a tie, in decreasing order of preference: NORTH, SOUTH, EAST, WEST, DOWN, UP
    // 6) If no suitable adjacent blocks, return Block.air
    private IBlockState selectBestAdjacentBlock(IBlockAccess world, BlockPos blockPos)
    {
        final IBlockState UNCAMOUFLAGED_BLOCK = Blocks.AIR.getDefaultState();
        TreeMap<EnumFacing, IBlockState> adjacentSolidBlocks = new TreeMap<EnumFacing, IBlockState>();

        HashMap<IBlockState, Integer> adjacentBlockCount = new HashMap<IBlockState, Integer>();
        for (EnumFacing facing : EnumFacing.values()) {
            BlockPos adjacentPosition = blockPos.add(facing.getFrontOffsetX(),
                    facing.getFrontOffsetY(),
                    facing.getFrontOffsetZ());
            IBlockState adjacentIBS = world.getBlockState(adjacentPosition);
            Block adjacentBlock = adjacentIBS.getBlock();
            if (adjacentBlock != Blocks.AIR
                    && adjacentBlock.getBlockLayer() == BlockRenderLayer.SOLID
                    && adjacentBlock.isOpaqueCube(adjacentIBS)) {
                adjacentSolidBlocks.put(facing, adjacentIBS);
                if (adjacentBlockCount.containsKey(adjacentIBS)) {
                    adjacentBlockCount.put(adjacentIBS, 1 + adjacentBlockCount.get(adjacentIBS));
                } else if (adjacentIBS.getBlock() != this){
                    adjacentBlockCount.put(adjacentIBS, 1);
                }
            }
        }

        if (adjacentBlockCount.isEmpty()) {
            return UNCAMOUFLAGED_BLOCK;
        }

        if (adjacentSolidBlocks.size() == 1) {
            IBlockState singleAdjacentBlock = adjacentSolidBlocks.firstEntry().getValue();
            if (singleAdjacentBlock.getBlock() == this) {
                return UNCAMOUFLAGED_BLOCK;
            } else {
                return singleAdjacentBlock;
            }
        }

        int maxCount = 0;
        ArrayList<IBlockState> maxCountIBlockStates = new ArrayList<IBlockState>();
        for (Map.Entry<IBlockState, Integer> entry : adjacentBlockCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCountIBlockStates.clear();
                maxCountIBlockStates.add(entry.getKey());
                maxCount = entry.getValue();
            } else if (entry.getValue() == maxCount) {
                maxCountIBlockStates.add(entry.getKey());
            }
        }

        assert maxCountIBlockStates.isEmpty() == false;
        if (maxCountIBlockStates.size() == 1) {
            return maxCountIBlockStates.get(0);
        }

        // for each block which has a match on the opposite side, add 10 to its count.
        // exact matches are counted twice --> +20, match with BlockCamouflage only counted once -> +10
        for (Map.Entry<EnumFacing, IBlockState> entry : adjacentSolidBlocks.entrySet()) {
            IBlockState iBlockState = entry.getValue();
            if (maxCountIBlockStates.contains(iBlockState)) {
                EnumFacing oppositeSide = entry.getKey().getOpposite();
                IBlockState oppositeBlock = adjacentSolidBlocks.get(oppositeSide);
                if (oppositeBlock != null && (oppositeBlock == iBlockState || oppositeBlock.getBlock() == this) ) {
                    adjacentBlockCount.put(iBlockState, 10 + adjacentBlockCount.get(iBlockState));
                }
            }
        }

        maxCount = 0;
        maxCountIBlockStates.clear();
        for (Map.Entry<IBlockState, Integer> entry : adjacentBlockCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCountIBlockStates.clear();
                maxCountIBlockStates.add(entry.getKey());
                maxCount = entry.getValue();
            } else if (entry.getValue() == maxCount) {
                maxCountIBlockStates.add(entry.getKey());
            }
        }
        assert maxCountIBlockStates.isEmpty() == false;
        if (maxCountIBlockStates.size() == 1) {
            return maxCountIBlockStates.get(0);
        }

        EnumFacing [] orderOfPreference = new EnumFacing[] {EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST,
                EnumFacing.WEST, EnumFacing.DOWN, EnumFacing.UP};

        for (EnumFacing testFace : orderOfPreference) {
            if (adjacentSolidBlocks.containsKey(testFace) &&
                    maxCountIBlockStates.contains(adjacentSolidBlocks.get(testFace))) {
                return adjacentSolidBlocks.get(testFace);
            }
        }
        assert false : "this shouldn't be possible";
        return null;
    }

}
