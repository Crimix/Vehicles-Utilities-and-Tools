package com.black_dog20.vut.waila.providers;

import java.text.DecimalFormat;
import java.util.List;

import com.black_dog20.vut.reference.VehicleNBTTags;
import com.black_dog20.vut.utility.NBTHelper;
import com.black_dog20.vut.utility.VehicleHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;

public class HoverBikeNBTProvider implements IWailaEntityProvider{
	
	static final DecimalFormat df = new DecimalFormat("#0.00");
	
	@Override
	public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		NBTTagCompound nbt = accessor.getNBTData();
		String owner = "none";
		if(!nbt.getString("Owner").isEmpty()){
			owner = nbt.getString("Owner");
		}
		currenttip.add("Owner: "+owner);
		currenttip.add("Fuel: "+nbt.getString("Fuel"));
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
		NBTTagCompound nbt = NBTHelper.getEntityNBT(ent);
		tag.setString("Owner", nbt.getString("Owner"));
		tag.setString("Fuel",df.format(VehicleHelper.FuelAmountLeft(nbt))+"mb / 10000mb");
		return tag;
	}

}
