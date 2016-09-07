package com.black_dog20.vut.entity;

import net.minecraft.entity.player.EntityPlayer;

public interface IEntityHoverVehicle {

	public boolean CanFly();
	
	public int VtolHight();
	
	public int HoverHight();
	
	public void GoDown(boolean isKeyPressed);
	
	public void GoUp(boolean isKeyPressed);
	
	public void StartEngine();
	public void StartVTOL();
}
