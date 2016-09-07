package com.black_dog20.vut.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public interface IProxy {

	public abstract void registerKeyBindings();

	public abstract void keyinput();

	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx);

	EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx);

	public void registerRenders();

	public void ServerRecipes();


}
