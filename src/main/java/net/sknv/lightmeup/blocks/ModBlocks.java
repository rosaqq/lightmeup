package net.sknv.lightmeup.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import scala.Array;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<>();

    // todo: make this block dynamically get he texture of the block it replaces in the overworld
    // https://github.com/TheGreyGhost/MinecraftByExample/tree/1-12-2-final/src/main/java/minecraftbyexample/mbe04_block_dynamic_block_model1
    public static final Block LIGHT_BLOCK = new BlockBase("light_block", Material.ROCK);
}
