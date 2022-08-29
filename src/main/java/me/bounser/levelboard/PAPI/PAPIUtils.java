package me.bounser.levelboard.PAPI;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;

public class PAPIUtils {

    public static String readPlaceholder(OfflinePlayer p, String placeholder){

        if(placeholder == null || placeholder.equals("none")) return String.valueOf(0);

        return PlaceholderAPI.setPlaceholders(p, placeholder);

    }

}
