package com.black_dog20.vut.init;

import com.black_dog20.vut.item.ItemHoverBike;
import com.black_dog20.vut.item.ItemVUT;
import com.black_dog20.vut.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

	public static final ItemHoverBike HoverBike = new ItemHoverBike();
	public static final ItemVUT Wrench = new ItemVUT("wrench"); 
	public static final ItemVUT Engine = new ItemVUT("engine"); 
	public static final ItemVUT Chest = new ItemVUT("Chest"); 
	public static final ItemVUT Chest2 = new ItemVUT("Chest2"); 
	public static final ItemVUT FlightDrive = new ItemVUT("FlightDrive"); 
	public static final ItemVUT Security = new ItemVUT("Security"); 
	public static final ItemVUT Vtol = new ItemVUT("Vtol");
	public static final ItemVUT FuelTank = new ItemVUT("FuelTank"); 
	public static final ItemVUT Fuel = new ItemVUT("Fuel"); 
	public static final ItemVUT Fuel1 = new ItemVUT("Fuel1"); 
	public static final ItemVUT Fuel2 = new ItemVUT("Fuel2"); 
	public static final ItemVUT Hammer = new ItemVUT("Hammer"); 
	public static final ItemVUT Front = new ItemVUT("Front");
	public static final ItemVUT Back = new ItemVUT("Back");
	public static final ItemVUT FlightCore = new ItemVUT("FlightCore");
	public static final ItemVUT IngotBlueIron = new ItemVUT("IngotBlueIron");
	
	public static void init() {

		GameRegistry.registerItem(HoverBike, "HoverBike");
		GameRegistry.registerItem(Wrench, "wrench");
		GameRegistry.registerItem(Engine, "Engine");
		GameRegistry.registerItem(Chest, "Chest");
		GameRegistry.registerItem(Chest2, "Chest2");
		GameRegistry.registerItem(FlightDrive, "FlightDrive");
		GameRegistry.registerItem(Security, "Security");
		GameRegistry.registerItem(Vtol, "Vtol");
		GameRegistry.registerItem(FuelTank, "FuelTank");
		GameRegistry.registerItem(Fuel, "Fuel");
		GameRegistry.registerItem(Fuel1, "Fuel1");
		GameRegistry.registerItem(Fuel2, "Fuel2");
		GameRegistry.registerItem(Hammer, "Hammer");
		GameRegistry.registerItem(Front, "Front");
		GameRegistry.registerItem(Back, "Back");
		GameRegistry.registerItem(FlightCore, "FlightCore");
		GameRegistry.registerItem(IngotBlueIron, "IngotBlueIron");
	}

}
