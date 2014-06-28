package universalteam.flatbedrock.proxy

import cpw.mods.fml.relauncher.{Side, SideOnly}
import cpw.mods.fml.common.registry.GameRegistry
import universalteam.flatbedrock.world.WorldGenFlatBedrock

class CommonProxy
{
    def preInit()
    {
        GameRegistry.registerWorldGenerator(new WorldGenFlatBedrock, 10)
    }

    def init()
    {

    }

    def postInit()
    {

    }
}

class ClientProxy extends CommonProxy
{
    @SideOnly(Side.CLIENT)
    override def preInit()
    {
        super.preInit()
    }

    @SideOnly(Side.CLIENT)
    override def init()
    {
        super.init()
    }

    @SideOnly(Side.CLIENT)
    override def postInit()
    {
        super.postInit()
    }
}

object Proxy extends ClientProxy
