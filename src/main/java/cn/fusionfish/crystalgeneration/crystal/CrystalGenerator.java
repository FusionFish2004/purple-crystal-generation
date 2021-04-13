package cn.fusionfish.crystalgeneration.crystal;

import com.google.common.collect.Sets;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class CrystalGenerator {

    public static final String CRYSTAL_BLOCK_DATA_STRING = "[up=false,down=false,north=false,south=false,west=false,east=false]";
    public static final BlockData CRYSTAL_BLOCK_DATA = Bukkit.createBlockData(Material.MUSHROOM_STEM, CRYSTAL_BLOCK_DATA_STRING);
    public static final int GENERATE_THRESHOLD_Y = 11;
    public static final int GENERATE_THRESHOLD_CHUNK_AMOUNT = 10;
    public static final String GENERATE_WORLD_NAME = "world";

    /**
     * 在指定位置生成一个水晶
     * @param location 目标位置
     */
    public static void generateCrystal(Location location) {
        Block block = location.getBlock();
        generateCrystal(block);
    }

    /**
     * 在指定方块位置生成水晶
     * @param block 目标方块
     */
    public static void generateCrystal(Block block) {
        block.setBlockData(CRYSTAL_BLOCK_DATA);
    }

    /**
     * 获取一个区块内所有有效的位置
     * @param chunk 目标区块
     * @return 有效位置集合
     */
    @Deprecated
    protected static Set<Location> getAvailableLocations(Chunk chunk) {
        return CrystalManager.getAllInArea(chunk).stream()
                .filter(location -> location.getBlock().getType() == Material.STONE)  //生成时将石头替换为矿物
                .filter(location -> {
                    for (Location loc : getNearLocations(location)) {
                        if (loc.getBlock().isPassable()) {  //周围有空气的位置才生成
                            return true;
                        }
                    }
                    return false;
                }).collect(Collectors.toSet());
    }

    /**
     * 在指定区块生成指定数量的水晶
     * @param chunk 目标区块
     */
    public static void generateCrystal(Chunk chunk) {
        /*
        Set<Location> bufferLocations = new HashSet<>(getAvailableLocations(chunk));
        if (bufferLocations.isEmpty()) return;
        for (int i = 0; i <= GENERATE_THRESHOLD_CHUNK_AMOUNT; i++) {
            if (bufferLocations.isEmpty()) break;  //若无有效位置则跳出循环
            Location location = bufferLocations.iterator().next();
            bufferLocations.remove(location);
            generateCrystal(location);
        }
        Main.getInstance().getLogger().info("Crystal generated at chunk[" + chunk.getX() + "," + chunk.getZ() + "]");
         */
        World world = chunk.getWorld();
        Random random = new Random(world.getSeed());

        int crystalNum = 0;  //生成成功水晶的数量
        while (crystalNum < GENERATE_THRESHOLD_CHUNK_AMOUNT) {
            int x = random.nextInt(16);  //产生随机位置
            int z = random.nextInt(16);
            int y = random.nextInt(GENERATE_THRESHOLD_Y);
            Block block = chunk.getBlock(x,y,z);
            if (isAvailable(block)) {
                generateCrystal(block.getLocation());
                crystalNum ++;
            }
        }
    }

    /**
     * 检测方块是否符合要求
     * @param block 目标方块
     * @return 是否符合
     */
    protected static boolean isAvailable(Block block) {
        return (block.getType() == Material.STONE);
    }

    /**
     * 获取紧邻某方块坐标的五个方块坐标
     * @param location 方块坐标
     * @return 方块坐标集合
     */
    protected static Set<Location> getNearLocations(Location location) {
        Location loc_up = location.clone().add(0,1,0);
        Location loc_down = location.clone().add(0,-1,0);
        Location loc_north = location.clone().add(0,0,-1);
        Location loc_south = location.clone().add(0,0,1);
        Location loc_east = location.clone().add(1,0,0);
        Location loc_west = location.clone().add(-1,0,0);
        return Sets.newHashSet(loc_up,loc_down,loc_north,loc_south,loc_east,loc_west);
    }
}
