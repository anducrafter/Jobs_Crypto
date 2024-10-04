package ch.andu.job.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class BossBarManager {
    public static HashMap<Player,BossBar> bossBarHashMap = new HashMap<>();

    /*double com = (xp/needXp)*10;

     */
    public void addtoBar(Player player){
        BossBar bar = bossBarHashMap.get(player);
        if(bar.getPlayers().contains(player)){
            return;
        }
        bar.addPlayer(player);
    }

    public void remouvetoBar(Player player){
        BossBar bar = bossBarHashMap.get(player);
        if(bar.getPlayers().contains(player)){
        bar.removePlayer(player);
        }
    }

    public void updateBar(Player player){
        BossBar bar = bossBarHashMap.get(player);
        if(bar.getPlayers().contains(player)){
           bar.removePlayer(player);
        }
        bar.addPlayer(player);
    }

    public void updateBar(Player player,String title){
        BossBar bar = bossBarHashMap.get(player);
        bar.setTitle(title);
        if(bar.getPlayers().contains(player)){
            bar.removePlayer(player);
        }
        bar.addPlayer(player);
    }

    public void setBarTitle(String title,Player player) {
        BossBar bar = bossBarHashMap.get(player);
        bar.setTitle(title);
    }

    public void setProgress(double progress,Player player){
        BossBar bar = bossBarHashMap.get(player);
        bar.setProgress(progress);
    }








    private String format(String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }

}
