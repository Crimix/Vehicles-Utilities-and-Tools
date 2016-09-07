package com.black_dog20.vut.nei.handlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.ICraftingHandler;

public class ItemInfoHandler implements ICraftingHandler {

	private List<String> recipes = new ArrayList<String>();
	private List<PositionedStack> positionedStacks = new ArrayList<PositionedStack>();

	@Override
	public String getRecipeName() {
		return "Information / Lore";
	}

	@Override
	public int numRecipes() {
		return recipes.size();
	}

	@Override
	public void drawBackground(int recipe) {
		GL11.glColor4f(1, 1, 1, 1);
	}

	@Override
	public void drawForeground(int recipe) {
		if (this.recipes.size() > recipe) {
			String info = recipes.get(recipe);
			String name = positionedStacks.get(recipe).item.getDisplayName();
			FontRenderer render = Minecraft.getMinecraft().fontRenderer;
			render.drawSplitString(StatCollector.translateToLocal(info), 10, 30, 140, 0);
			render.drawSplitString(StatCollector.translateToLocal(name), 28, 4, 140, 0);
		}
	}

	@Override
	public List<PositionedStack> getIngredientStacks(int recipe) {
		return new ArrayList<PositionedStack>();
	}

	@Override
	public List<PositionedStack> getOtherStacks(int recipetype) {
		return new ArrayList<PositionedStack>();
	}

	@Override
	public PositionedStack getResultStack(int recipe) {
		return positionedStacks.get(recipe);
	}

	@Override
	public void onUpdate() {

	}

	@Override
	public boolean hasOverlay(GuiContainer gui, Container container, int recipe) {
		return false;
	}

	@Override
	public IRecipeOverlayRenderer getOverlayRenderer(GuiContainer gui, int recipe) {
		return null;
	}

	@Override
	public IOverlayHandler getOverlayHandler(GuiContainer gui, int recipe) {
		return null;
	}

	@Override
	public int recipiesPerPage() {
		return 1;
	}

	@Override
	public List<String> handleTooltip(GuiRecipe gui, List<String> currenttip, int recipe) {
		return currenttip;
	}

	@Override
	public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipe) {
		return currenttip;
	}

	@Override
	public boolean keyTyped(GuiRecipe gui, char keyChar, int keyCode, int recipe) {
		return false;
	}

	@Override
	public boolean mouseClicked(GuiRecipe gui, int button, int recipe) {
		return false;
	}

	@Override
	public ICraftingHandler getRecipeHandler(String outputId, Object... results) {

		ItemInfoHandler infoHandler = new ItemInfoHandler();
		if (results.length > 0 && (results[0] instanceof ItemStack) && (((ItemStack) results[0]).getItem() instanceof ItemBlock) /*
																																	* &&
																																	* (
																																	* (
																																	* (
																																	* ItemBlock
																																	* )
																																	* (
																																	* (
																																	* ItemStack
																																	* )
																																	* results
																																	* [
																																	* 0
																																	* ]
																																	* )
																																	* .
																																	* getItem
																																	* (
																																	* )
																																	* )
																																	* .
																																	* field_150939_a
																																	* instanceof
																																	* ITucsItem
																																	* )
																																	*/) {
			String unlocalinfo = ((ItemBlock) ((ItemStack) results[0]).getItem()).field_150939_a.getUnlocalizedName() + ".info";
			String unlocallore = ((ItemBlock) ((ItemStack) results[0]).getItem()).field_150939_a.getUnlocalizedName() + ".lore";
			add(infoHandler, unlocalinfo, results[0]);
			return add(infoHandler, unlocallore, results[0]);
		}
		if (results.length > 0 && (results[0] instanceof ItemStack /*
																	 * &&
																	 * ((ItemStack
																	 * )
																	 * results[0
																	 * ]
																	 * ).getItem
																	 * ()
																	 * instanceof
																	 * ITucsItem
																	 */)) {
			String unlocalinfo = ((ItemStack) results[0]).getUnlocalizedName() + ".info";
			String unlocallore = ((ItemStack) results[0]).getUnlocalizedName() + ".lore";
			add(infoHandler, unlocalinfo, results[0]);
			return add(infoHandler, unlocallore, results[0]);
		}
		return this;
	}

	private ItemInfoHandler add(ItemInfoHandler infoHandler, String unlocal, Object result) {
		if (!StatCollector.translateToLocal(unlocal).equals(unlocal)) {
			infoHandler.recipes.add(unlocal);
			infoHandler.positionedStacks.add(new PositionedStack(result, 10, 4));
			return infoHandler;
		}
		return infoHandler;
	}

}
