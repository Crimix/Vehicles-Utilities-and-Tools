package com.black_dog20.vut.client.handler;

import net.minecraft.entity.player.EntityPlayer;

import com.black_dog20.vut.vut;
import com.black_dog20.vut.client.settings.Keybindings;
import com.black_dog20.vut.entity.EntityHoverBike;
import com.black_dog20.vut.entity.EntityVehicle;
import com.black_dog20.vut.entity.IEntityHoverVehicle;
import com.black_dog20.vut.network.PacketHandler;
import com.black_dog20.vut.network.message.MessagePlayerStartEngine;
import com.black_dog20.vut.network.message.MessagePlayerStartVTOL;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		if (Keybindings.fly.isPressed()){
			if (FMLClientHandler.instance().getClientPlayerEntity() != null) {
				EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();
				if(entityPlayer.ridingEntity instanceof IEntityHoverVehicle){
					PacketHandler.network.sendToServer(new MessagePlayerStartEngine());
				}
			}
		}
		if (Keybindings.VTOL.isPressed()){
			if (FMLClientHandler.instance().getClientPlayerEntity() != null) {
				EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();
				if(entityPlayer.ridingEntity instanceof IEntityHoverVehicle){
					PacketHandler.network.sendToServer(new MessagePlayerStartVTOL());
				}
			}
		}
	}
}
