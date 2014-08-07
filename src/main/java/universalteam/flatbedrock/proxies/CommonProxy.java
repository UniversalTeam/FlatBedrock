package universalteam.flatbedrock.proxies;

import cpw.mods.fml.common.registry.GameRegistry;
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

		CustomDimensionManager.execute();

		initWorldGenerators();

		FlatBedrockRetroGenHandler.initRetrogenerators();
	}

	public void init()
	{

	}

	public void postInit()
	{

	}

	public void initWorldGenerators()
	{
		GameRegistry.registerWorldGenerator(new WorldGenFlatBedrock(), 10);
	}
}
