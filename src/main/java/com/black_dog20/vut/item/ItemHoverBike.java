package com.black_dog20.vut.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.black_dog20.vut.entity.EntityHoverBike;
import com.black_dog20.vut.utility.NBTHelper;

public class ItemHoverBike extends ItemVUT {

	public ItemHoverBike() {

		super();
		this.setUnlocalizedName("hoverBike");

	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffSet) {

		if (!world.isRemote) {
			EntityHoverBike ent = new EntityHoverBike(world); // change to
																// whatever
																// entity you're
																// trying to
																// spawn

			if (item.hasTagCompound()) {
				NBTTagCompound nbtTagCompound = NBTHelper.getEntityNBT(ent);
				NBTTagCompound nbt = item.getTagCompound();
				nbtTagCompound = nbt;
				ent.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, nbtTagCompound);
			}
			ent.setLocationAndAngles(x, y + 1, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			world.spawnEntityInWorld(ent);
		}
		return true;
	}

}
