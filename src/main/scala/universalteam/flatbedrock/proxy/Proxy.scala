package universalteam.flatbedrock.proxy

import cpw.mods.fml.relauncher.{Side, SideOnly}

class CommonProxy
{
    def preInit
    {

    }

    def init
    {

    }

    def postInit
    {

    }
}

class ClientProxy extends CommonProxy
{
    @SideOnly(Side.CLIENT)
    override def preInit
    {
        super.preInit()
    }

    @SideOnly(Side.CLIENT)
    override def init
    {
        super.init()
    }

    @SideOnly(Side.CLIENT)
    override def postInit
    {
        super.postInit()
    }
}

object Proxy extends ClientProxy
