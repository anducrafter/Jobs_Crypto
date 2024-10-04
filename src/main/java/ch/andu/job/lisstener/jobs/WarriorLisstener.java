package ch.andu.job.lisstener.jobs;

import ch.andu.job.Job;
import ch.andu.job.jobevent.JägerEvent;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class WarriorLisstener implements Listener {


    private HikariDataSource hikari;

    public WarriorLisstener(HikariDataSource hikari){
        this.hikari = hikari;
    }
    @EventHandler
    public void onDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof Player){
            return;
        }

        if(e.getEntity().getKiller() == null) {
            return;
        }

        Player p = e.getEntity().getKiller();
        if(!Job.getInstance().worlds.contains(p.getWorld().getName()))return;
        String playername = p.getName();
/*
        JobSql jobSql = new JobSql(hikari);

        JobManager.setPlayerjob(playername,"Jäger");
        JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(p.getUniqueId().toString(),"warrior"));
        JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(p.getUniqueId().toString(),"warrior"));
*/
        if(!JobManager.getPlayerjob(playername).equalsIgnoreCase("Jäger")){
            JobSql jobSql = new JobSql(hikari);
            if(JobManager.getPlayerjobxp(playername) != null || JobManager.getPlayerjoblevel(playername) != null){
                jobSql.setPlayerJobXp(playername,"miner",JobManager.getPlayerjobxp(playername));
                jobSql.setPlayerJobLevel(playername,"miner",JobManager.getPlayerjoblevel(playername));
            }
            //saved in database
            JobManager.setPlayerjob(playername,"Jäger");
            JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(p.getUniqueId().toString(),"warrior"));
            JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(p.getUniqueId().toString(),"warrior"));
        }
        new JägerEvent(hikari).inect(p);
    }
}
