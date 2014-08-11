package universalteam.flatbedrock.proxies;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import universalteam.flatbedrock.custom.CustomDimensionManager;
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
	}

	public void init()
	{
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
		compound.setString("fileName", "testDimension");
		compound.setInteger("dimensionID", 555);
		compound.setBoolean("generateFlatTop", true);
		compound.setBoolean("generateFlatBottom", false);
		compound.setString("fillBlock", "minecraft:end_stone");
		FMLInterModComms.sendRuntimeMessage(Reference.MOD_ID, Reference.MOD_ID, "addDimension", compound);
	}
}
