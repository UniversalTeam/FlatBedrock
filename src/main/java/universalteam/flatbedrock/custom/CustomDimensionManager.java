package universalteam.flatbedrock.custom;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import universalteam.flatbedrock.config.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import static universalteam.flatbedrock.FlatBedrock.logger;

//TODO convert to different JSON files for each dimension!
public class CustomDimensionManager
{
	public static final CustomDimensionManager INSTANCE = new CustomDimensionManager();

	public static final File dimensionsJSON = new File(Config.configLocation, "dimensions.json");

	protected Map<Integer, DimensionEntry> dimensions = Maps.newHashMap();

	public static void execute()
	{
		if (!dimensionsJSON.exists())
			INSTANCE.createDefaultJSON();

		INSTANCE.readJSON();
	}

	public static Map<Integer, DimensionEntry> getDimensions()
	{
		return INSTANCE.dimensions;
	}

	public static void addDimensionEntry(DimensionEntry dimension)
	{
		INSTANCE.dimensions.put(dimension.dimID, dimension);
	}

	public void createDefaultJSON()
	{
		DimensionEntry overworld = new DimensionEntry(0, false, true, false, false, "minecraft:stone");
		DimensionEntry nether = new DimensionEntry(-1, true, true, false, false, "minecraft:netherrack");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<DimensionEntry> defaultDims = Lists.newArrayList();
		defaultDims.add(overworld);
		defaultDims.add(nether);

		try
		{
			Files.createParentDirs(dimensionsJSON);
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dimensionsJSON.getAbsolutePath())));
			writer.write(gson.toJson(defaultDims));
			writer.close();
			logger.info("Creating dimensions.json");
		}
		catch (Exception e)
		{
			logger.severe("Failed to create the dimensions.json file, please report this!");
			e.printStackTrace();
		}
	}

	public void readJSON()
	{
		Gson gson = new Gson();
		DimensionEntry[] dimensions;

		try
		{
			dimensions = gson.fromJson(new FileReader(dimensionsJSON), DimensionEntry[].class);
		}
		catch (Exception e)
		{
			logger.severe("Failed to read the dimensions.json file, please report this! This could cause major issues as your settings aren't saved and the world might not generate as you expect! If this was the case you could still try to enable the retro gen in that dimension (but only do this if you really want to keep the world, and also BACKUP)!!!");
			e.printStackTrace();
			return;
		}

		convertToMap(dimensions);
	}

	public void convertToMap(DimensionEntry[] dimensionEntries)
	{
		for (DimensionEntry dimension : dimensionEntries)
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
	}
}
