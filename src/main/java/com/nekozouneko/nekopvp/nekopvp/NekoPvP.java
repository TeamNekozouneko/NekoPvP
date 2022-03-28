package com.nekozouneko.nekopvp.nekopvp;

import com.nekozouneko.nekopvp.nekopvp.cmd.mainCmd;
import com.nekozouneko.nekopvp.nekopvp.cmd.randomizeCmd;
import com.nekozouneko.nekopvp.nekopvp.cmd.startCmd;
import com.nekozouneko.nekopvp.nekopvp.cmd.teambarCmd;
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
        saveDefaultConfig();
        getCommand("start").setExecutor(new startCmd());
        getCommand("nekopvp").setExecutor(new mainCmd());
        getCommand("randomize").setExecutor(new randomizeCmd());
        getCommand("teambar").setExecutor(new teambarCmd());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
