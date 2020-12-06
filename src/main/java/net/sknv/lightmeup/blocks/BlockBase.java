package net.sknv.lightmeup.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.sknv.lightmeup.IHasModel;
import net.sknv.lightmeup.LightMeUp;
import net.sknv.lightmeup.items.ModItems;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setLightLevel(1f);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        LightMeUp.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
