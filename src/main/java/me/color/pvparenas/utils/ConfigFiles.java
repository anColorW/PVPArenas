package me.color.pvparenas.utils;

import me.color.pvparenas.Pvparenas;
import me.color.pvparenas.arenas.Arena;
import me.color.pvparenas.commands.ArenaCreateCommand;
import me.color.pvparenas.commands.LeaveCommand;
import me.color.pvparenas.commands.QueueCommand;
import me.color.pvparenas.events.ArenaCreateEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFiles {


    File newConfig;
    FileConfiguration newConfigz;

    public ConfigFiles(String name) {
        newConfig = new File(Pvparenas.getInstance().getDataFolder(), "\\arenas\\" + name + ".yml");
        newConfigz = YamlConfiguration.loadConfiguration(newConfig);
        saveNewConfig();
    }

    public void add(String path, Object value){
        newConfigz.set(path, value);
        saveNewConfig();
    }



    private void saveNewConfig(){
        try{
            newConfigz.save(newConfig);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
