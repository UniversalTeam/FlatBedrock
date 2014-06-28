package universalteam.flatbedrock.world

import cpw.mods.fml.common.IWorldGenerator
import java.util.Random
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.{WorldType, World}
import net.minecraft.init.Blocks
import net.minecraft.block.Block

class WorldGenFlatBedrock extends IWorldGenerator
{
    def generate(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkProvider, chunkProvider: IChunkProvider)
    {
        if (world.provider.terrainType != WorldType.FLAT)
            generateWorld(chunkX, chunkZ, world)
    }

    def generateWorld(chunkX: Int, chunkZ: Int, world: World)
    {
        if (world.provider.dimensionId == 0)
            generateOverworld(chunkX, chunkZ, world)
        if (world.provider.dimensionId == -1)
            generateNether(chunkX, chunkZ, world)
    }

    def generateOverworld(chunkX: Int, chunkZ: Int, world: World)
    {
        generateFlatBottom(chunkX, chunkZ, world, Blocks.stone)
    }

    def generateNether(chunkX: Int, chunkZ: Int, world: World)
    {
        generateFlatTop(chunkX, chunkZ, world, Blocks.netherrack)
        generateFlatBottom(chunkX, chunkZ, world, Blocks.netherrack)
    }

    def generateFlatTop(chunkX: Int, chunkZ: Int, world: World, block: Block)
    {
        for (blockX <- 1 to 16)
            for (blockZ <- 1 to 16)
                for (blockY <- 121 to 126)
                    if (world.getBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ) == Blocks.bedrock)
                        world.setBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ, block, 0, 2)
    }

    def generateFlatBottom(chunkX: Int, chunkZ: Int, world: World, block: Block)
    {
        for (blockX <- 1 to 16)
            for (blockZ <- 1 to 16)
                for (blockY <- 1 to 5)
                    if (world.getBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ) == Blocks.bedrock)
                        world.setBlock(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ, block, 0, 2)
    }
}

object WorldGenFlatBedrock
{
    lazy val instance = new WorldGenFlatBedrock
}
