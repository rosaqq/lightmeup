package net.sknv.lightmeup.items;

import javafx.scene.LightBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.common.Mod;
import net.sknv.lightmeup.blocks.BlockLight;
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
    public static final Item TEST_WAND = new ItemTestWand("test_wand");

    public static final Item NAME_WAND = new ItemTestWand("sout_wand") {
        @Override
        public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            IBlockState state = worldIn.getBlockState(pos);
            System.out.println(state);
            return EnumActionResult.SUCCESS;
        }
    };
}
