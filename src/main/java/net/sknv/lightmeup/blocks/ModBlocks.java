package net.sknv.lightmeup.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import scala.Array;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block LIGHT_BLOCK = new BlockBase("light_block", Material.ROCK);
}
