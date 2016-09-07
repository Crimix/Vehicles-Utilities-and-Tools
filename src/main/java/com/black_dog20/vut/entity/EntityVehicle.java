package com.black_dog20.vut.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.black_dog20.vut.init.ModItems;
import com.black_dog20.vut.utility.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityVehicle extends Entity implements IEntityVehicle{
	protected boolean isEmpty;
	protected int PosRotationIncrements;
	protected double X;
	protected double Y;
	protected double Z;
	protected double Yaw;
	protected double Pitch;
	@SideOnly(Side.CLIENT)
	protected double velocityX;
	@SideOnly(Side.CLIENT)
	protected double velocityY;
	@SideOnly(Side.CLIENT)
	protected double velocityZ;
	protected String Owner;
	protected ItemStack itemToDrop;
	protected List<EntityPlayer> seats;

	public EntityVehicle(World world) {
		super(world);
		this.isEmpty = true;
		this.preventEntitySpawning = true;
		this.setSize(1.5F, 0.6F);
		this.yOffset = this.height / 2.0F;
		this.Owner = "none";
		seats = new ArrayList<EntityPlayer>();

	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they
	 * walk on. used for spiders and wolves to prevent them from trampling crops
	 */
	protected boolean canTriggerWalking() {
		return false;
	}

	protected void entityInit() {
		this.dataWatcher.addObject(18, new Integer(1));
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and
	 * blocks. This enables the entity to be pushable on contact, like boats or
	 * minecarts.
	 */
	public AxisAlignedBB getCollisionBox(Entity vehicle) {
		return vehicle.boundingBox;
	}

	/**
	 * returns the bounding box for this entity
	 */
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities
	 * when colliding.
	 */
	public boolean canBePushed() {
		return true;
	}

	public EntityVehicle(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y + (double) this.yOffset, z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding
	 * this one.
	 */
	public double getMountedYOffset() {
		return (double) this.height * 0.0D - 0.30000001192092896D;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float par1) {
		if (this.isEntityInvulnerable()) {
			return false;
		} else if (!this.worldObj.isRemote && !this.isDead) {

			boolean flag = source.getEntity() instanceof EntityPlayer && (((EntityPlayer) source.getEntity()).getDisplayName().equals(this.Owner) || this.Owner.equals("none"));
			if (flag) {
				EntityPlayer player = (EntityPlayer) source.getEntity();
				if(itemToDrop != null){
					SaveVehicleNBTToItem(itemToDrop);
					if(!player.inventory.addItemStackToInventory(itemToDrop)){
						player.dropPlayerItemWithRandomChoice(itemToDrop, false);
					}
				}
				this.setDead();
			} else if (((EntityPlayer) source.getEntity()).capabilities.isCreativeMode) {
				this.setDead();
			} else {
				ChatComponentTranslation msg = new ChatComponentTranslation("msg.message_hoverBikeNotOwnerBreak.txt");
				msg.appendText(" " + this.Owner);
				((EntityPlayer) source.getEntity()).addChatMessage(msg);
			}
			return true;
		} else {
			return true;
		}
	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	/**
	 * Sets the position and rotation. Only difference from the other one is no
	 * bounding on the rotation. Args: posX, posY, posZ, yaw, pitch
	 */
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double posX, double posY, double posZ, float yaw, float pitch, int posRotation) {
		if (this.isEmpty) {
			this.PosRotationIncrements = posRotation + 5;
		} else {
			double d3 = posX - this.posX;
			double d4 = posY - this.posY;
			double d5 = posZ - this.posZ;
			double d6 = d3 * d3 + d4 * d4 + d5 * d5;

			if (d6 <= 1.0D) {
				return;
			}

			this.PosRotationIncrements = 3;
		}

		this.X = posX;
		this.Y = posY;
		this.Z = posZ;
		this.Yaw = (double) yaw;
		this.Pitch = (double) pitch;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z) {
		this.velocityX = this.motionX = x;
		this.velocityY = this.motionY = y;
		this.velocityZ = this.motionZ = z;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		super.onUpdate();
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		double d10 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		double d2;
		double d4;

		if (d10 > 0.26249999999999996D) {
			d2 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D);
			d4 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D);

		}

		double d11;
		double d12;

		if (this.worldObj.isRemote && this.isEmpty) {
			if (this.PosRotationIncrements > 0) {
				d2 = this.posX + (this.X - this.posX) / (double) this.PosRotationIncrements;
				d4 = this.posY + (this.Y - this.posY) / (double) this.PosRotationIncrements;
				d11 = this.posZ + (this.Z - this.posZ) / (double) this.PosRotationIncrements;
				d12 = MathHelper.wrapAngleTo180_double(this.Yaw - (double) this.rotationYaw);
				this.rotationYaw = (float) ((double) this.rotationYaw + d12 / (double) this.PosRotationIncrements);
				this.rotationPitch = (float) ((double) this.rotationPitch + (this.Pitch - (double) this.rotationPitch) / (double) this.PosRotationIncrements);
				--this.PosRotationIncrements;
				this.setPosition(d2, d4, d11);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			} else {
				d2 = this.posX + this.motionX;
				d4 = this.posY + this.motionY;
				d11 = this.posZ + this.motionZ;
				this.setPosition(d2, d4, d11);
			}
		} else {
			
			VehicleBehaviour();

			this.moveEntity(this.motionX, this.motionY, this.motionZ);

			this.rotationPitch = 0.0F;
			d4 = (double) this.rotationYaw;
			d11 = this.prevPosX - this.posX;
			d12 = this.prevPosZ - this.posZ;

			if (d11 * d11 + d12 * d12 > 0.001D) {
				d4 = (double) ((float) (Math.atan2(d12, d11) * 180.0D / Math.PI));
			}

			double d7 = MathHelper.wrapAngleTo180_double(d4 - (double) this.rotationYaw);

			if (d7 > 20.0D) {
				d7 = 20.0D;
			}

			if (d7 < -20.0D) {
				d7 = -20.0D;
			}

			this.rotationYaw = (float) ((double) this.rotationYaw + d7);
			this.setRotation(this.rotationYaw, this.rotationPitch);

			if (!this.worldObj.isRemote) {
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));

				if (list != null && !list.isEmpty()) {
					for (int k1 = 0; k1 < list.size(); ++k1) {
						Entity entity = (Entity) list.get(k1);

						if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityHoverBike) {
							entity.applyEntityCollision(this);
						}
					}
				}

				if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
					this.riddenByEntity = null;
				}
			}
		}
	}

	public void updateRiderPosition() {
		if (this.riddenByEntity != null) {
			double d0 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
		}
	}
	
	public void writeOwner(){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(this);
		nbt.setString("Owner", this.Owner);
		this.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, nbt);
	}
	
	public void readOwner(){
		NBTTagCompound nbt = NBTHelper.getEntityNBT(this);
		this.Owner = nbt.getString("Owner");
	}

	public void writeEntityToNBT(NBTTagCompound nbt) {
	}

	public void readEntityFromNBT(NBTTagCompound nbt) {
		readOwner();
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.5F;
	}

	protected void openInventory(EntityPlayer player) {
	}

	/**
	 * First layer of player interaction
	 */
	public boolean interactFirst(EntityPlayer entityPlayer) {
		if (!entityPlayer.isSneaking()) {
			if (this.Owner.equals("none")) {
				this.Owner = entityPlayer.getDisplayName();
				writeOwner();
			}
			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != entityPlayer) {
				return true;
			} else {
				if (!this.worldObj.isRemote ) {
					if(entityPlayer.getDisplayName().equals(Owner))
						entityPlayer.mountEntity(this);
				else{
					if(!entityPlayer.worldObj.isRemote){
						ChatComponentTranslation msg = new ChatComponentTranslation("msg.message_hoverBikeNotOwner.txt");
						msg.appendText(" " + this.Owner);
						entityPlayer.addChatMessage(msg);
					}
				
					}
				}

				return true;
			}
		} else {
			if(entityPlayer.getHeldItem() != null && entityPlayer.getHeldItem().isItemEqual(new ItemStack(ModItems.Wrench))&& entityPlayer.getDisplayName().equals(Owner)){
				openUpgradeInventory(entityPlayer);
			}
			else if(entityPlayer.getDisplayName().equals(Owner)){
				openInventory(entityPlayer);
			}
			else{
				if(!entityPlayer.worldObj.isRemote){
					ChatComponentTranslation msg = new ChatComponentTranslation("msg.message_hoverBikeNotOwner.txt");
					msg.appendText(" " + this.Owner);
					entityPlayer.addChatMessage(msg);
				}
			}
		}
		return true;
	}

	public void setForwardDirection(int direction) {
		this.dataWatcher.updateObject(18, Integer.valueOf(direction));
	}

	public int getForwardDirection() {
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	@SideOnly(Side.CLIENT)
	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	@Override
	public double Maxspeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Seats() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Slots() {
		return Rows() * Columns();
	}

	@Override
	public String Name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Rows() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Columns() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void VehicleBehaviour() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SaveVehicleNBTToItem(ItemStack item) {
		NBTTagCompound nbtTagCompound = NBTHelper.getEntityNBT(this);
		item.stackTagCompound = nbtTagCompound;
		
	}

	protected void openUpgradeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
}
