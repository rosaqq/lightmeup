package net.sknv.lightmeup;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.fml.common.Mod;
import net.sknv.lightmeup.items.armor.ArmorBase;

import java.util.ArrayList;
import java.util.List;

import static net.sknv.lightmeup.LightMeUp.MODID;

@Mod.EventBusSubscriber(modid=MODID)
public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item TORCH_IRON_HELMET = new ArmorBase("torch_iron_helmet", ItemArmor.ArmorMaterial.IRON, 1, EntityEquipmentSlot.HEAD);
    public static final Item TORCH_GOLD_HELMET = new ArmorBase("torch_gold_helmet", ItemArmor.ArmorMaterial.GOLD, 1, EntityEquipmentSlot.HEAD);
    public static final Item TORCH_DIAMOND_HELMET = new ArmorBase("torch_diamond_helmet", ItemArmor.ArmorMaterial.DIAMOND, 1, EntityEquipmentSlot.HEAD);
}
