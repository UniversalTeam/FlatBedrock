package universalteam.flatbedrock.world;

import com.google.common.collect.Maps;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import universalteam.flatbedrock.custom.CustomDimensionManager;
import universalteam.flatbedrock.world.retrogen.FlatBedrockRetroGenHandler;
import universalteam.universalcore.utils.BlockUtil;
import universalteam.universalcore.world.retrogen.RetroactiveWorldGenerator;

import java.util.Map;
import java.util.Random;

public class WorldGenFlatBedrock implements IWorldGenerator
{
	public static WorldGenFlatBedrock instance = new WorldGenFlatBedrock();

	protected Map<Integer, CustomDimensionManager.DimensionEntry> dimensions = Maps.newHashMap(CustomDimensionManager.getDimensions());

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (canGenerate(world, chunkX, chunkZ))
			generateWorld(world, chunkX, chunkZ);
	}

	public boolean canGenerate(World world, int chunkX, int chunkZ)
	{
		return world.provider.terrainType != WorldType.FLAT;
	}

	public void generateWorld(World world, int chunkX, int chunkZ)
	{
		int id = world.provider.dimensionId;

		if (dimensions.containsKey(id))
		{
			CustomDimensionManager.DimensionEntry dimension = dimensions.get(id);

			if (dimension.genBottom)
				generateBottom(world, chunkX, chunkZ, BlockUtil.getBlockForUniqueName(dimension.fillBlock));

			if (dimension.genTop)
				generateTop(world, chunkX, chunkZ, BlockUtil.getBlockForUniqueName(dimension.fillBlock));
		}
	}

	public void retroGenerateWorld(World world, int chunkX, int chunkZ)
	{
		int id = world.provider.dimensionId;

		if (dimensions.containsKey(id))
		{
			CustomDimensionManager.DimensionEntry dimension = dimensions.get(id);

			if (dimension.retroGenBottom)
				generateBottom(world, chunkX, chunkZ, BlockUtil.getBlockForUniqueName(dimension.fillBlock));

			if (dimension.retroGenTop)
				generateTop(world, chunkX, chunkZ, BlockUtil.getBlockForUniqueName(dimension.fillBlock));
		}
	}

	public void generateTop(World world, int chunkX, int chunkZ, Block block)
	{
		for (int blockX = 0; blockX < 16; blockX++)
			for (int blockZ = 0; blockZ < 16; blockZ++)
				for (int blockY = 126; blockY > 121; blockY--)
					if (world.getBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ) == Blocks.bedrock)
						world.setBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ, block, 0, 2);
	}

	public void generateBottom(World world, int chunkX, int chunkZ, Block block)
	{
		for (int blockX = 0; blockX < 16; blockX++)
			for (int blockZ = 0; blockZ < 16; blockZ++)
				for (int blockY = 5; blockY > 0; blockY--)
					if (world.getBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ) == Blocks.bedrock)
						world.setBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ, block, 0, 2);
	}
}
