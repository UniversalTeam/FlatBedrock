package universalteam.flatbedrock.handler;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.nbt.NBTTagCompound;
import universalteam.flatbedrock.custom.CustomDimensionManager;

import java.util.List;

public class IMCHandler
{
	public static List<CustomDimensionManager.DimensionEntry> entries = Lists.newArrayList();

	public static void processMessages(List<FMLInterModComms.IMCMessage> messages)
	{
		for (FMLInterModComms.IMCMessage message : messages)
			if (message.key.equals("addDimension") && message.isNBTMessage() && message.getNBTValue().hasKey("dimensionID") && !hasMessage(message.getNBTValue().getInteger("dimensionID")))
				handleDimensionMessage(message.getNBTValue());
	}

	public static boolean hasMessage(int dimID)
	{
		for (CustomDimensionManager.DimensionEntry entry : entries)
			if (entry.dimID == dimID)
				return true;

		return false;
	}

	public static void handleDimensionMessage(NBTTagCompound compound)
	{
		
	}
}
