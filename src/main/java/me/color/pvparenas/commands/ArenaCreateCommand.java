package me.color.pvparenas.commands;

import me.color.pvparenas.events.ArenaCreateEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArenaCreateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {

        if(!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(!ArenaCreateEvent.uuid.isEmpty()){
            player.sendMessage("Arena creation is currently busy...");
            return true;
        }

        if(args.length == 0){
            player.sendMessage("Usage: /create {arenaname}");
            return true;
        }

        ArenaCreateEvent.uuid = player.getUniqueId().toString();
        ArenaCreateEvent.arenaName = args[0];

        System.out.println(args[0]);



        return false;
    }
}
