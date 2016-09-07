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

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSeveVehicleInteract implements IMessage, IMessageHandler<MessageSeveVehicleInteract, IMessage> {

	private int id;
	private String owner;
	
	@Override
	public IMessage onMessage(MessageSeveVehicleInteract message, MessageContext context) {
		EntityPlayer player = vut.Proxy.getPlayerFromMessageContext(context);
		NBTTagCompound nbt = NBTHelper.getPlayerNBT(player);
		nbt.setInteger("DVMVehicleId", message.id);
		nbt.setString("DVMVehicleOwner", message.owner);
		return null;
	}

	public MessageSeveVehicleInteract() {
	}
	public MessageSeveVehicleInteract(int id,String owner) {
		this.id = id;
		this.owner = owner;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		ByteBufUtils.writeUTF8String(buf, owner);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.owner = ByteBufUtils.readUTF8String(buf);
	}
}
