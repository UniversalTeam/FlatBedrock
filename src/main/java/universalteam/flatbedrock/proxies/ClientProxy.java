package universalteam.flatbedrock.proxies;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.nbt.NBTTagCompound;
import universalteam.flatbedrock.lib.Reference;
import universalteam.universalcore.version.UCVersion;
import universalteam.universalcore.version.UCVersionChecker;

public class ClientProxy extends CommonProxy
{
	public void preInit()
	{
		super.preInit();

		initVersionChecker();
	}

	public void init()
	{
		super.init();
	}

	public void postInit()
	{
		super.postInit();
	}

	public void initVersionChecker()
	{
		if (Loader.isModLoaded("UniversalCore"))
			UCVersionChecker.registerModVersion(new UCVersion(Reference.MOD_VERSION, "https://raw.githubusercontent.com/UniversalTeam/UCModVersions/master/FlatBedrock/version.json"));
		else
		{
			NBTTagCompound compound = new NBTTagCompound();
			compound.setString("curseProjectName", "78886-flatbedrockx");
			compound.setString("curseFilenameParser", "FlatBedrockx-[].jar");
			FMLInterModComms.sendRuntimeMessage(Reference.MOD_ID, "VersionChecke", "addCurseCheck", compound);
		}
	}
}
