package me.color.pvparenas.commands;


import me.color.pvparenas.arenas.Queue;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LeaveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {

        if (!(sender instanceof Player))
            return true;

        Player p = (Player) sender;

        if (!Queue.isInQueue(p)){
            p.sendMessage("You are not in queue...");
            return true;
        }

        p.sendMessage("You left the queue...");

        Queue.remove(p);


        return false;
    }
}
