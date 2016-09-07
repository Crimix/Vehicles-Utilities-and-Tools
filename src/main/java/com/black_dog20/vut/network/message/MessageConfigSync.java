package com.black_dog20.vut.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;

import com.black_dog20.vut.vut;
import com.black_dog20.vut.handler.ConfigurationHandler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage> {

	@Override
	public IMessage onMessage(MessageConfigSync message, MessageContext context) {
		vut.instance.Proxy.ServerRecipes();
		return null;
	}

	public MessageConfigSync() {}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		ConfigurationHandler.configurationServer = true;

	}
}
