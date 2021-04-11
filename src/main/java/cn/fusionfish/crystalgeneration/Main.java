package cn.fusionfish.crystalgeneration;

import cn.fusionfish.crystalgeneration.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new AsyncChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
        Bukkit.getPluginManager().registerEvents(new PistonListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChunkLoadListener(), this);

        //TODO 配置文件
    }

    public static Main getInstance() {
        return plugin;
    }
}
