package com.black_dog20.vut.slot;

import com.black_dog20.vut.entity.EntityVehicle;
import com.black_dog20.vut.init.ModItems;
import com.black_dog20.vut.utility.VehicleHelper;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFuel extends Slot {

	EntityVehicle vehicle;
	
	public SlotFuel(IInventory inventory, int slotIndex, int x, int y, Entity entity) {
		super(inventory, slotIndex, x, y);
		if(entity instanceof EntityVehicle)
			vehicle = (EntityVehicle) entity;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() == ModItems.Fuel;
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}
	
	@Override
	public void putStack(ItemStack stack)
	{
		if(stack != null){
			if(VehicleHelper.FuelAmountLeft(vehicle)!= 10000 && (VehicleHelper.FuelAmountLeft(vehicle)+1000) <= 10000){
				   VehicleHelper.AddFuel(vehicle, 1000);
			   }
			super.putStack(new ItemStack(Items.bucket));
		}
		else{
			super.putStack(stack);
		}
	}

}
