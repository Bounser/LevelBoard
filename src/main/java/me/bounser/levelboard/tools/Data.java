package me.bounser.levelboard.tools;

import de.leonhard.storage.Toml;
import me.bounser.levelboard.LevelBoard;

import java.util.HashMap;
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

    HashMap<UUID, Integer> bonus = new HashMap<>();

    public Integer getBonus(UUID uuid) {
        if(bonus.get(uuid) != null){ return bonus.get(uuid); }
        else {
            bonus.put(uuid, toml.getInt(String.valueOf(uuid)));
            return bonus.get(uuid);
        }
    }

    public void setBonus(UUID uuid, int value) {
        if (bonus.get(uuid).equals(value)) {
            return;
        }
        toml.set(String.valueOf(uuid), value);
        bonus.put(uuid, value);
    }
}
