package com.black_dog20.vut.handler;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModLivingDropsEvent {
	NBTTagCompound nbt;
	Random r = new Random();

	// Event for dropping items when a entity dies
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {

	}

}