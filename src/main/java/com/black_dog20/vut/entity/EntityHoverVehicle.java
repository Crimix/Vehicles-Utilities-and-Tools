package com.black_dog20.vut.entity;

import net.minecraft.world.World;

public class EntityHoverVehicle extends EntityVehicle implements IEntityHoverVehicle{

	public EntityHoverVehicle(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}
	
	public EntityHoverVehicle(World world, double x, double y, double z) {
		super(world,x,y,z);
	}

	@Override
	public boolean CanFly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int VtolHight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int HoverHight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void GoDown(boolean isKeyPressed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GoUp(boolean isKeyPressed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void StartEngine() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void StartVTOL() {
		// TODO Auto-generated method stub
		
	}

}
