package me.color.pvparenas.arenas;

import me.color.pvparenas.Pvparenas;
import me.color.pvparenas.utils.ConfigFiles;
import me.color.pvparenas.utils.Utils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Arena {

    private String name;
    private Location firstPlayer;
    private Location secondPlayer;

    public Arena(String name, Location firstPlayer, Location secondPlayer) {
        this.name = name;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        ConfigFiles cf = new ConfigFiles(name);
        cf.add(name + ".firstPlayer", firstPlayer);
        cf.add(name + ".secondPlayer", secondPlayer);
    }

    public String getName() {
        return name;
    }

    public Location getFirstPlayer() {
        return firstPlayer;
    }

    public Location getSecondPlayer() {
        return secondPlayer;
    }

    private static File[] listofFiles = new File(Pvparenas.getInstance().getDataFolder().getPath() + "\\arenas\\").listFiles();
    public static Arena getRandomArena(){

        if(listofFiles.length > 0){

            Random rand = new Random();
            File file = listofFiles[rand.nextInt(listofFiles.length)];
            FileConfiguration arena = YamlConfiguration.loadConfiguration(file);

            String name = file.getName().substring(0, file.getName().length() - 4);
            Location firstPlayerLoc = arena.getLocation(name + ".firstPlayer");
            Location secondPlayerLoc = arena.getLocation(name + ".secondPlayer");
            firstPlayerLoc.setYaw(Utils.roundPlayerYaw(firstPlayerLoc.getYaw()));
            secondPlayerLoc.setYaw(Utils.roundPlayerYaw(secondPlayerLoc.getYaw()));

            return new Arena(name, firstPlayerLoc, secondPlayerLoc);
        }

        return null;
    }



    public static ArrayList<String> getArenas(){

        if (listofFiles.length == 0)
            return null;

        ArrayList<String> arenas = new ArrayList<>();

        for (File f : listofFiles){
            String str = f.getName();
            arenas.add(str.substring(0, str.length() - 4));
        }

        return arenas;
    }
}
