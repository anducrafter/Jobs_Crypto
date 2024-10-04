package ch.andu.job.lisstener.jobs;

import ch.andu.job.jobevent.MaurerEvent;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


public class BuilderLisstener implements Listener{

    private  HikariDataSource hikari;
    public BuilderLisstener(HikariDataSource hikari){
        this.hikari = hikari;
    }

    @EventHandler
    public void blockBreak(BlockPlaceEvent e){
       Player p = e.getPlayer();
       new MaurerEvent(hikari).inect(p);
    }








}
