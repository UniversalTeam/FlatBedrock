package universalteam.flatbedrock.world.retrogen;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import universalteam.flatbedrock.world.WorldGenFlatBedrock;
import universalteam.universalcore.world.retrogen.IRetroGenerator;

import java.util.Random;

public class FlatBedrockRetroGenHandler
{
	public static void initRetrogenerators()
	{

	}

	public static class RetroGenFlatBedrock implements IRetroGenerator
	{
		@Override
		public String getUniqueGenerationID()
		{
			return "FlatBedrock:FlatBedrock";
		}

		@Override
		public boolean canGenerateIn(World world, Chunk chunk)
		{
			return WorldGenFlatBedrock.instance.canGenerate(world, chunk.xPosition, chunk.zPosition);
		}

		@Override
		public void generate(Random rand, World world, int chunkX, int chunkZ)
		{
			WorldGenFlatBedrock.instance.generateWorld(world, chunkX, chunkZ);
		}
	}
}
