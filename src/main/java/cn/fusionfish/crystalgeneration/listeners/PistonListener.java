package cn.fusionfish.crystalgeneration.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 监听活塞事件
 * 防止玩家“伪造”紫晶矿石
 * @author JeremyHu
 */
public class PistonListener implements Listener {
    @EventHandler
    public void onExtend(BlockPistonExtendEvent event){  //活塞伸出时触发
        Set<Material> materials = event.getBlocks()
                .stream()
                .map(Block::getType)
                .collect(Collectors.toSet());
        if (materials.contains(Material.MUSHROOM_STEM)) event.setCancelled(true);
    }

    @EventHandler
    public void onRetract(BlockPistonRetractEvent event){  //活塞收回时触发
        Set<Material> materials = event.getBlocks()
                .stream()
                .map(Block::getType)
                .collect(Collectors.toSet());
        if (materials.contains(Material.MUSHROOM_STEM)) event.setCancelled(true);
    }
}
