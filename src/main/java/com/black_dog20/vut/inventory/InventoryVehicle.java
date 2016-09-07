package com.black_dog20.vut.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.black_dog20.vut.entity.IEntityVehicle;
import com.black_dog20.vut.utility.NBTHelper;

public class InventoryVehicle implements IInventory {
	public Entity parentItemStack;
	protected ItemStack[] inventory;
	protected String customName;
	private IEntityVehicle hoverVehicle;

	public InventoryVehicle(Entity entity) {
		System.out.println(entity);
		int size = 0;
		parentItemStack = entity;

		if (parentItemStack instanceof IEntityVehicle) {
			hoverVehicle = (IEntityVehicle) parentItemStack;
			size = hoverVehicle.Slots();
		}

		inventory = new ItemStack[size];

		read(NBTHelper.getEntityNBT(parentItemStack));
	}

	public void save() {
		NBTTagCompound nbtTagCompound = NBTHelper.getEntityNBT(parentItemStack);

		if (nbtTagCompound == null) {
			nbtTagCompound = new NBTTagCompound();

		}

		write(nbtTagCompound);
		parentItemStack.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, nbtTagCompound);
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		return inventory[slotIndex];
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
		ItemStack itemStack = getStackInSlot(slotIndex);
		if (itemStack != null) {
			if (itemStack.stackSize <= decrementAmount) {
				setInventorySlotContents(slotIndex, null);
			} else {
				itemStack = itemStack.splitStack(decrementAmount);
				if (itemStack.stackSize == 0) {
					setInventorySlotContents(slotIndex, null);
				}
			}
		}

		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		if (inventory[slotIndex] != null) {
			ItemStack itemStack = inventory[slotIndex];
			inventory[slotIndex] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
		inventory[slotIndex] = itemStack;
	}

	@Override
	public String getInventoryName() {
		return hoverVehicle.Name();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack) {
		return true;
	}

	public void read(NBTTagCompound nbtTagCompound) {
		if (nbtTagCompound != null && nbtTagCompound.hasKey("items")) {
			// Read in the ItemStacks in the inventory from NBT
			if (nbtTagCompound.hasKey("items")) {
				NBTTagList tagList = nbtTagCompound.getTagList("items", 10);
				inventory = new ItemStack[this.getSizeInventory()];
				for (int i = 0; i < tagList.tagCount(); ++i) {
					NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
					byte slotIndex = tagCompound.getByte("Slot");
					if (slotIndex >= 0 && slotIndex < inventory.length) {
						inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
					}
				}
			}
		}
	}

	public void write(NBTTagCompound nbtTagCompound) {
		// Write the ItemStacks in the inventory to NBT
		NBTTagList tagList = new NBTTagList();
		for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
			if (inventory[currentIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				inventory[currentIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		nbtTagCompound.setTag("items", tagList);
	}
}
