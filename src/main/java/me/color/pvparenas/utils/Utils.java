package me.color.pvparenas.utils;

import me.color.pvparenas.arenas.Arena;
import me.color.pvparenas.arenas.Queue;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Utils {

    public static void sendActionBar(Player p, String str){
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(str));
    }

    public static void clearActionBar(Player p){
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(""));
    }

    public static String chat (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }


    public static float roundPlayerYaw(float yaw){

        float[] angles = {0, 90, 180, -90};


        float minDifference = Float.MAX_VALUE;
        float closestAngle = angles[0];

        for (float angle : angles) {
            float difference = Math.abs(yaw - angle);
            if (difference < minDifference) {
                minDifference = difference;
                closestAngle = angle;
            }
        }

        return closestAngle;
    }
}
