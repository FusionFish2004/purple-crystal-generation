package cn.fusionfish.crystalgeneration.crystal;

import cn.fusionfish.crystalgeneration.Main;
import com.google.common.collect.Sets;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Set;
import java.util.stream.Collectors;

import static cn.fusionfish.crystalgeneration.crystal.CrystalGenerator.CRYSTAL_BLOCK_DATA;
import static cn.fusionfish.crystalgeneration.crystal.CrystalGenerator.GENERATE_THRESHOLD_Y;

/**
 * 管理所有在世界中的水晶
 * @author JeremyHu
 */
public class CrystalManager {

    /**
     * 获取某区块内所有水晶
     * @param chunk 目标区块
     * @return 所有水晶的位置
     */
    public static Set<Location> getCrystals(Chunk chunk) {
        return getAllInArea(chunk).stream()
                .filter(location -> location.getBlock().getBlockData().matches(CRYSTAL_BLOCK_DATA))  //排除不是水晶的方块
                .collect(Collectors.toSet());
    }

    /**
     * 遍历在区块内所有可能生成的位置
     * @param chunk 目标区块
     * @return 所有位置的集合
     */
    protected static Set<Location> getAllInArea(Chunk chunk) {
        final long origin_x = chunk.getX() * 16L;  //获取遍历的起点坐标
        final long origin_z = chunk.getZ() * 16L;
        final long max_x = origin_x + 16L;
        final long max_z = origin_z + 16L;
        Set<Location> locations = Sets.newHashSet();
        for (long x = origin_x; x < max_x; x++) {  //开始遍历
            for (long z = origin_z; z < max_z; z++) {
                for (long y = 0; y < GENERATE_THRESHOLD_Y; y++) {
                    Location location = new Location(chunk.getWorld(), x, y, z);
                    locations.add(location);  //将位置存入集合
                }
            }
        }
        return locations;
    }
}
