package com.black_dog20.vut.waila;

import com.black_dog20.vut.entity.EntityHoverBike;
import com.black_dog20.vut.waila.providers.HoverBikeNBTProvider;

import mcp.mobius.waila.api.IWailaRegistrar;

public class Waila {
	public static void onCall(IWailaRegistrar registrar){
		registrar.registerNBTProvider(new HoverBikeNBTProvider(), EntityHoverBike.class);
		registrar.registerBodyProvider(new HoverBikeNBTProvider(), EntityHoverBike.class);
	}
	
}
