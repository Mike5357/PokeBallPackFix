package com.thenodemc.pokeballpackfix;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PokeBallPackFix extends JavaPlugin {
    private PluginManager pm;
    private PackStatusListener packStatusListener;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("PokeBallPackFix >> PokeBallPackFix by Mike5357 loaded!");
        pm = getServer().getPluginManager();
        packStatusListener = new PackStatusListener();
        pm.registerEvents(packStatusListener, this);
    }
}