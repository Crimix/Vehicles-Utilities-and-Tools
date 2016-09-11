package com.black_dog20.vut.utility;

import net.minecraft.entity.player.EntityPlayer;

public class Coordinate {

	public double x,y,z;
	public String UUID;
	
	public Coordinate(double x, double y, double z, String UUID){
		this.x = x;
		this.y = y;
		this.z= z;
		this.UUID = UUID;
	}
}
