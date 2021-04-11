package cn.fusionfish.crystalgeneration.listeners;

import cn.fusionfish.crystalgeneration.crystal.CrystalGenerator;
import cn.fusionfish.crystalgeneration.crystal.CrystalManager;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Set;

import static cn.fusionfish.crystalgeneration.crystal.CrystalGenerator.CRYSTAL_BLOCK_DATA;


public class AsyncChatListener implements Listener {
    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        //CrystalGenerator.generateCrystal(player.getLocation());
        Chunk chunk = player.getLocation().getChunk();
        CrystalGenerator.generateCrystal(chunk);
    }
}
