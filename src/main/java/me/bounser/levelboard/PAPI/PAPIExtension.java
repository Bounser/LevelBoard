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
            return String.valueOf(p == null ? null : ((Integer.parseInt(PAPIUtils.readPlaceholder(p, Data.getInstance().getPlaceholder()))
                    + data.getBonus(p.getUniqueId())) * Data.getInstance().getMultiplier()));
        }
        if(params.equalsIgnoreCase("extra_levels")) {
            return String.valueOf(p == null ? null : data.getBonus(p.getUniqueId()));
        }

        return null;
    }


}
