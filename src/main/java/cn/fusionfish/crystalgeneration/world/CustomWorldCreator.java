package cn.fusionfish.crystalgeneration.world;

import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

public class CustomWorldCreator extends WorldCreator {
    /**
     * Creates an empty WorldCreationOptions for the given world name
     *
     * @param name Name of the world that will be created
     */
    public CustomWorldCreator(String name) {
        super(name);
        CustomChunkGenerator chunkGenerator = new CustomChunkGenerator();
        this.generator(chunkGenerator);
    }


}
