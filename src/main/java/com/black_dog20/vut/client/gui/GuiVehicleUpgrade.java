package com.black_dog20.vut.client.gui;

import java.text.DecimalFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.black_dog20.vut.container.ContainerVehicle;
import com.black_dog20.vut.container.ContainerVehicleUpgrade;
import com.black_dog20.vut.entity.EntityHoverBike;
import com.black_dog20.vut.entity.EntityVehicle;
import com.black_dog20.vut.utility.VehicleHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiVehicleUpgrade extends GuiContainer {

	private ResourceLocation texture = new ResourceLocation("vut:textures/gui/vehicleUpgrade.png");
	private EntityVehicle entityHoverBike;
	
	public GuiVehicleUpgrade(EntityPlayer player, World world, int x, int y, int z, Entity entity) {
		super(new ContainerVehicleUpgrade(player, world, x, y, z, entity));

		entityHoverBike = (EntityVehicle) entity;
		this.xSize = 175;
		this.ySize = 223;

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
		this.fontRendererObj.drawString(StatCollector.translateToLocal("Upgrades"), 8+getSpacesNeedForName(entityHoverBike.Name())+4, 6, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

	}

}
