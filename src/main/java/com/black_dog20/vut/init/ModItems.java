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
	public static final ItemVUT Chest = new ItemVUT("chest"); 
	public static final ItemVUT Chest2 = new ItemVUT("chest2"); 
	public static final ItemVUT FlightDrive = new ItemVUT("flightDrive"); 
	public static final ItemVUT Security = new ItemVUT("security"); 
	public static final ItemVUT Vtol = new ItemVUT("vtol");
	public static final ItemVUT FuelTank = new ItemVUT("fuelTank"); 
	public static final ItemVUT Fuel = new ItemVUT("fuel"); 
	public static final ItemVUT Fuel1 = new ItemVUT("fuel1"); 
	public static final ItemVUT Fuel2 = new ItemVUT("fuel2"); 
	public static final ItemVUT Hammer = new ItemVUT("hammer"); 
	public static final ItemVUT Front = new ItemVUT("front");
	public static final ItemVUT Back = new ItemVUT("back");
	public static final ItemVUT FlightCore = new ItemVUT("flightCore");
	public static final ItemVUT IngotBlueIron = new ItemVUT("ingotBlueIron");
	public static final ItemVUT DustBlackIron = new ItemVUT("dustBlackIron");
	public static final ItemVUT IngotBlackIron = new ItemVUT("ingotBlackIron");
	
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
		GameRegistry.registerItem(DustBlackIron, "DustBlackIron");
		GameRegistry.registerItem(IngotBlackIron, "IngotBlackIron");
		
	}

}
