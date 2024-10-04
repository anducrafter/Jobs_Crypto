package ch.andu.job.jobevent;

import ch.andu.job.Job;
import ch.andu.job.manager.BossBarManager;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.earth2me.essentials.Essentials;
import com.zaxxer.hikari.HikariDataSource;
import net.ess3.api.MaxMoneyException;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BauerEvent {
    int taskid;

    private Essentials essentials;
    private HikariDataSource hikari;
    public BauerEvent(HikariDataSource hikari){
        this.hikari = hikari;
        essentials = Job.getInstance().essentials;
    }
    public void inect(Player player){



        String playername = player.getName();
        if(!JobManager.getPlayerjob(playername).equalsIgnoreCase("Bauer")){
            return;
        }
        int needXp = (JobManager.getPlayerjoblevel(playername)*10)+10;
        int xp = JobManager.getPlayerjobxp(playername);
        int level = JobManager.getPlayerjoblevel(playername);

        if(xp>=needXp){
            JobManager.setPlayerjobxp(playername, 1);
            JobManager.setPlayerjoblevel(playername,level+1);


            try {

                essentials.getUser(player.getUniqueId()).giveMoney(BigDecimal.valueOf(50));
            } catch (MaxMoneyException e) {
                e.printStackTrace();
            }

            //Level UP
            player.playSound(player, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,2,1);
            player.sendMessage(" ");
            player.sendMessage(Job.getInstance().cfgconfig.getString("levelup").replace("&","§").replace("%level%",JobManager.getPlayerjoblevel(playername)+""));
            player.sendMessage(" ");
            player.giveExp(10*JobManager.getPlayerjoblevel(playername));



            new BossBarManager().setProgress(0.0,player);
            new BossBarManager().setBarTitle("§b§lBauer §blevel §e"+JobManager.getPlayerjoblevel(playername),player);
            JobSql jobSql = new JobSql(hikari);
            jobSql.setPlayerJobXp(player.getUniqueId().toString(),"farmer",JobManager.getPlayerjobxp(playername));
            jobSql.setPlayerJobLevel(player.getUniqueId().toString(),"farmer",JobManager.getPlayerjoblevel(playername));
            return;

        }
        Bukkit.getScheduler().cancelTask(taskid);
        double zahl1 = xp;
        double zahl2 = needXp;
        double progress = zahl1/zahl2;
        new BossBarManager().setProgress(progress,player);
        new BossBarManager().setBarTitle("§b§lBauer §blevel §e"+JobManager.getPlayerjoblevel(playername),player);
        new BossBarManager().addtoBar(player);

        //hier bekommst der Spieler seine XP xD
        double getmoney    = 0.09;
        int getxp =  1;

        JobManager.setPlayerjobxp(playername,xp+getxp);
        double money = 0.09*Math.pow(JobManager.getPlayerjoblevel(playername).doubleValue(),1.1)+0.1;


        try {

            essentials.getUser(player.getUniqueId()).giveMoney(BigDecimal.valueOf(Job.getInstance().round2(money)));
        } catch (MaxMoneyException e) {
            e.printStackTrace();
        }

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("§aDu hast "+Job.getInstance().round2(money)+" §agutgeschrieben bekommen §eXP §7"+xp+"§7/§7"+needXp));
        JobSql jobSql = new JobSql(hikari);
        jobSql.setPlayerJobXp(player.getUniqueId().toString(),"farmer",JobManager.getPlayerjobxp(playername));
        Job.getInstance().countdown.put(player,10);


        /*
        taskid = Bukkit.getScheduler().scheduleSyncDelayedTask(Job.getInstance(), new Runnable() {
            @Override
            public void run() {
                new BossBarManager().remouvetoBar(player);
            }
        },20*15);


         */


    }

}
