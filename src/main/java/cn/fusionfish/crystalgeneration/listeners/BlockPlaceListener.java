package cn.fusionfish.crystalgeneration.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * 监听玩家放置方块事件
 * 防止玩家“伪造”紫晶矿石
 * @author JeremyHu
 */
public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.MUSHROOM_STEM) event.setCancelled(true);  //如果放置的方块是蘑菇梗，返回
    }
}
