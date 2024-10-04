package ch.andu.job.lisstener.jobs;

import ch.andu.job.jobevent.FischerEvent;
import ch.andu.job.jobevent.JägerEvent;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.ArrayList;


public class Fischerisstener implements Listener {


    private HikariDataSource hikari;

    private ArrayList<Entity> fisches = new ArrayList<>();
    public Fischerisstener(HikariDataSource hikari){
        this.hikari = hikari;
    }
    @EventHandler
    public void onDeath(PlayerFishEvent e) {
        if(e.getCaught() == null ){
            return;
        }

        if(e.getHook().isInWater()) {
            new FischerEvent(hikari).inect(e.getPlayer());
        }

    }

    private  void addCollection(){

    }
}
