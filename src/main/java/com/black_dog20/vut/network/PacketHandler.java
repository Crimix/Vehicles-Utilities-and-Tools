package com.black_dog20.vut.network;

import com.black_dog20.vut.network.message.MessagePlayerDownStart;
import com.black_dog20.vut.network.message.MessagePlayerDownStop;
import com.black_dog20.vut.network.message.MessagePlayerStartEngine;
import com.black_dog20.vut.network.message.MessagePlayerStartVTOL;
import com.black_dog20.vut.network.message.MessagePlayerUpStart;
import com.black_dog20.vut.network.message.MessagePlayerUpStop;
import com.black_dog20.vut.network.message.MessageSeveVehicleInteract;
import com.black_dog20.vut.network.message.MessageSeverFuelAmount;
import com.black_dog20.vut.reference.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

	public static void init() {
//		network.registerMessage(MessageConfigSync.class, MessageConfigSync.class, 1, Side.CLIENT);
		network.registerMessage(MessagePlayerStartEngine.class, MessagePlayerStartEngine.class, 2, Side.SERVER);
		network.registerMessage(MessagePlayerDownStart.class, MessagePlayerDownStart.class, 3, Side.SERVER);
		network.registerMessage(MessagePlayerDownStop.class, MessagePlayerDownStop.class, 4, Side.SERVER);
		network.registerMessage(MessagePlayerUpStart.class, MessagePlayerUpStart.class, 5, Side.SERVER);
		network.registerMessage(MessagePlayerUpStop.class, MessagePlayerUpStop.class, 6, Side.SERVER);
		network.registerMessage(MessagePlayerStartVTOL.class, MessagePlayerStartVTOL.class, 7, Side.SERVER);
		network.registerMessage(MessageSeverFuelAmount.class, MessageSeverFuelAmount.class, 9, Side.CLIENT);
		network.registerMessage(MessageSeveVehicleInteract.class, MessageSeveVehicleInteract.class, 10, Side.CLIENT);
	}

}
