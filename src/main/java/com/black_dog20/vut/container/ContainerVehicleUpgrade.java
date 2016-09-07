package com.black_dog20.vut.container;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import com.black_dog20.vut.entity.IEntityVehicle;
import com.black_dog20.vut.init.ModItems;
import com.black_dog20.vut.inventory.InventoryVehicle;
import com.black_dog20.vut.inventory.InventoryVehicleUpgrade;
import com.black_dog20.vut.reference.VehicleNBTTags;
import com.black_dog20.vut.slot.SlotChest;
import com.black_dog20.vut.slot.SlotEngine;
import com.black_dog20.vut.slot.SlotFlightDrive;
import com.black_dog20.vut.slot.SlotFuel;
import com.black_dog20.vut.slot.SlotFuelTank;
import com.black_dog20.vut.slot.SlotSecruity;
import com.black_dog20.vut.slot.SlotVtol;
import com.black_dog20.vut.utility.NBTHelper;

public class ContainerVehicleUpgrade extends Container {
	public final InventoryVehicleUpgrade iventory;
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;
	private IEntityVehicle hoverBike;
	private Entity entity;
	private int rows;
	private int columns;
	private int ENGINE = 0;
	private int HOVER_DRIVE = 1;
	private int CHEST = 2;
	private int FUEL_TANK = 3;
	private int VTOL = 4;
	private int SECURITY = 5;
	private int FUEL = 6;

	public ContainerVehicleUpgrade(EntityPlayer player, World world, int x, int y, int z, Entity entity) {
		this.worldObj = world;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.entity = entity;
		this.iventory = new InventoryVehicleUpgrade(entity);
		if (entity instanceof IEntityVehicle) {
			this.hoverBike = (IEntityVehicle) entity;
		}
		
		this.addSlotToContainer(new SlotEngine(iventory, ENGINE, 62, 36));
		this.addSlotToContainer(new SlotFlightDrive(iventory, HOVER_DRIVE, 80, 36));
		this.addSlotToContainer(new SlotChest(iventory, CHEST, 80, 54));
		this.addSlotToContainer(new SlotFuelTank(iventory, FUEL_TANK, 80, 18));
		this.addSlotToContainer(new SlotVtol(iventory, VTOL, 98, 36));
		this.addSlotToContainer(new SlotSecruity(iventory, SECURITY, 116, 36));
		this.addSlotToContainer(new SlotFuel(iventory, FUEL, 152, 119,entity));
		

		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlotToContainer(new Slot(player.inventory, column + row * 9 + 9, 8 + column * 18, 140 + row * 18));
			}
		}

		for (int row = 0; row < 9; ++row) {
			this.addSlotToContainer(new Slot(player.inventory, row, 8 + row * 18, 198));
		}
		
		NBTTagCompound nbt = NBTHelper.getEntityNBT(entity);
		iventory.read(nbt);
		
		nbt.removeTag(VehicleNBTTags.CHEST);
		nbt.removeTag(VehicleNBTTags.ENGINE);
		nbt.removeTag(VehicleNBTTags.HOVER_DRIVE);
		nbt.removeTag(VehicleNBTTags.SECURITY);
		nbt.removeTag(VehicleNBTTags.VTOL);

	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(EntityPlayer EPlayer) {
		NBTTagCompound nbt = NBTHelper.getEntityNBT(entity);
		iventory.write(nbt);

		for (int i = 0; i < 6; i++) {
			ItemStack upgrade = this.iventory.getStackInSlot(i);
			if (upgrade != null) {
				if(upgrade.getItem() == ModItems.Engine){
					nbt.setBoolean(VehicleNBTTags.ENGINE, true);
				} else if(upgrade.getItem() == ModItems.Chest){
					nbt.setInteger(VehicleNBTTags.CHEST, 1);
				} else if(upgrade.getItem() == ModItems.Chest2){
					nbt.setInteger(VehicleNBTTags.CHEST, 2);
				} else if(upgrade.getItem() == ModItems.FlightDrive){
					nbt.setBoolean(VehicleNBTTags.HOVER_DRIVE, true);
				} else if(upgrade.getItem() == ModItems.Security){
					nbt.setBoolean(VehicleNBTTags.SECURITY, true);
				} else if(upgrade.getItem() == ModItems.Vtol){
					nbt.setBoolean(VehicleNBTTags.VTOL, true);
				} else if(upgrade.getItem() == ModItems.FuelTank){
					nbt.setBoolean(VehicleNBTTags.FUEL_TANK, true);
				} 
			} 
		}
		/*
		 * super.onContainerClosed(EPlayer);
		 * 
		 * if (!this.worldObj.isRemote) { for (int i = 0; i < 9; ++i) {
		 * ItemStack itemstack = this.iventory.getStackInSlotOnClosing(i);
		 * 
		 * if (itemstack != null) {
		 * EPlayer.dropPlayerItemWithRandomChoice(itemstack, false); } } }
		 */
	}

	public boolean canInteractWith(EntityPlayer EPlayer) {
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer EPlayer, int slot) {
		Slot slotObject = (Slot) inventorySlots.get(slot);
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			ItemStack stack = stackInSlot.copy();
			if (slot <= 1) {
				if (!mergeItemStack(stackInSlot, 2, inventorySlots.size(), true))
					return null;
			} else if (slot != 1 && !getSlot(0).getHasStack()) {
				ItemStack copy = slotObject.decrStackSize(1);
				getSlot(0).putStack(copy);
				return null;
			} else if (slot != 2 && !getSlot(1).getHasStack()) {
				ItemStack copy = slotObject.decrStackSize(1);
				getSlot(1).putStack(copy);
				return null;
			} else if (slot != 3 && !getSlot(2).getHasStack()) {
				ItemStack copy = slotObject.decrStackSize(1);
				getSlot(2).putStack(copy);
				return null;
			} else {
				return null;
			}

			if (stackInSlot.stackSize == 0)
				slotObject.putStack(null);
			else
				slotObject.onSlotChanged();

			return stack;
		}
		return null;
	}


}
