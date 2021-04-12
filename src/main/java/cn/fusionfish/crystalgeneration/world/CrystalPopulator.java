package cn.fusionfish.crystalgeneration.world;

import cn.fusionfish.crystalgeneration.crystal.CrystalGenerator;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

import static cn.fusionfish.crystalgeneration.crystal.CrystalGenerator.*;

/**
 * 世界装饰器，用于在生成世界时生成水晶
 * @author JeremyHu
 */
public class CrystalPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk source) {
        int X, Y, Z;
        boolean isStone;
        for (int i = 1; i < 15; i++) {  // Number of tries
            if (random.nextInt(100) < 60) {  // The chance of spawning
                X = random.nextInt(15);
                Z = random.nextInt(15);
                Y = random.nextInt(GENERATE_THRESHOLD_Y);  // Get randomized coordinates
                if (world.getBlockAt(X, Y, Z).getType() == Material.STONE) {
                    isStone = true;
                    while (isStone) {
                        //world.getBlockAt(X, Y, Z).setType(Material.COAL_ORE);
                        CrystalGenerator.generateCrystal(new Location(world, X, Y, Z));
                        if (random.nextInt(100) < 15)  {   // The chance of continuing the vein
                            switch (random.nextInt(6)) {  // The direction chooser
                                case 0: X++; break;
                                case 1: Y++; break;
                                case 2: Z++; break;
                                case 3: X--; break;
                                case 4: Y--; break;
                                case 5: Z--; break;
                            }
                            isStone = (world.getBlockAt(X, Y, Z).getType() == Material.STONE) && (world.getBlockAt(X, Y, Z).getType() != Material.MUSHROOM_STEM);
                        } else isStone = false;
                    }
                }
            }
        }
    }
}
