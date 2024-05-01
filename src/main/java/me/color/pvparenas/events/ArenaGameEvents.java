package me.color.pvparenas.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class ArenaGameEvents implements Listener {

    public static ArrayList<Player> freezedPlayers = new ArrayList<>();


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){

        Player player = e.getPlayer();

        if (!freezedPlayers.contains(player))
            return;

        e.setCancelled(true);
    }


}
