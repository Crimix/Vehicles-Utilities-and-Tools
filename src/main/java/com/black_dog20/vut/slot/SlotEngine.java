package com.black_dog20.vut.slot;

import com.black_dog20.vut.init.ModItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEngine extends Slot {

	public SlotEngine(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() == ModItems.Engine;
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

}
