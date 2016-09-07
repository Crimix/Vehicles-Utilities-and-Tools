package com.black_dog20.vut.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.black_dog20.vut.container.ContainerVehicle;
import com.black_dog20.vut.entity.EntityHoverBike;
import com.black_dog20.vut.entity.EntityVehicle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiVehicle extends GuiContainer {

	private ResourceLocation texture = new ResourceLocation("minecraft:textures/gui/container/generic_54.png");
	private int inventoryRows;
	private EntityVehicle entityHoverBike;
	
	public GuiVehicle(EntityPlayer player, World world, int x, int y, int z, Entity entity) {
		super(new ContainerVehicle(player, world, x, y, z, entity));

		entityHoverBike = (EntityVehicle) entity;
		short short1 = 222;
		int i = short1 - 108;
		this.inventoryRows = entityHoverBike.Slots() / 9;
		this.ySize = i + this.inventoryRows * 18;
		this.xSize = 176;

	}
	
	private int getSpacesNeedForName(String name){
		int result = 0;
		for(char  c : name.toCharArray()){
			result += fontRendererObj.getCharWidth(c);
		}
		return result;
	}

	protected void drawGuiContainerForegroundLayer(int i, int j) {

		this.fontRendererObj.drawString(StatCollector.translateToLocal(entityHoverBike.Name()), 8, 6, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("Inventory"), 8+getSpacesNeedForName(entityHoverBike.Name())+4, 6, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);

	}

}
