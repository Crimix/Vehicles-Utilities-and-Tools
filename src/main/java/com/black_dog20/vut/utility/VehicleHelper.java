package com.black_dog20.vut.utility;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;

import com.black_dog20.vut.entity.EntityVehicle;
import com.black_dog20.vut.reference.VehicleNBTTags;

public class VehicleHelper {
	
	
	public static int ChestUpgrade(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		if(nbt.hasKey(VehicleNBTTags.CHEST)){
			return nbt.getInteger(VehicleNBTTags.CHEST);
		}
		else{
			return 0;
		}
	}
	
	public static void AddFuel(EntityVehicle vehicle, double fuel){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
			double fuel_ = nbt.getDouble(VehicleNBTTags.FUEL);
			double newFuel;
			if(fuel_+fuel > 10000){
				newFuel = 10000;
			}
			else
				newFuel = fuel_+fuel;
			nbt.setDouble(VehicleNBTTags.FUEL, newFuel);
	}
	
	public static void SubtractFuel(EntityVehicle vehicle,double fuel){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		if(nbt.hasKey(VehicleNBTTags.FUEL)){
			double fuel_ = nbt.getDouble(VehicleNBTTags.FUEL);
			double newFuel = fuel_-fuel;
			nbt.setDouble(VehicleNBTTags.FUEL, newFuel);
		}
	}
	
	public static double FuelAmountLeft(NBTTagCompound nbt){
		if(nbt.hasKey(VehicleNBTTags.FUEL)){
			return nbt.getDouble(VehicleNBTTags.FUEL);
		}
		else{
			return 0.0;
		}
	}
	
	public static double FuelAmountLeft(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return FuelAmountLeft(nbt);
	}
	
	public static double MaxSpeedFromMotor(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return 0.0;
	}
	
	public static int VtolHight(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return 10;
	}

	public static boolean HasFuelEfficentUpgrade(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return false;
	}
	
	public static boolean HasEngine(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return nbt.hasKey(VehicleNBTTags.ENGINE);
	}
	
	public static boolean HasHoverDrive(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return nbt.hasKey(VehicleNBTTags.HOVER_DRIVE);
	}
	
	public static boolean HasSecurityUpgrade(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return nbt.hasKey(VehicleNBTTags.SECURITY);
	}
	
	public static boolean HasVtolUpgrade(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return nbt.hasKey(VehicleNBTTags.VTOL);
	}
	public static boolean HasFuelTank(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return nbt.hasKey(VehicleNBTTags.FUEL_TANK);
	}
	
	public static List<String> PlayerWhoCanDrive(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return null;
	}
	
	public static List<String> PlayerWhoCanAccessChest(EntityVehicle vehicle){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(vehicle);
		return null;
	}
}
