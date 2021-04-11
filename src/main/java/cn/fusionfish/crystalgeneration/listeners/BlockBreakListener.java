package cn.fusionfish.crystalgeneration.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static cn.fusionfish.crystalgeneration.crystal.CrystalGenerator.CRYSTAL_BLOCK_DATA;

/**
 * 监测方块破坏事件
 * @author JeremyHu
 */
public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (block.getType() != Material.MUSHROOM_STEM) return;  //如果不是蘑菇梗就返回
        if (!block.getBlockData().matches(CRYSTAL_BLOCK_DATA)) return;  //如果不是水晶矿石就返回
        if (player.getGameMode() == GameMode.CREATIVE) return; //如果玩家是创造模式就返回

        event.setDropItems(false);  //避免使用精准采集掉落物品
        Location location = block.getLocation();

        Item itemEntity = (Item) location.getWorld().spawnEntity(location, EntityType.DROPPED_ITEM);  //掉落物品
        itemEntity.setItemStack(new ItemStack(Material.DIAMOND, 1));

        ExperienceOrb experienceOrb = (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);  //掉落经验球
        experienceOrb.setExperience(5);
    }
}
