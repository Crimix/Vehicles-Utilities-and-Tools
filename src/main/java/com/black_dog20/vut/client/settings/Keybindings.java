package com.black_dog20.vut.client.settings;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import com.black_dog20.vut.reference.Names;

public class Keybindings {
	
	public static KeyBinding START = new KeyBinding(Names.Keys.START, Keyboard.KEY_F, Names.Keys.CATEGORY);
	public static KeyBinding DOWN = new KeyBinding(Names.Keys.DOWN, Keyboard.KEY_C, Names.Keys.CATEGORY);
	public static KeyBinding VTOL = new KeyBinding(Names.Keys.VTOL, Keyboard.KEY_H, Names.Keys.CATEGORY);

}
