package universalteam.flatbedrock.proxies;

import cpw.mods.fml.common.registry.GameRegistry;
import universalteam.flatbedrock.world.WorldGenFlatBedrock;

public class CommonProxy
{
	public void preInit()
	{
		initWorldGenerators();
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
