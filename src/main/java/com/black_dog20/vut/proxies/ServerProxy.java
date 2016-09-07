package com.black_dog20.vut.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy extends CommonProxy {

	@Override
	public void registerKeyBindings() {
		// NOOP

	}

	@Override
	public void keyinput() {
		// TODO Auto-generated method stub

	}

	@Override
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}

	@Override
	public EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerRenders() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ServerRecipes() {
		// TODO Auto-generated method stub

	}
}
