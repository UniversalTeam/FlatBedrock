package universalteam.flatbedrock.proxies;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import universalteam.flatbedrock.custom.CustomDimensionManager;
import universalteam.flatbedrock.handler.IMCHandler;
import universalteam.flatbedrock.lib.Reference;
import universalteam.flatbedrock.world.WorldGenFlatBedrock;
import universalteam.flatbedrock.world.retrogen.FlatBedrockRetroGenHandler;
import universalteam.universalcore.version.UCVersion;
import universalteam.universalcore.version.UCVersionChecker;

public class CommonProxy
{
	public void preInit()
	{
		UCVersionChecker.registerModVersion(new UCVersion(Reference.MOD_VERSION, "https://raw.githubusercontent.com/UniversalTeam/UCModVersions/master/FlatBedrock/version.json"));

		sendTestMessage();
	}

	public void init()
	{
		IMCHandler.processMessages(FMLInterModComms.fetchRuntimeMessages(Reference.MOD_ID));

		CustomDimensionManager.execute();

		initWorldGenerators();

		FlatBedrockRetroGenHandler.initRetrogenerators();
	}

	public void postInit()
	{

	}

	public void initWorldGenerators()
	{
		GameRegistry.registerWorldGenerator(new WorldGenFlatBedrock(), 10);
	}

	protected void sendTestMessage()
	{
		NBTTagCompound compound = new NBTTagCompound();
		FMLInterModComms.sendRuntimeMessage(Reference.MOD_ID, Reference.MOD_ID, "addDimension", compound);
	}
}
