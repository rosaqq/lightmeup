package net.sknv.lightmeup.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.sknv.lightmeup.IHasModel;
import net.sknv.lightmeup.LightMeUp;
import net.sknv.lightmeup.items.ModItems;

public class BlockBase extends Block implements IHasModel {

    public Item itemBlock;

    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
        itemBlock = new ItemBlock(this).setRegistryName(this.getRegistryName());
        ModItems.ITEMS.add(itemBlock);
    }

    @Override
    public void registerModels() {
        LightMeUp.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public Item getItemBlock() {
        return this.itemBlock;
    }
}
