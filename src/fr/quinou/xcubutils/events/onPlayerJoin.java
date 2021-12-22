package fr.quinou.xcubutils.events;

import fr.quinou.xcubutils.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.util.UUID;


public class onPlayerJoin implements Listener {



    private Main main;
    public onPlayerJoin(Main main)
    {
        this.main = main;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (!p.hasPlayedBefore()) {
            main.sqlPlayerListener.createPlayer(p);
        } else if(!main.sqlPlayerListener.uuidExist(uuid)){
            main.sqlPlayerListener.createPlayer(p);
            main.sqlPlayerListener.playerFound(uuid);
        }


    }


}
