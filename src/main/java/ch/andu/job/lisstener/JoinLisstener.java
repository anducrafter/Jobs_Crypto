package ch.andu.job.lisstener;

import ch.andu.job.Job;
import ch.andu.job.manager.BossBarManager;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.zaxxer.hikari.HikariDataSource;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.awt.*;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class JoinLisstener implements Listener {

    private final HikariDataSource hikari;
    public JoinLisstener(HikariDataSource hikari){
        this.hikari = hikari;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage("");
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();
        JobSql jobSql = new JobSql(hikari);
        jobSql.createPlayer(uuid);
        String job = jobSql.getPlayerJob(uuid);
        JobManager.setPlayerjob(e.getPlayer().getName(),job);
        Job.getInstance().countdown.put(p,10);
        //BossBar
        BossBar bar = Bukkit.createBossBar("player_job", BarColor.WHITE, BarStyle.SEGMENTED_10);
        new BossBarManager().bossBarHashMap.put(e.getPlayer(),bar);

        if(job.isEmpty()){
        return;
        }
        //Abfragen welcher Job der Spieler hat xD
        if(job.equalsIgnoreCase("Bauer")){
            JobManager.setPlayerjoblevel(p.getName(),jobSql.getPlayerJobLevel(uuid,"farmer"));
            JobManager.setPlayerjobxp(p.getName(),jobSql.getPlayerJobXp(uuid,"farmer"));
        }else if (job.equalsIgnoreCase("Mienenarbeiter")){
            JobManager.setPlayerjoblevel(p.getName(),jobSql.getPlayerJobLevel(uuid,"miner"));
            JobManager.setPlayerjobxp(p.getName(),jobSql.getPlayerJobXp(uuid,"miner"));
        }else if (job.equalsIgnoreCase("Holzfäller")){
            JobManager.setPlayerjoblevel(p.getName(),jobSql.getPlayerJobLevel(uuid,"woodcuter"));
            JobManager.setPlayerjobxp(p.getName(),jobSql.getPlayerJobXp(uuid,"woodcuter"));
        }else if (job.equalsIgnoreCase("Jäger")){
            JobManager.setPlayerjoblevel(p.getName(),jobSql.getPlayerJobLevel(uuid,"warrior"));
            JobManager.setPlayerjobxp(p.getName(),jobSql.getPlayerJobXp(uuid,"warrior"));
        }else if (job.equalsIgnoreCase("Fischer")){
            JobManager.setPlayerjoblevel(p.getName(),jobSql.getPlayerJobLevel(uuid,"fischer"));
            JobManager.setPlayerjobxp(p.getName(),jobSql.getPlayerJobXp(uuid,"fischer"));
        }else if (job.equalsIgnoreCase("Maurer")){
            JobManager.setPlayerjoblevel(p.getName(),jobSql.getPlayerJobLevel(uuid,"builder"));
            JobManager.setPlayerjobxp(p.getName(),jobSql.getPlayerJobXp(uuid,"builder"));
        }else if (job.equalsIgnoreCase("Entdecker")){
            JobManager.setPlayerjoblevel(p.getName(),jobSql.getPlayerJobLevel(uuid,"entdecker"));
            JobManager.setPlayerjobxp(p.getName(),jobSql.getPlayerJobXp(uuid,"entdecker"));
        }


    }


}
