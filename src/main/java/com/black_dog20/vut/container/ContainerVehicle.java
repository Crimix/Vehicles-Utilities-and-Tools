package com.black_dog20.vut.container;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.black_dog20.vut.entity.IEntityVehicle;
import com.black_dog20.vut.inventory.InventoryVehicle;
import com.black_dog20.vut.utility.NBTHelper;

public class ContainerVehicle extends Container {
	public final InventoryVehicle iventory;
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;
	private IEntityVehicle hoverBike;
	private Entity entity;
	private int rows;
	private int columns;

	public ContainerVehicle(EntityPlayer player, World world, int x, int y, int z, Entity entity) {
		this.worldObj = world;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.entity = entity;
		this.iventory = new InventoryVehicle(entity);
		if (entity instanceof IEntityVehicle) {
			this.hoverBike = (IEntityVehicle) entity;
		}
		this.rows = hoverBike.Rows();
		this.columns = hoverBike.Columns();

		for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
			for (int columnIndex = 0; columnIndex < columns; ++columnIndex) {
				this.addSlotToContainer(new Slot(iventory, columnIndex + rowIndex * columns, 8 + columnIndex * 18, 18 + rowIndex * 18));
			}
		}

		int playerIvnY = 84;
		if(rows > 3){
			playerIvnY = 139;
		}
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlotToContainer(new Slot(player.inventory, column + row * 9 + 9, 8 + column * 18, playerIvnY + row * 18));
			}
		}

		int playerHotY = 142;
		if(rows > 3){
			playerHotY = 197;
		}
		for (int row = 0; row < 9; ++row) {
			this.addSlotToContainer(new Slot(player.inventory, row, 8 + row * 18, playerHotY));
		}

		iventory.read(NBTHelper.getEntityNBT(entity));

	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(EntityPlayer EPlayer) {
		iventory.write(NBTHelper.getEntityNBT(entity));

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
	public ItemStack transferStackInSlot(EntityPlayer EPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 < this.rows * 9) {
				if (!this.mergeItemStack(itemstack1, this.rows * 9, this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.rows * 9, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

}
