package cn.fusionfish.crystalgeneration.world;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Collections;
import java.util.List;

public class CustomChunkGenerator extends ChunkGenerator {
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Collections.singletonList(new CrystalPopulator());
    }


}
