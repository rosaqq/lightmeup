package net.sknv.lightmeup.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.sknv.lightmeup.blocks.BlockLight;
import net.sknv.lightmeup.blocks.ModBlocks;
import net.sknv.lightmeup.blocks.UnlistedPropertyCopiedBlock;

public class ItemTestWand extends ItemBase{

    public ItemTestWand(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState original = worldIn.getBlockState(pos);
        IExtendedBlockState newState = (IExtendedBlockState) ModBlocks.LIGHT_BLOCK.getBlockState().getBaseState();
        newState.withProperty(BlockLight.COPIEDBLOCK, original);
        worldIn.setBlockState(pos, newState);
        player.sendMessage(new TextComponentString("used"));

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
