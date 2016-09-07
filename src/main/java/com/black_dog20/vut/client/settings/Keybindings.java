package com.black_dog20.vut.client.settings;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

public class Keybindings {
	
	public static KeyBinding fly = new KeyBinding("Start", Keyboard.KEY_F, "DVM");
	public static KeyBinding down = new KeyBinding("Down", Keyboard.KEY_C, "DVM");
	public static KeyBinding VTOL = new KeyBinding("VTOL", Keyboard.KEY_H, "DVM");

}
