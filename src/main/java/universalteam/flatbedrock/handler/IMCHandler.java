package universalteam.flatbedrock.handler;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.nbt.NBTTagCompound;
import universalteam.flatbedrock.custom.CustomDimensionManager;
import universalteam.flatbedrock.lib.Reference;

import java.util.List;

public class IMCHandler
{
	public static int number = 0;

	public static List<CustomDimensionManager.DimensionEntry> entries = Lists.newArrayList();

	public static void processMessages(List<FMLInterModComms.IMCMessage> messages)
	{
		for (FMLInterModComms.IMCMessage message : messages)
			if (message.key.equals("addDimension") && message.isNBTMessage() && message.getNBTValue().hasKey("dimensionID") && !hasMessage(message.getNBTValue().getInteger("dimensionID")))
				handleDimensionMessage(message.getNBTValue(), message.getSender());
	}

	public static boolean hasMessage(int dimID)
	{
		for (CustomDimensionManager.DimensionEntry entry : entries)
			if (entry.dimID == dimID)
				return true;

		return false;
	}

	public static void handleDimensionMessage(NBTTagCompound compound, String modID)
	{
		String fileName;
		CustomDimensionManager.DimensionEntry dimension = new CustomDimensionManager.DimensionEntry();

		if (compound.hasKey("fileName"))
			fileName = compound.getString("fileName");
		else
		{
			FMLLog.severe("[%s] %s didn't set a fileName value in it's IMC message, aborting! This will not flatten the bedrock in the specified dimension!", Reference.MOD_ID, modID);
			return;
		}

		if (CustomDimensionManager.doesFileExist(fileName))
			return;

		if (compound.hasKey("dimensionID"))
			dimension.dimID = compound.getInteger("dimensionID");
		else
		{
			FMLLog.severe("[%s] %s didn't set a dimensionID value in it's IMC message, aborting! This will not flatten the bedrock in the specified dimension!", Reference.MOD_ID, modID);
			return;
		}

		if (compound.hasKey("generateFlatTop"))
			dimension.genTop = compound.getBoolean("generateFlatTop");
		else
			dimension.genTop = true;

		if (compound.hasKey("generateFlatBottom"))
			dimension.genBottom = compound.getBoolean("generateFlatBottom");
		else
			dimension.genBottom = true;

		if (compound.hasKey("fillBlock"))
			dimension.fillBlock = compound.getString("fillBlock");
		else
			dimension.fillBlock = "minecraft:stone";

		dimension.retroGenTop = false;
		dimension.retroGenBottom = false;

		CustomDimensionManager.createJSONFile(fileName, dimension);
	}
}
