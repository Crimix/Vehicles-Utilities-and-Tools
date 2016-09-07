package com.black_dog20.vut;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.black_dog20.vut.entity.EntityHoverBike;
import com.black_dog20.vut.handler.ConfigurationHandler;
import com.black_dog20.vut.handler.EventHandler;
import com.black_dog20.vut.handler.GuiHandler;
import com.black_dog20.vut.handler.ModLivingDropsEvent;
import com.black_dog20.vut.handler.PlayerEventHandler;
import com.black_dog20.vut.init.ModBlocks;
import com.black_dog20.vut.init.ModItems;
import com.black_dog20.vut.init.Recipes;
import com.black_dog20.vut.network.PacketHandler;
import com.black_dog20.vut.proxies.IProxy;
import com.black_dog20.vut.reference.Reference;
import com.black_dog20.vut.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class vut {

	@Mod.Instance(Reference.MOD_ID)
	public static vut instance = new vut();

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy Proxy;

	public static final int guiVehicleInventory = 1;
	public static final int guiVehicleUpgrade = 2;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		MinecraftForge.EVENT_BUS.register(new ModLivingDropsEvent());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		FMLCommonHandler.instance().bus().register(new EventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		FMLCommonHandler.instance().bus().register(new PlayerEventHandler());
		Proxy.registerKeyBindings();
		ModItems.init();
		ModBlocks.init();
		PacketHandler.init();
		Proxy.registerRenders();
		FMLInterModComms.sendMessage("Waila", "register", "com.black_dog20.vut.waila.Waila.onCall");

		LogHelper.info("Pre Initialization Complete!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		LogHelper.info("Initialization Complete!");
		Proxy.keyinput();
		Recipes.init();
		EntityRegistry.registerModEntity(EntityHoverBike.class, "Speeder", 0, instance, 80, 1, true);
}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		LogHelper.info("Post Initialization Complete!");
	}

	public void reloadRecipes() {
		Recipes.init();
	}
}
