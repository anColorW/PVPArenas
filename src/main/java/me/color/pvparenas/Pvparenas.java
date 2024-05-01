package me.color.pvparenas;

import me.color.pvparenas.arenas.Arena;
import me.color.pvparenas.commands.ArenaCreateCommand;
import me.color.pvparenas.commands.ArenaListCommand;
import me.color.pvparenas.commands.LeaveCommand;
import me.color.pvparenas.commands.QueueCommand;
import me.color.pvparenas.events.ArenaCreateEvent;
import me.color.pvparenas.events.ArenaDeathEvent;
import me.color.pvparenas.events.ArenaGameEvents;
import me.color.pvparenas.events.ArenaLeftServerEvent;
import me.color.pvparenas.utils.ConfigFiles;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


public class Pvparenas extends JavaPlugin {


    static Pvparenas instance;
    public static Pvparenas getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;

        //                                  --COMMANDS--
        Bukkit.getServer().getPluginCommand("queue").setExecutor(new QueueCommand());
        Bukkit.getServer().getPluginCommand("leave").setExecutor(new LeaveCommand());
        Bukkit.getServer().getPluginCommand("create").setExecutor(new ArenaCreateCommand());
        Bukkit.getServer().getPluginCommand("list").setExecutor(new ArenaListCommand());


        //                                   --EVENTS--
        Bukkit.getServer().getPluginManager().registerEvents(new ArenaCreateEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ArenaDeathEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ArenaGameEvents(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ArenaLeftServerEvent(), this);




    }



}
