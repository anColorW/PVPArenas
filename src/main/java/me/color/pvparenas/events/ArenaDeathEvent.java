package me.color.pvparenas.events;

import me.color.pvparenas.arenas.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class ArenaDeathEvent implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        Player player = e.getEntity().getPlayer();

        if (!Game.isPlayerInGame(player))
            return;

        Game.getPlayerGame(player).endGame(player);
        player.spigot().respawn();

    }
}
