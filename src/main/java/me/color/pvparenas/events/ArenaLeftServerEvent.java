package me.color.pvparenas.events;

import me.color.pvparenas.arenas.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ArenaLeftServerEvent implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){

        Player player = e.getPlayer();

        if (!Game.isPlayerInGame(player))
            return;

        Game.getPlayerGame(player).endGame(player);
    }
}
