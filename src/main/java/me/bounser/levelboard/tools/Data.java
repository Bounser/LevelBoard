package me.bounser.levelboard.tools;

import de.leonhard.storage.Toml;
import me.bounser.levelboard.LevelBoard;

import java.util.UUID;

public class Data {

    private static Data instance;
    private static LevelBoard main;
    public static Toml toml;

    public static Data getInstance(){
        if(instance == null){
            main = LevelBoard.getInstance();
            instance = new Data();

            toml = new Toml("PlayersBonus", LevelBoard.getInstance().getDataFolder().getPath());
            main.getConfig().options().copyDefaults();
            main.saveDefaultConfig();

            return instance;
        }

        return instance;
    }

    // Config

    public boolean getUseCommand(){ return main.getConfig().getBoolean("use_command"); }

    public String getPlaceholder(){ return main.getConfig().getString("placeholder"); }

    public int getMultiplier(){ return main.getConfig().getInt("placeholder_multiplier"); }

    // toml

    public int getBonus(UUID uuid){ return toml.get(String.valueOf(uuid)) == null ? 0 : (int) toml.get(String.valueOf(uuid)); }

    public void setBonus(UUID uuid, int value){ toml.set(String.valueOf(uuid), value); }

}
