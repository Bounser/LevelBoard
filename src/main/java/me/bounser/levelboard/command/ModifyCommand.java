package me.bounser.levelboard.command;

import me.bounser.levelboard.tools.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ModifyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!Data.getInstance().getUseCommand()){
            sender.sendMessage(ChatColor.RED + "Command not enabled.");
            return false;
        }

        if(sender instanceof Player && !sender.hasPermission("levelboard.admin")){
            sender.sendMessage(ChatColor.RED + "You don't have the requiered permission! (levelboard.admin)");
            return false;
        }

        if(args.length != 2){
            sender.sendMessage(ChatColor.RED + "Invalid use of the command. /addlevel <player> <levels>");
            return false;
        } else {
            if(Bukkit.getPlayer(args[0]) == null){
                sender.sendMessage(ChatColor.RED + "That is not a valid player!");
                return false;
            }
            if(!args[1].matches("-?\\d+")){
                sender.sendMessage(ChatColor.RED + "That level isn`t valid! (Only integers are allowed)");
            }
            Data data = Data.getInstance();

            UUID uuid = Bukkit.getPlayer(args[0]).getUniqueId();
            int preBonus = Data.getInstance().getBonus(uuid);

            data.setBonus(uuid, Integer.parseInt(args[1]) + preBonus);

            sender.sendMessage(ChatColor.GREEN + "You have added " + args[1] + " level(s) to " + args[0] + ". Now he has " + Data.getInstance().getBonus(uuid));

        }

        return false;
    }

}
