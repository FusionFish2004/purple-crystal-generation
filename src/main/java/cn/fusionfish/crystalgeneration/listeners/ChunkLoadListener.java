package cn.fusionfish.crystalgeneration.listeners;

import cn.fusionfish.crystalgeneration.crystal.CrystalGenerator;
import cn.fusionfish.crystalgeneration.crystal.CrystalManager;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

/**
 * 监听区块加载时间
 * @author JeremyHu
 */
public class ChunkLoadListener implements Listener {
    @EventHandler
    public void onLoad(ChunkLoadEvent event) {
        Chunk chunk = event.getChunk();
        if (CrystalManager.getCrystals(chunk).isEmpty()) return;  //若区块内未采集全部水晶，则返回
        CrystalGenerator.generateCrystal(chunk);  //生成水晶
    }
}
