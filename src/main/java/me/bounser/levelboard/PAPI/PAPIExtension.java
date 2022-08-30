package me.bounser.levelboard.PAPI;

import me.bounser.levelboard.LevelBoard;
import me.bounser.levelboard.tools.Data;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PAPIExtension extends PlaceholderExpansion {

    Data data = Data.getInstance();
    private final LevelBoard main;

    public PAPIExtension(LevelBoard main) {
        this.main = main;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "levelboard";
    }

    @Override
    public @NotNull String getAuthor() {
        return "bounser";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer p, @NotNull String params){

        if(params.equalsIgnoreCase("level")) {
            return String.valueOf(p == null ? 0 : ((Integer.parseInt(PAPIUtils.readPlaceholder(p, data.getPlaceholder()))
                    * data.getMultiplier()) + data.getBonus(p.getUniqueId())));
        }
        if(params.equalsIgnoreCase("bonus")) {
            return String.valueOf(p == null ? 0 : data.getBonus(p.getUniqueId()));
        }

        return null;
    }


}
