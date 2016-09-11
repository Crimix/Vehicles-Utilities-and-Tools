package com.black_dog20.vut.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.black_dog20.vut.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class Recipes {

	public static void init() {
		GameRegistry.addSmelting(ModItems.DustBlackIron, new ItemStack(ModItems.IngotBlackIron), 1.0f);
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Hammer, new Object[] { "bbb", " s ", " s ", 'b', ModItems.IngotBlackIron, 's', "stickWood" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.IngotBlueIron, new ItemStack(Items.dye,1,4), ModItems.Hammer, Items.iron_ingot));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.FlightCore, new Object[] { "ege", "ede", "ehe", 'h', ModItems.Hammer, 'e', Items.ender_pearl, 'g', Items.ghast_tear,'d', Blocks.diamond_block }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.FlightDrive, new Object[] { "ibi", "bfb", "ibi", 'b', ModItems.IngotBlackIron, 'i', Items.iron_ingot, 'f', ModItems.FlightCore }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Back, new Object[] { " ii", "bbl", "bbl", 'l', ModItems.IngotBlackIron, 'i', Items.iron_ingot, 'b', ModItems.IngotBlueIron}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Front, new Object[] { " g ", "bbl", "bbb", 'l', Items.leather, 'g', Blocks.glass_pane, 'b', ModItems.IngotBlueIron}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.HoverBike, ModItems.Back, ModItems.Front, ModItems.Hammer));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Wrench, new Object[] { "b b", " b ", " b ", 'b', ModItems.IngotBlueIron}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Fuel1, new Object[] { "chc", "cgc", "cwc", 'h', ModItems.Hammer, 'c' , Items.coal , 'w', Items.bucket,'g',new ItemStack(Items.potionitem, 1, 8206)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Fuel2, new Object[] { "nnn", "nfn", "nnn", 'f', ModItems.Fuel1, 'n' , Items.nether_wart }));
		GameRegistry.addSmelting(ModItems.Fuel2, new ItemStack(ModItems.Fuel), 1.0f);
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.FuelTank, new Object[] { "ihi", "i i", "iii", 'h', ModItems.Hammer, 'i' , Items.iron_ingot }));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Chest, new Object[] { " h ", "bcb", "bbb", 'h', ModItems.Hammer, 'c' , Blocks.chest , 'b' , ModItems.IngotBlueIron}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Chest2, new Object[] { " h ", "cbc", "   ", 'h', ModItems.Hammer, 'c' , ModItems.Chest , 'b' , ModItems.IngotBlueIron}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Vtol, new Object[] { "fff", "fbf", "fff", 'f', Items.feather, 'b' , ModItems.IngotBlueIron}));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.Engine, new Object[] { "b b", "bib", "iii", 'i', Items.iron_ingot, 'b' , ModItems.IngotBlackIron}));
	}

}
