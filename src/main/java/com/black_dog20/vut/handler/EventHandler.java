package com.black_dog20.vut.handler;

import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.black_dog20.vut.entity.EntityVehicle;
import com.black_dog20.vut.entity.IEntityVehicle;
import com.black_dog20.vut.network.PacketHandler;
import com.black_dog20.vut.network.message.MessageSeverFuelAmount;
import com.black_dog20.vut.utility.VehicleHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
	
	@SubscribeEvent
	public void Tool(ItemTooltipEvent event) {
		ItemStack item = event.itemStack;
		List list = event.toolTip;
		if (item.hasTagCompound()) {
			NBTTagCompound nbtTagCompound = item.getTagCompound();
			if (nbtTagCompound.hasKey("Owner")) {
				list.add(EnumChatFormatting.LIGHT_PURPLE + I18n.format("Owner") + ": " + nbtTagCompound.getString("Owner"));
			}
		}
	}

	@SubscribeEvent
	public void onFall(LivingFallEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (player.ridingEntity instanceof IEntityVehicle) {
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event) {
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entity;
			if(!player.worldObj.isRemote && player.ridingEntity != null && player.ridingEntity instanceof EntityVehicle){
				EntityVehicle vehicle = (EntityVehicle) player.ridingEntity;
				PacketHandler.network.sendTo(new MessageSeverFuelAmount(VehicleHelper.FuelAmountLeft(vehicle)), (EntityPlayerMP) player);
			}
		
		}
		
	}
}