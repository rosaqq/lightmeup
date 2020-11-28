package net.sknv.lightmeup.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.sknv.lightmeup.IHasModel;
import net.sknv.lightmeup.LightMeUp;
import net.sknv.lightmeup.ModItems;

public class ItemBase extends Item implements IHasModel {

    public ItemBase(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        LightMeUp.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
