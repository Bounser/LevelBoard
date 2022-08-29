package me.bounser.levelboard.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {

    static public List<String> getPlayerNames(){

        List<String> playerNames = new ArrayList<>();
        for(Player p : Bukkit.getOnlinePlayers()){
            playerNames.add(p.getName());
        }

        return playerNames;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("levelboard.admin")) return null;

        final List<String> completes = new ArrayList<>();

        StringUtil.copyPartialMatches(args[0], getPlayerNames(), completes);
        Collections.sort(completes);

        return completes;
    }
}
