package me.bounser.levelboard;

import me.bounser.levelboard.PAPI.PAPIExtension;
import me.bounser.levelboard.command.ModifyCommand;
import me.bounser.levelboard.command.TabComplete;
import me.bounser.levelboard.tools.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class LevelBoard extends JavaPlugin {

    private static LevelBoard main;
    public static LevelBoard getInstance(){ return main; }

    @Override
    public void onEnable() {
        main = this;

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) new PAPIExtension(this).register();

        if(Data.getInstance().getUseCommand()){
            getCommand("addlevel").setExecutor(new ModifyCommand());
            getCommand("addlevel").setTabCompleter(new TabComplete());
        }

        this.getLogger().info("[LevelBoard] Plugin successfully loaded.");
    }

}
