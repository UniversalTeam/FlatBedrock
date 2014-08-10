package universalteam.flatbedrock.custom;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.event.FMLInterModComms;
import universalteam.flatbedrock.config.Config;
import universalteam.flatbedrock.handler.IMCHandler;
import universalteam.flatbedrock.lib.Reference;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import static universalteam.flatbedrock.FlatBedrock.logger;

public class CustomDimensionManager
{
	public static final File dimensionsFolder = new File(Config.configLocation, "dimensions");

	protected static File overworldJSON = new File(dimensionsFolder, "overworld.json");
	protected static File netherJSON = new File(dimensionsFolder, "nether.json");
	protected static Map<Integer, DimensionEntry> dimensions = Maps.newHashMap();
	protected static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static void execute()
	{
		if (!doDefaultsExist())
			createDefaults();

		IMCHandler.processMessages(FMLInterModComms.fetchRuntimeMessages(Reference.MOD_ID));

		readJSONFiles();
	}

	public static Map<Integer, DimensionEntry> getDimensions()
	{
		return dimensions;
	}

	public static void addDimensionEntry(DimensionEntry dimension)
	{
		dimensions.put(dimension.dimID, dimension);
	}

	public static boolean doDefaultsExist()
	{
		return overworldJSON.exists() && netherJSON.exists();
	}

	public static boolean doesFileExist(String fileName)
	{
		return new File(dimensionsFolder, fileName + ".json").exists();
	}

	public static void createDefaults()
	{
		if (!overworldJSON.exists())
		{
			DimensionEntry overworld = new DimensionEntry(0, false, true, false, false, "minecraft:stone");
			createJSONFile("overworld", overworld);
		}

		if (!netherJSON.exists())
		{
			DimensionEntry nether = new DimensionEntry(-1, true, true, false, false, "minecraft:netherrack");
			createJSONFile("nether", nether);
		}
	}

	public static void createJSONFile(String fileName, DimensionEntry dimension)
	{
		File jsonFile = new File(dimensionsFolder, fileName + ".json");

		try
		{
			Files.createParentDirs(jsonFile);
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonFile)));
			writer.write(gson.toJson(dimension));
			writer.close();
			logger.info("Creating %s.json file", fileName);
		}
		catch (Exception e)
		{
			logger.severe("Failed to create the %s.json file, this could be one of the default files that create on startup or one of the IMC received files, please report this!", fileName);
			e.printStackTrace();
		}
	}

	public static void readJSONFiles()
	{
		for (File file : dimensionsFolder.listFiles(new JSONFileNameFilter()))
			readJSON(file);
	}

	public static void readJSON(File jsonFile)
	{
		DimensionEntry dimension;

		try
		{
			dimension = gson.fromJson(new FileReader(jsonFile), DimensionEntry.class);
		}
		catch (Exception e)
		{
			logger.severe("Failed to read the dimensions.json file, please report this! This could cause major issues as your settings aren't saved and the world might not generate as you expect! If this was the case you could still try to enable the retro gen in that dimension (but only do this if you really want to keep the world, and also BACKUP)!!!");
			e.printStackTrace();
			return;
		}

		addDimensionEntry(dimension);
	}

	public static class DimensionEntry
	{
		public int dimID;
		public boolean genTop;
		public boolean genBottom;
		public boolean retroGenTop;
		public boolean retroGenBottom;
		public String fillBlock;

		public DimensionEntry(int dimID, boolean genTop, boolean genBottom, boolean retroGenTop, boolean retroGenBottom, String fillBlock)
		{
			this.dimID = dimID;
			this.genTop = genTop;
			this.genBottom = genBottom;
			this.retroGenTop = retroGenTop;
			this.retroGenBottom = retroGenBottom;
			this.fillBlock = fillBlock;
		}

		public DimensionEntry()
		{

		}
	}

	public static class JSONFileNameFilter implements FilenameFilter
	{
		@Override
		public boolean accept(File dir, String name)
		{
			return name.endsWith(".json");
		}
	}
}
