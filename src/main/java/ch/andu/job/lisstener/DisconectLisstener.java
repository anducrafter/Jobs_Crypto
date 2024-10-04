package ch.andu.job.lisstener;

import ch.andu.job.Job;
import ch.andu.job.manager.BossBarManager;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DisconectLisstener implements Listener {


    private HikariDataSource hikari;

    public DisconectLisstener(HikariDataSource hikari){
        this.hikari = hikari;
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e){
        e.setQuitMessage(null);
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();
        JobSql jobSql = new JobSql(hikari);
        String job = JobManager.getPlayerjob(p.getName());
        if(job.isEmpty()){
            return;
        }
        if(job.equalsIgnoreCase("Bauer")){
            jobSql.setPlayerJobLevel(uuid,"farmer",JobManager.getPlayerjoblevel(p.getName()));
            jobSql.setPlayerJobXp(uuid,"farmer",JobManager.getPlayerjobxp(p.getName()));
        }else if (job.equalsIgnoreCase("Mienenarbeiter")){
            jobSql.setPlayerJobLevel(uuid,"miner",JobManager.getPlayerjoblevel(p.getName()));
            jobSql.setPlayerJobXp(uuid,"miner",JobManager.getPlayerjobxp(p.getName()));
        }else if (job.equalsIgnoreCase("Holzfäller")){
            jobSql.setPlayerJobLevel(uuid,"woodcuter",JobManager.getPlayerjoblevel(p.getName()));
            jobSql.setPlayerJobXp(uuid,"woodcuter",JobManager.getPlayerjobxp(p.getName()));
        }else if (job.equalsIgnoreCase("Jäger")){
            jobSql.setPlayerJobLevel(uuid,"warrior",JobManager.getPlayerjoblevel(p.getName()));
            jobSql.setPlayerJobXp(uuid,"warrior",JobManager.getPlayerjobxp(p.getName()));
        }else if (job.equalsIgnoreCase("Fischer")){
            jobSql.setPlayerJobLevel(uuid,"fischer",JobManager.getPlayerjoblevel(p.getName()));
            jobSql.setPlayerJobXp(uuid,"fischer",JobManager.getPlayerjobxp(p.getName()));
        }else if (job.equalsIgnoreCase("Maurer")){
            jobSql.setPlayerJobLevel(uuid,"builder",JobManager.getPlayerjoblevel(p.getName()));
            jobSql.setPlayerJobXp(uuid,"builder",JobManager.getPlayerjobxp(p.getName()));
        }else if (job.equalsIgnoreCase("Entdecker")){
            jobSql.setPlayerJobLevel(uuid,"entdecker",JobManager.getPlayerjoblevel(p.getName()));
            jobSql.setPlayerJobXp(uuid,"entdecker",JobManager.getPlayerjobxp(p.getName()));
        }
    }
}
