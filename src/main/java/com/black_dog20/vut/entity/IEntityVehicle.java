package com.black_dog20.vut.entity;

import com.sun.xml.internal.stream.Entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IEntityVehicle {

	public double Maxspeed();

	public int Seats();

	public int Slots();

	public String Name();

	public int Rows();

	public int Columns();
	
	public void VehicleBehaviour();
	
	public void SaveVehicleNBTToItem(ItemStack item);

}
