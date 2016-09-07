package com.black_dog20.vut.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import io.netty.buffer.ByteBuf;

import com.black_dog20.vut.vut;
import com.black_dog20.vut.entity.EntityHoverBike;
import com.black_dog20.vut.entity.IEntityHoverVehicle;
import com.black_dog20.vut.handler.PlayerEventHandler;
import com.black_dog20.vut.utility.NBTHelper;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSeverFuelAmount implements IMessage, IMessageHandler<MessageSeverFuelAmount, IMessage> {

	private double fuel;
	
	@Override
	public IMessage onMessage(MessageSeverFuelAmount message, MessageContext context) {
		EntityPlayer player = vut.Proxy.getPlayerFromMessageContext(context);
		NBTTagCompound nbt = NBTHelper.getPlayerNBT(player);
		nbt.setDouble("VutFuel", message.fuel);
		return null;
	}

	public MessageSeverFuelAmount() {
	}
	public MessageSeverFuelAmount(double fuel) {
		this.fuel = fuel;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(fuel);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.fuel = buf.readDouble();
	}
}
