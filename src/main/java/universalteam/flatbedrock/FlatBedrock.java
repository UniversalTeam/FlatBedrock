package universalteam.flatbedrock;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import universalteam.flatbedrock.handler.IMCHandler;
import universalteam.flatbedrock.lib.Reference;
import universalteam.flatbedrock.proxies.CommonProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = "after:UniversalCore")
public class FlatBedrock
{
	@Mod.Instance(Reference.MOD_ID)
	public static FlatBedrock instance;

	@SidedProxy(clientSide = "universalteam.flatbedrock.proxies.ClientProxy", serverSide = "universalteam.flatbedrock.proxies.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}

	@Mod.EventHandler
	public void handleIMCMessages(FMLInterModComms.IMCEvent event)
	{
		IMCHandler.processMessages(event.getMessages());
	}
}
