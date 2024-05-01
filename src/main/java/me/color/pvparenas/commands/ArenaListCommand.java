package me.color.pvparenas.commands;

import me.color.pvparenas.arenas.Arena;
import me.color.pvparenas.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ArenaListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {

        if(!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        ArrayList<String> arenas = Arena.getArenas();


        if(arenas == null){
            player.sendMessage("0 arenas found...");
            return true;
        }

        for(int i = 0; i < arenas.size(); i++){
            player.sendMessage("[" + (i + 1)  +"] " + arenas.get(i));
        }




        return false;
    }
}
