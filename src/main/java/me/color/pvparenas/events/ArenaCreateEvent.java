package me.color.pvparenas.events;

import me.color.pvparenas.arenas.Arena;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;
import java.util.EventListener;

public class ArenaCreateEvent implements Listener {

    public static String arenaName = "";
    public static String uuid = "";
    public static ArrayList<Location> locations = new ArrayList<>();
    int sneakCounter = 0;

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();



        if (uuid.isEmpty())
            return;

        if (!player.getUniqueId().toString().equals(uuid))
            return;

        if(player.isSneaking())
            return;

        if (sneakCounter == 0) {
            player.sendMessage("Type \"confirm\" on chat to confirm position of first player.");
        } else {
            player.sendMessage("Type \"confirm\" on chat to confirm position of second player.");
        }


    }

    @EventHandler
    public void onPlayerChatEvent(PlayerChatEvent event) {

        Player p = event.getPlayer();

        if (uuid.isEmpty())
            return;

        if(!p.getUniqueId().toString().equals(uuid))
            return;

        if (event.getMessage().equalsIgnoreCase("confirm")){
            sneakCounter++;
            locations.add(p.getLocation());
            p.sendMessage("Succesfully setted coordinations for player.");
        }


        if (sneakCounter > 1){

            new Arena(arenaName, locations.get(0), locations.get(1));
            uuid = "";
            sneakCounter = 0;
            locations.clear();
        }

        event.setCancelled(true);
    }



}
