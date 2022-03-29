package com.nekozouneko.nekopvp.nekopvp;

import com.nekozouneko.nekopvp.nekopvp.cmd.*;
import com.nekozouneko.nekopvp.nekopvp.listener.deathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NekoPvP extends JavaPlugin {

    public static NekoPvP instance;
    public static NekoPvP getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getResource("");
        saveDefaultConfig();
        getCommand("start").setExecutor(new startCmd());
        getCommand("nekopvp").setExecutor(new mainCmd());
        getCommand("randomize").setExecutor(new randomizeCmd());
        getCommand("teambar").setExecutor(new teambarCmd());
        getCommand("countdown").setExecutor(new countdownCmd());

        getServer().getPluginManager().registerEvents(new deathListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
