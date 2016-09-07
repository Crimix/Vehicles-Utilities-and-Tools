package com.black_dog20.vut.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;

import com.black_dog20.vut.nei.handlers.ItemInfoHandler;
import com.black_dog20.vut.reference.Reference;

public class NEITucsConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {

		API.registerRecipeHandler(new ItemInfoHandler());
	}

	@Override
	public String getName() {
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return Reference.VERSION;
	}

}