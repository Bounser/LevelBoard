package me.bounser.levelboard.command;

import me.bounser.levelboard.tools.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BonusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Data data = Data.getInstance();

        if(!data.getUseCommand()){
            sender.sendMessage(ChatColor.RED + "Command not enabled.");
            return false;
        }

        if(sender instanceof Player && !sender.hasPermission("levelboard.admin")){
            sender.sendMessage(ChatColor.RED + "You don't have the required permission! (levelboard.admin)");
            return false;
        }

        if(args.length != 2){
            sender.sendMessage(ChatColor.RED + "Invalid use of the command. /addbonus <player> <bonus>");
            return false;
        } else {
            if(Bukkit.getPlayer(args[0]) == null){
                sender.sendMessage(ChatColor.RED + "That is not a valid player!");
                return false;
            }
            if(!args[1].matches("-?\\d+")){
                sender.sendMessage(ChatColor.RED + "That bonus isn't valid! (Only integers are allowed)");
                return false;
            }

            UUID uuid = Bukkit.getPlayer(args[0]).getUniqueId();
            int preBonus = data.getBonus(uuid);

            data.setBonus(uuid, Integer.parseInt(args[1]) + preBonus);

            sender.sendMessage(ChatColor.GREEN + "You have added " + ChatColor.GRAY + args[1] + ChatColor.GREEN +
                    " level(s) to " + ChatColor.GRAY + args[0] + ChatColor.GREEN + ". Now he has " + ChatColor.GRAY +
                    data.getBonus(uuid) + ChatColor.GREEN + " in total.");
        }

        return false;
    }

}
