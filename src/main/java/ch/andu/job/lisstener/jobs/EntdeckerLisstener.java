package ch.andu.job.lisstener.jobs;

import ch.andu.job.jobevent.EntdeckerEvent;
import ch.andu.job.jobevent.MaurerEvent;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class EntdeckerLisstener implements Listener{

    private  HikariDataSource hikari;
    public EntdeckerLisstener(HikariDataSource hikari){
        this.hikari = hikari;
    }

    @EventHandler
    public void blockBreak(PlayerMoveEvent event){
        if ((int) event.getFrom().getX() != (int) event.getTo().getX()  || (int) event.getFrom().getZ() != (int) event.getTo().getZ()) {
            Player p = event.getPlayer();


            new EntdeckerEvent(hikari).inect(p);

        }

    }








}
