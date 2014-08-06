package universalteam.flatbedrock.world;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class WorldGenFlatBedrock implements IWorldGenerator
{
	public static WorldGenFlatBedrock instance = new WorldGenFlatBedrock();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.terrainType != WorldType.FLAT)
			generateWorld(world, chunkX, chunkZ);
	}

	public void generateWorld(World world, int chunkX, int chunkZ)
	{
		if (world.provider.dimensionId == 0)
			generateOverworld(world, chunkX, chunkZ);
		if (world.provider.dimensionId == -1)
			generateNether(world, chunkX, chunkZ);
	}

	public void generateOverworld(World world, int chunkX, int chunkZ)
	{
		generateBottom(world, chunkX, chunkZ, Blocks.stone);
	}

	public void generateNether(World world, int chunkX, int chunkZ)
	{
		generateBottom(world, chunkX, chunkZ, Blocks.netherrack);
		generateTop(world, chunkX, chunkZ, Blocks.netherrack);
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
