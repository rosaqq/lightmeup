package net.sknv.lightmeup.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// The idea is to create a block with the same texture as the overworld block below the player but
// with light level set to 15. Then we just need to keep replacing the blocks when the player walks.
// Based on https://github.com/TheGreyGhost/MinecraftByExample/tree/1-12-2-final/src/main/java/minecraftbyexample/mbe04_block_dynamic_block_model1
public class BlockLight extends Block {

    // The block this will replace and copy
    public final IBlockState replacedBlock;

    // the COPIEDBLOCK property is used to store the identity of the block that BlockCamouflage will copy
    public static final UnlistedPropertyCopiedBlock COPIEDBLOCK = new UnlistedPropertyCopiedBlock();

    public BlockLight(IBlockState replacedBlock) {
        super(Material.CIRCUITS);
        this.replacedBlock = replacedBlock;
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
            IExtendedBlockState newState = (IExtendedBlockState)state;
            newState = newState.withProperty(COPIEDBLOCK, replacedBlock);
            return newState;
        }
        return state;
    }
}
