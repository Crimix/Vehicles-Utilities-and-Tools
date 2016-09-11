package com.black_dog20.vut.handler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.black_dog20.vut.client.settings.Keybindings;
import com.black_dog20.vut.entity.EntityVehicle;
import com.black_dog20.vut.entity.IEntityHoverVehicle;
import com.black_dog20.vut.init.ModItems;
import com.black_dog20.vut.network.PacketHandler;
import com.black_dog20.vut.network.message.MessagePlayerDownStart;
import com.black_dog20.vut.network.message.MessagePlayerDownStop;
import com.black_dog20.vut.network.message.MessagePlayerUpStart;
import com.black_dog20.vut.network.message.MessagePlayerUpStop;
import com.black_dog20.vut.utility.Coordinate;
import com.black_dog20.vut.utility.NBTHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerEventHandler {

	private double speed = 0;
	private double oldX, oldY;
	private boolean active = false;
	private int ticks = 0;
	NBTTagCompound nbt;
	boolean hasChanged;
	boolean downIsPressed = false;
	boolean upIsPressed = false;
	static final DecimalFormat df = new DecimalFormat("#0.00");

	//	@SubscribeEvent
	//	public void onEntityDeath(PlayerDropsEvent event) {
	//		EntityPlayer player = event.entityPlayer;
	//		ArrayList<EntityItem> list = event.drops;
	//		NBTTagList nbttaglist = new NBTTagList();
	//		ListIterator<EntityItem> litr = list.listIterator();
	//		nbt = NBTHelper.getPlayerNBT(player);
	//		int i = 0;
	//		while (litr.hasNext()) {
	//
	//			EntityItem item = litr.next();
	//			ItemStack itemstack = item.getEntityItem();
	//
	//			if (item != null) {
	//				if (itemstack.hasTagCompound()) {
	//					NBTTagCompound itemT = itemstack.getTagCompound();
	//					if (itemT.hasKey(NBTTags.SOULBOUND) || itemT.hasKey(NBTTags.SOULBOUND_P)) {
	//						NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	//						nbttagcompound1.setByte("Slot", (byte) i);
	//						itemstack.writeToNBT(nbttagcompound1);
	//						nbttaglist.appendTag(nbttagcompound1);
	//						litr.remove();
	//						i++;
	//					}
	//				}
	//			}
	//		}
	//		nbt.setTag("SoulboundItems", nbttaglist);
	//
	//	}

	//	@SubscribeEvent
	//	public void Interact(PlayerInteractEvent event) {
	//		if (event.entityPlayer.ridingEntity instanceof IEntityVehicle) {
	//			event.setCanceled(true);
	//		}
	//	}



	@SubscribeEvent
	public void Interact(LivingUpdateEvent event) {
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entity;
			if(player.ridingEntity != null){
				if(player.worldObj.isRemote && Keybindings.DOWN.getIsKeyPressed() && !downIsPressed){
					if(player.ridingEntity instanceof IEntityHoverVehicle){
						downIsPressed = true;
						PacketHandler.network.sendToServer(new MessagePlayerDownStart());
					}
				}
				else if(player.worldObj.isRemote && !Keybindings.DOWN.getIsKeyPressed() && downIsPressed){
					if(player.ridingEntity instanceof IEntityHoverVehicle){
						downIsPressed = false;
						PacketHandler.network.sendToServer(new MessagePlayerDownStop());
					}
				}
				if(player.worldObj.isRemote && Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed() && !upIsPressed){
					if(player.ridingEntity instanceof IEntityHoverVehicle){
						upIsPressed = true;
						PacketHandler.network.sendToServer(new MessagePlayerUpStart());
					}
				}
				else if(player.worldObj.isRemote && !Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed() && upIsPressed){
					if(player.ridingEntity instanceof IEntityHoverVehicle){
						upIsPressed = false;
						PacketHandler.network.sendToServer(new MessagePlayerUpStop());
					}
				}
			}
		}
	}
	private ArrayList<Coordinate> coords = new ArrayList<Coordinate>();

	private void addLight(EntityPlayer player, Coordinate coordinate){
		player.worldObj.setLightValue(EnumSkyBlock.Block, (int) coordinate.x,
				(int) coordinate.y, (int) coordinate.z, 15);
		player.worldObj.markBlockRangeForRenderUpdate((int) coordinate.x,
				(int) coordinate.y, (int) coordinate.z, 12, 12, 12);
		player.worldObj.markBlockForUpdate((int) coordinate.x, (int) coordinate.y,
				(int) coordinate.z);
		player.worldObj.updateLightByType(EnumSkyBlock.Block, (int) coordinate.x,
				(int) coordinate.y + 1, (int) coordinate.z);
		player.worldObj.updateLightByType(EnumSkyBlock.Block, (int) coordinate.x,
				(int) coordinate.y - 1, (int) coordinate.z);
		player.worldObj.updateLightByType(EnumSkyBlock.Block,
				(int) coordinate.x + 1, (int) coordinate.y, (int) coordinate.z);
		player.worldObj.updateLightByType(EnumSkyBlock.Block,
				(int) coordinate.x - 1, (int) coordinate.y, (int) coordinate.z);
		player.worldObj.updateLightByType(EnumSkyBlock.Block, (int) coordinate.x,
				(int) coordinate.y, (int) coordinate.z + 1);
		player.worldObj.updateLightByType(EnumSkyBlock.Block, (int) coordinate.x,
				(int) coordinate.y, (int) coordinate.z - 1);

	}


	private void removeLight(EntityPlayer player, Coordinate coordinate){
		player.worldObj.updateLightByType(EnumSkyBlock.Block, (int) coordinate.x,
				(int) coordinate.y, (int) coordinate.z);
		//		player.worldObj.markBlockRangeForRenderUpdate((int) coordinate.x,
		//				(int) coordinate.y, (int) coordinate.z, 12, 12, 12);
		//		player.worldObj.markBlockForUpdate((int) coordinate.x, (int) coordinate.y,
		//				(int) coordinate.z);
	}

	private int ticksLight = 0;

	@SubscribeEvent
	public void LightUpAroundPlayer(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(ticksLight%20==0){
//				System.out.println("light");
				ticksLight=0;
				if(player.getHeldItem()!=null && player.getHeldItem().getItem() == ModItems.Engine){
					if(coords.size()>3){
						Coordinate c = coords.get(0);
						if(!(c.x == player.posX && c.y == player.posY && c.z == player.posZ)){
							removeLight(player, c);
							coords.remove(0);
						}
					}
					Coordinate coord = new Coordinate(player.posX,player.posY,player.posZ,player.getUniqueID().toString());
					coords.add(coord);
					addLight(player,coord);
				}
				else{
					ListIterator<Coordinate> litr = coords.listIterator();
					while (litr.hasNext()) {
						Coordinate c = litr.next();
						if(c.UUID.equals(player.getUniqueID().toString())){
							removeLight(player, c);
							litr.remove();
						}
					}
				}

			}
			ticksLight++;
		}
	}



	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiRender(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE) {
			return;
		}
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (player.ridingEntity != null && player.ridingEntity instanceof EntityVehicle) {
			double fuel = NBTHelper.getPlayerNBT(player).getDouble("VutFuel");
			double maxFuel = 10000;
			double procent = (fuel / maxFuel) * 100;
			Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Fuel:"+ df.format(procent) + "%", 2, 2, 0xffffff);
			Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(df.format(speed) + " blocks/s", 2, 12, 0xffffff);
		}
	}

	@SubscribeEvent
	public void CalculateSpeed(LivingUpdateEvent event) {
		if(event.entity instanceof EntityPlayer){
			EntityPlayer entity = (EntityPlayer) event.entity;
			if(entity.ridingEntity != null && entity.ridingEntity instanceof EntityVehicle){
				if(!active){
					oldX = entity.posX;
					oldY = entity.posY;
				}
				active = true;
			}
			else{
				active = false;
				speed = 0;
			}
			if(active){
				ticks++;
				if(ticks%20==0){
					speed = Math.sqrt(Math.pow(entity.posX-oldX,2)+Math.pow(entity.posY-oldY, 2));
					ticks = 0;
					oldX = entity.posX;
					oldY = entity.posY;
				}
			}
		}
	}


	//	@SubscribeEvent
	//	public void onPlayerRespawn(PlayerRespawnEvent event) {
	//		EntityPlayer player = event.player;
	//		nbt = NBTHelper.getPlayerNBT(player);
	//		NBTTagList nbttaglist = nbt.getTagList("SoulboundItems", Constants.NBT.TAG_COMPOUND);
	//		for (int i = 0; i <= nbttaglist.tagCount(); i++) {
	//			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
	//			byte b0 = nbttagcompound1.getByte("Slot");
	//			ItemStack item = ItemStack.loadItemStackFromNBT(nbttagcompound1);
	//			if (item != null && item.getItem() instanceof ItemArmor) {
	//				ItemArmor armor = (ItemArmor) item.getItem();
	//				if (player.inventory.armorInventory[InventoryHelper.getArmorPosition(armor)] == null) {
	//					player.inventory.armorInventory[InventoryHelper.getArmorPosition(armor)] = item;
	//				} else {
	//					player.inventory.addItemStackToInventory(item);
	//				}
	//
	//			} else {
	//				player.inventory.addItemStackToInventory(item);
	//			}
	//		}
	//		nbt.removeTag("SoulboundItems");
	//	}

	//	@SubscribeEvent
	//	public void onPlayerLoginEvent(PlayerLoggedInEvent event) {
	//		if (!event.player.worldObj.isRemote) {
	//			if (!MinecraftServer.getServer().isDedicatedServer()) {
	//				ConfigurationHandler.loadConfiguration();
	//				
	//			}
	//			PacketHandler.network.sendTo(new MessageConfigSync(), (EntityPlayerMP) event.player);
	//
	//		}
	//
	//	}

}