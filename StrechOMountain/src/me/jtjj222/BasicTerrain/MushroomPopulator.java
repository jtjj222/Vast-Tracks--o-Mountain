package me.jtjj222.BasicTerrain;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

/**
 * BlockPopulator that generates stone quarries.
 */
public class MushroomPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (random.nextInt(16) > 0) {
            return;
        }
        
        int rx = 2 + random.nextInt(12);
        int rz = 2 + random.nextInt(12);
        Block block = chunk.getBlock(rx, world.getHighestBlockYAt((chunk.getX() << 4) + rx, (chunk.getZ() << 4) + rz), rz);
        if (block.getBiome() != Biome.TAIGA) {
            return;
        }
        if (block.getRelative(BlockFace.DOWN).getType() != Material.GRASS) {
            return;
        }
        
        int size = 2 + random.nextInt(4);
        for (int i = 0; i <= size + 1; i++) {
            Block mushroom = block.getRelative(BlockFace.UP, i);
            mushroom.setType(Material.LOG);
            mushroom.setData((byte) 2);
            
            if (i >= size) {
                int diff = i - size;
                for (int x = -size + diff; x <= size - diff; x++) {
                    for (int z = -size + diff; z <= size - diff; z++) {
                        if (x*x + z*z < (size-diff)*(size-diff)) {
                            mushroom.getRelative(x, 0, z).setType(Material.STONE);
                        }
                    }
                }
            }
        }
    }

}
