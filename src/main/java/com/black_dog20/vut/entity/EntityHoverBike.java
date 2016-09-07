package com.black_dog20.vut.entity;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.black_dog20.vut.vut;
import com.black_dog20.vut.init.ModItems;
import com.black_dog20.vut.network.PacketHandler;
import com.black_dog20.vut.network.message.MessageSeveVehicleInteract;
import com.black_dog20.vut.utility.NBTHelper;
import com.black_dog20.vut.utility.VehicleHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityHoverBike extends EntityHoverVehicle {

	private boolean state = false;
	private double forward = 0D;
	private double startSpeed = 5D;
	private boolean downKey = false;
	private boolean upKey = false;
	private int ticks = 0;
	private boolean vtolState = false;
	private boolean landing = false;
	private double VtolY = 0;

	public EntityHoverBike(World world) {
		super(world);
		this.itemToDrop = new ItemStack(ModItems.HoverBike);
	}

	public EntityHoverBike(World world, double x, double y, double z) {
		super(world,x,y,z);
		this.itemToDrop = new ItemStack(ModItems.HoverBike);
	}

	@Override
	public void GoDown(boolean isKeyPressed){
		downKey = isKeyPressed;
	}

	@Override
	public void GoUp(boolean isKeyPressed){
		upKey = isKeyPressed;
	}

	@Override
	public void StartEngine(){
		if(CanFly() && !vtolState){
			if(state){
				state = false;
			}else{
				state = true;
			}
			startSpeed = 5D;
			if(state == true){
				this.forward = 1.107000000216066837D;
			}
			else if(!vtolState)
			{
				this.forward = 0D;
				this.motionX = 0;
				this.motionZ = 0;
			}
		}
	}
	
	@Override
	public void StartVTOL(){
		if(CanFly()){
			if(state){
				state= false;
				this.forward = 0D;
				this.motionX = 0;
				this.motionZ = 0;
				landing = true;
			}
			else{
				VtolY = VehicleHelper.VtolHight(this) + this.riddenByEntity.posY;
				vtolState = true;
			}
		}
	}


	@Override
	protected void openInventory(EntityPlayer player) {
		if(VehicleHelper.ChestUpgrade(this)!=0){
			NBTHelper.getPlayerNBT(player).setInteger("DVMVehicleId", this.getEntityId());
			if(!player.worldObj.isRemote)
				PacketHandler.network.sendTo(new MessageSeveVehicleInteract(this.getEntityId(),Owner), (EntityPlayerMP) player);
			player.openGui(vut.instance, vut.guiVehicleInventory, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
	}

	@Override
	protected void openUpgradeInventory(EntityPlayer player) {
		NBTHelper.getPlayerNBT(player).setInteger("DVMVehicleId", this.getEntityId());
		if(!player.worldObj.isRemote)
			PacketHandler.network.sendTo(new MessageSeveVehicleInteract(this.getEntityId(),Owner), (EntityPlayerMP) player);
		player.openGui(vut.instance, vut.guiVehicleUpgrade, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}

	@Override
	public double Maxspeed() {
		return 20D;
	}

	@Override
	public int Seats() {
		return 1;
	}

	@Override
	public String Name() {
		return "Speeder";
	}

	@Override
	public int Rows() {
		return 3*VehicleHelper.ChestUpgrade(this);
	}

	@Override
	public int Columns() {
		return 9;
	}

	@Override
	public int HoverHight() {
		return 3;
	}

	@Override
	public boolean CanFly() {
		return VehicleHelper.HasHoverDrive(this);
	}

	@Override
	public void VehicleBehaviour(){
		if(this.riddenByEntity == null){
			state = false;
		}
		ticks++;

		if(VehicleHelper.HasEngine(this) && VehicleHelper.HasFuelTank(this) && VehicleHelper.FuelAmountLeft(this)!= 100){
			if(!CanFly()){
			if (this.riddenByEntity != null && this.worldObj.getBlock((int) posX, (int) posY - (HoverHight()-1), (int) posZ) != Blocks.air) {
				this.motionY = 1.207000000216066837D;
			} else if (this.riddenByEntity != null && this.worldObj.getBlock((int) posX, (int) posY - HoverHight(), (int) posZ) == Blocks.air) {
				this.motionY = -1.107000000216066837D;
			} else if (this.riddenByEntity == null && this.worldObj.getBlock((int) posX, (int) posY, (int) posZ) != Blocks.air) {
				this.motionY = 1.207000000216066837D;
			} else if (this.riddenByEntity == null && this.worldObj.getBlock((int) posX, (int) posY - 1, (int) posZ) == Blocks.air) {
				this.motionY = -1.107000000216066837D;
			} else {
				this.motionY = 0;
			}
				if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
					EntityLivingBase entitylivingbase = (EntityLivingBase) this.riddenByEntity;
					float f = this.riddenByEntity.rotationYaw + -entitylivingbase.moveStrafing * 90.0F;
					this.motionX = -Math.sin((double) (f * (float) Math.PI / 180.0F)) * Maxspeed()/2 * (double) entitylivingbase.moveForward * 0.05000000074505806D;
					this.motionZ = Math.cos((double) (f * (float) Math.PI / 180.0F)) * Maxspeed()/2 * (double) entitylivingbase.moveForward * 0.05000000074505806D;
					if((entitylivingbase.moveForward != 0 || entitylivingbase.moveStrafing != 0) && ticks%20 == 0){
						VehicleHelper.SubtractFuel(this, 1);
					}
				}
				else{
					this.motionX = 0;
					this.motionZ = 0;
				}
			}
			else if(!vtolState && !landing){
				if(state && ticks%20 == 0){
					VehicleHelper.SubtractFuel(this, ((startSpeed/10)*1.5));
				}
				if(upKey && state){
					this.motionY = 1.207000000216066837D;
				}
				else if(downKey && state){
					this.motionY = -1.207000000216066837D;
				}
				else{
					this.motionY = 0;
				}



				if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
					EntityLivingBase entitylivingbase = (EntityLivingBase) this.riddenByEntity;
					float f = this.riddenByEntity.rotationYaw + -entitylivingbase.moveStrafing * 90.0F;
					if(entitylivingbase.moveForward > 0 && state){
						if(startSpeed != Maxspeed()){
							startSpeed+=1D;
						}
					}
					else if(entitylivingbase.moveForward < 0 && state){
						if(startSpeed != 5D){
							startSpeed-=1D;
						}
					}
					this.motionX = -Math.sin((double) (f * (float) Math.PI / 180.0F)) * startSpeed * this.forward * 0.05000000074505806D;
					this.motionZ = Math.cos((double) (f * (float) Math.PI / 180.0F)) * startSpeed * this.forward * 0.05000000074505806D;
				} else {
					this.forward = 0D;
					this.motionX = 0;
					this.motionZ = 0;
				}
			}
			else{
				if(vtolState){
					if (this.riddenByEntity != null && posY < VtolY) {
						this.motionY = 1.207000000216066837D;
					}
					else{
						this.motionY = 0;
						this.vtolState = false;
						this.StartEngine();
					}
					
				}else{
					if (this.riddenByEntity != null && this.worldObj.getBlock((int) posX, (int) posY - HoverHight(), (int) posZ) == Blocks.air) {
						this.motionY = -1.107000000216066837D;
					} else {
						this.motionY = 0;
						this.landing = false;
					}
				}
			}
		}
		else if(VehicleHelper.FuelAmountLeft(this)==100){
			if (this.riddenByEntity != null && this.worldObj.getBlock((int) posX, (int) posY - (HoverHight()-1), (int) posZ) != Blocks.air) {
				this.motionY = 1.207000000216066837D;
			} else if (this.riddenByEntity != null && this.worldObj.getBlock((int) posX, (int) posY - HoverHight(), (int) posZ) == Blocks.air) {
				this.motionY = -1.107000000216066837D;
			} else if (this.riddenByEntity == null && this.worldObj.getBlock((int) posX, (int) posY, (int) posZ) != Blocks.air) {
				this.motionY = 1.207000000216066837D;
			} else if (this.riddenByEntity == null && this.worldObj.getBlock((int) posX, (int) posY - 1, (int) posZ) == Blocks.air) {
				this.motionY = -1.107000000216066837D;
			} else {
				this.motionY = 0;
			}
		}
		else{
			this.motionX = 0;
			this.motionZ = 0;
			this.motionY = 0;

		}
	}
}