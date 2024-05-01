package me.color.pvparenas.commands;

import me.color.pvparenas.Pvparenas;
import me.color.pvparenas.arenas.Arena;
import me.color.pvparenas.arenas.Game;
import me.color.pvparenas.arenas.Queue;
import me.color.pvparenas.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getServer;

public class QueueCommand implements CommandExecutor {



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, @NotNull String[] args) {


        if (!(sender instanceof Player))
            return true;

        Player p = (Player) sender;

        if (Queue.isInQueue(p)){
            p.sendMessage("You are already in queue...");
            return true;
        }

        if (Game.isPlayerInGame(p)){
            p.sendMessage("You are in game...");
            return true;
        }

        Queue.add(p);


        BukkitTask task = new BukkitRunnable() {

            int wait = 10;

            @Override
            public void run() {

                Player enemy = null;


                if (!Queue.isInQueue(p)){
                    Utils.clearActionBar(p);
                    this.cancel();
                }

                if(wait == 10){
                    enemy = Queue.findEnemy(p);
                    wait = 0;
                }

                if(enemy != null){
                    p.sendMessage("enemy found " + enemy.getName());
                    new Game(p, enemy);
                }

                Utils.sendActionBar(p,"Players in queue: " + Queue.getPlayersInQueue());


                wait++;
            }
        }.runTaskTimer(Pvparenas.getInstance(), 0, 20 * 2);


        return false;
    }
}
