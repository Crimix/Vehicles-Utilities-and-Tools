package com.black_dog20.vut.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import com.black_dog20.vut.reference.Reference;

public class CreativeTabVUT{

	public static final CreativeTabs VUT_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

		@Override
		public Item getTabIconItem() {
			return Items.apple;
			//return ModItems.TLSOC;
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.MOD_NAME;
		}
	};

}
