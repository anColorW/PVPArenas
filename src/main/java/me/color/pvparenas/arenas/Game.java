package me.color.pvparenas.arenas;

import me.color.pvparenas.Pvparenas;
import me.color.pvparenas.events.ArenaGameEvents;
import me.color.pvparenas.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class Game {

    private final Player playerOne;
    private final Player playerTwo;

    private final Arena arena = Arena.getRandomArena();


    private static final ArrayList<Game> currentGames = new ArrayList<>();

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        startGame();
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    private void startGame(){

        removePlayersFromQueue();
        teleportToArena();
        currentGames.add(this);
        freezePlayers(true);


        BukkitTask task = new BukkitRunnable() {

            int timer = 3;

            @Override
            public void run() {

                if (timer == 0){
                    Utils.clearActionBar(playerOne);
                    Utils.clearActionBar(playerTwo);
                    freezePlayers(false);
                    this.cancel();
                }

                Utils.sendActionBar(playerOne,"Match will start in " + timer + " seconds!");
                Utils.sendActionBar(playerTwo,"Match will start in " + timer + " seconds!");


                timer--;
            }
        }.runTaskTimer(Pvparenas.getInstance(), 0, 20);

    }

    public void endGame(Player loser){

        if (loser == playerOne){
            playerOne.sendMessage("You loss against " + playerTwo.getName() + "[" + playerTwo.getHealth() + "/" + playerTwo.getMaxHealth() + "]");
            playerTwo.sendMessage("You won against " + playerOne.getName());
        } else{
            playerTwo.sendMessage("You loss against " + playerOne.getName() + "[" + playerOne.getHealth() + "/" + playerOne.getMaxHealth() + "]");
            playerOne.sendMessage("You won against " + playerTwo.getName());
        }

        currentGames.remove(this);
        teleportToLobby();
    }


    private void freezePlayers(boolean freezed){

        if (freezed){
            ArenaGameEvents.freezedPlayers.add(playerOne);
            ArenaGameEvents.freezedPlayers.add(playerTwo);
        } else{
            ArenaGameEvents.freezedPlayers.remove(playerOne);
            ArenaGameEvents.freezedPlayers.remove(playerTwo);
        }

    }
    private void removePlayersFromQueue(){
        Queue.remove(playerOne);
        Queue.remove(playerTwo);
    }

    private void teleportToArena(){
        playerOne.teleport(arena.getFirstPlayer());
        playerTwo.teleport(arena.getSecondPlayer());
    }

    private void teleportToLobby(){
        playerOne.teleport(playerOne.getWorld().getSpawnLocation());
        playerTwo.teleport(playerOne.getWorld().getSpawnLocation());
    }

    public static Game getPlayerGame(Player player) {

        if(!currentGames.isEmpty()){
            for (Game game : currentGames){
                if (game.getPlayerOne().equals(player) || game.getPlayerTwo().equals(player))
                    return game;
            }
        }

        return null;
    }

    public static boolean isPlayerInGame(Player player){

        if (!currentGames.isEmpty()){
            for (Game game : currentGames){
                if (game.getPlayerOne().equals(player) || game.getPlayerTwo().equals(player))
                    return true;
            }
        }


        return false;
    }

}
