package me.color.pvparenas.arenas;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Queue {

    private static final ArrayList<Player> playersInQueue = new ArrayList<>();

    public static void add(Player player) {
        playersInQueue.add(player);
    }
    public static void remove(Player player) {
        playersInQueue.remove(player);
    }


    public static int getPlayersInQueue(){
        return playersInQueue.size();
    }

    public static boolean isInQueue(Player player) { return playersInQueue.contains(player); }

    public static Player findEnemy(Player player){

        if (playersInQueue.size() < 2)
            return null;

        for (Player p : playersInQueue){
            if (p.getUniqueId().equals(player.getUniqueId()))
                continue;

            return p;
        }

        return null;
    }



}
