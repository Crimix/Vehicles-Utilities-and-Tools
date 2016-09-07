package com.black_dog20.vut.network.message;

import net.minecraft.entity.player.EntityPlayer;
import io.netty.buffer.ByteBuf;

import com.black_dog20.vut.vut;
import com.black_dog20.vut.entity.EntityHoverBike;
import com.black_dog20.vut.entity.IEntityHoverVehicle;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayerUpStart implements IMessage, IMessageHandler<MessagePlayerUpStart, IMessage> {

	int entityId;

	@Override
	public IMessage onMessage(MessagePlayerUpStart message, MessageContext context) {
		EntityPlayer me = vut.Proxy.getPlayerFromMessageContext(context);
		((IEntityHoverVehicle)me.ridingEntity).GoUp(true);
		return null;
	}

	public MessagePlayerUpStart() {}

	@Override
	public void toBytes(ByteBuf buf) {}

	@Override
	public void fromBytes(ByteBuf buf) {}
}
