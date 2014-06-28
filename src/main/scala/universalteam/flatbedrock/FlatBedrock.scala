package universalteam.flatbedrock

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import universalteam.flatbedrock.proxy.Proxy

@Mod(modid = "FlatBedrockx", name = "FlatBedrock", version = "@VERSION@", modLanguage = "scala", dependencies = "required-after:Forge;after:UniversalCore")
object FlatBedrock
{
    val MOD_ID = "FlatBedrockx"
    val MOD_NAME = "FlatBedrock"
    val MOD_VERSION = "@VERSION@"

    @Mod.EventHandler
    def preInit(event: FMLPreInitializationEvent)
    {
        Proxy.preInit()
    }

    @Mod.EventHandler
    def init(event: FMLInitializationEvent)
    {
        Proxy.init()
    }

    @Mod.EventHandler
    def postInit(event: FMLPostInitializationEvent)
    {
        Proxy.postInit()
    }
}
