package com.black_dog20.vut.item;

import net.minecraft.item.ItemStack;

public class ItemHammer extends ItemVUT {

	public ItemHammer(){
		super("hammer");
		this.maxStackSize=1;
		this.setContainerItem(this);
	}
	
	 @Override
     public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
     {
         return false;
     }
}
