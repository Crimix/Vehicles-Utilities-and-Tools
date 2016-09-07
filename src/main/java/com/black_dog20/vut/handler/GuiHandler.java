package com.black_dog20.vut.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.black_dog20.vut.vut;
import com.black_dog20.vut.client.gui.GuiVehicle;
import com.black_dog20.vut.client.gui.GuiVehicleUpgrade;
import com.black_dog20.vut.container.ContainerVehicle;
import com.black_dog20.vut.container.ContainerVehicleUpgrade;
import com.black_dog20.vut.init.ModBlocks;
import com.black_dog20.vut.utility.NBTHelper;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		ItemStack item = player.getHeldItem();

		if (ID == vut.guiVehicleInventory) {
			return new ContainerVehicle(player, world, x, y, z, world.getEntityByID(NBTHelper.getPlayerNBT(player).getInteger("DVMVehicleId")));
		}
		else if(ID == vut.guiVehicleUpgrade){
			return new ContainerVehicleUpgrade(player, world, x, y, z, world.getEntityByID(NBTHelper.getPlayerNBT(player).getInteger("DVMVehicleId")));
			
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		if (ID == vut.guiVehicleInventory) {
			Entity vehicle = world.getEntityByID(NBTHelper.getPlayerNBT(player).getInteger("DVMVehicleId"));
			if(vehicle !=null){
				return new GuiVehicle(player, world, x, y, z,vehicle );
			}
			else return null;
		}
		else if(ID == vut.guiVehicleUpgrade){
			Entity vehicle = world.getEntityByID(NBTHelper.getPlayerNBT(player).getInteger("DVMVehicleId"));
			if(vehicle !=null){
				return new GuiVehicleUpgrade(player, world, x, y, z, vehicle);
			}
			else return null;
		}

		return null;
	}

}