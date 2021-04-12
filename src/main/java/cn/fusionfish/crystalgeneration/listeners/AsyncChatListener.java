package cn.fusionfish.crystalgeneration.listeners;

import cn.fusionfish.crystalgeneration.crystal.CrystalGenerator;
import cn.fusionfish.crystalgeneration.crystal.CrystalManager;
import cn.fusionfish.crystalgeneration.world.CrystalPopulator;
import cn.fusionfish.crystalgeneration.world.CustomWorldCreator;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Random;
import java.util.Set;

import static cn.fusionfish.crystalgeneration.crystal.CrystalGenerator.CRYSTAL_BLOCK_DATA;


public class AsyncChatListener implements Listener {
    @EventHandler
    public void onChat(PlayerChatEvent event) {
        switch (event.getMessage()) {
            case "1":


        }
        Player player = event.getPlayer();
        CrystalPopulator crystalPopulator = new CrystalPopulator();
        crystalPopulator.populate(player.getWorld(), new Random(), player.getChunk());
    }
}
