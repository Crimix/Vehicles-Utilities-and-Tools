package com.black_dog20.vut.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import com.black_dog20.vut.client.settings.Keybindings;
import com.black_dog20.vut.client.handler.KeyInputEventHandler;
import com.black_dog20.vut.client.render.RenderHoverBike;
import com.black_dog20.vut.entity.EntityHoverBike;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerKeyBindings() {
		ClientRegistry.registerKeyBinding(Keybindings.START);
		ClientRegistry.registerKeyBinding(Keybindings.DOWN);
		ClientRegistry.registerKeyBinding(Keybindings.VTOL);
	}

	@Override
	public void keyinput() {
		FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
	}

	@Override
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		switch (ctx.side) {
		case CLIENT:
			EntityPlayer entityClientPlayerMP = Minecraft.getMinecraft().thePlayer;
			return entityClientPlayerMP;
		case SERVER:
			EntityPlayer entityPlayerMP = ctx.getServerHandler().playerEntity;
			return entityPlayerMP;
		}
		return null;
	}

	@Override
	public EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			EntityPlayer entityClientPlayerMP = (EntityPlayer) Minecraft.getMinecraft().theWorld.getEntityByID(id);
			return entityClientPlayerMP;
		}
		return null;
	}

	@Override
	public void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityHoverBike.class, new RenderHoverBike());
	}

	@Override
	public void ServerRecipes() {
//		TucsRegistry.RemoveRecipe(ModItems.Unbreaking3Upgrade);
//		Recipes.Upgrades();
//
//		LogHelper.info("removed " + TucsRegistry.number + " recipes");
//		TucsRegistry.number = 0;
	}

}
