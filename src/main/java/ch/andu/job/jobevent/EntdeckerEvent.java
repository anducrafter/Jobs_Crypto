package ch.andu.job.jobevent;
import ch.andu.job.Job;
import ch.andu.job.manager.BossBarManager;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import com.zaxxer.hikari.HikariDataSource;
import net.ess3.api.MaxMoneyException;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.HashMap;

public class EntdeckerEvent {
    private Essentials essentials;
    private HikariDataSource hikari;
    public static HashMap<Player,Integer> blocks = new HashMap<>();
    public EntdeckerEvent(HikariDataSource hikari){
        this.hikari = hikari;
        essentials = Job.getInstance().essentials;
    }

    public void inect(Player player) {
        String playername = player.getName();
        if (!JobManager.getPlayerjob(playername).equalsIgnoreCase("Entdecker")) {
            return;
        }
      if(!blocks.containsKey(player)){
          blocks.put(player,0);
      }
        int needXp = (JobManager.getPlayerjoblevel(playername) * 12) + 20;
        int xp = JobManager.getPlayerjobxp(playername);
        int level = JobManager.getPlayerjoblevel(playername);

        if (xp >= needXp) {
            JobManager.setPlayerjobxp(playername, 1);
            JobManager.setPlayerjoblevel(playername, level + 1);

            try {

                essentials.getUser(player.getUniqueId()).giveMoney(BigDecimal.valueOf(50));
            } catch (MaxMoneyException e) {
                e.printStackTrace();
            }

            //Level UP
            player.playSound(player, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 2, 1);
            player.sendMessage(" ");
            player.sendMessage(Job.getInstance().prefix + "§c§lLevel UP");
            player.sendMessage("§7Dein neues Level ist nun §b§l" + JobManager.getPlayerjoblevel(playername));
            player.sendMessage("§7Du bekommst nun §b+0.2 §7mehr pro 150 Meter");
            player.sendMessage(" ");
            player.giveExp(10*JobManager.getPlayerjoblevel(playername));


            new BossBarManager().setProgress(0.0, player);
            new BossBarManager().setBarTitle("§b§lEntdecker §blevel §e" + JobManager.getPlayerjoblevel(playername), player);
            JobSql jobSql = new JobSql(hikari);
            jobSql.setPlayerJobLevel(player.getUniqueId().toString(), "entdecker", JobManager.getPlayerjoblevel(playername));
            return;

        }

        double zahl1 = xp;
        double zahl2 = needXp;
        double progress = zahl1 / zahl2;
        new BossBarManager().setProgress(progress, player);
        new BossBarManager().setBarTitle("§b§lEntdecker §blevel §e" + JobManager.getPlayerjoblevel(playername), player);
        new BossBarManager().addtoBar(player);

        //hier bekommst der Spieler seine XP xD
        if(blocks.get(player) % 20 == 0){
            JobManager.setPlayerjobxp(playername, xp + 1);
        }

        if(blocks.get(player) == 100) {
            double money = 0.09*Math.pow(JobManager.getPlayerjoblevel(playername).doubleValue(),1.1)+0.1;

            try {

                essentials.getUser(player.getUniqueId()).giveMoney(BigDecimal.valueOf(Job.getInstance().round2(money)));
            } catch (MaxMoneyException e) {
                e.printStackTrace();
            }
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("§aDu hast "+Job.getInstance().round2(money)+" §agutgeschrieben bekommen §eXP §7"+xp+"§7/§7"+needXp));
            blocks.put(player,0);
        }
        int block = blocks.get(player);
        int amount = block+1;
        blocks.put(player,amount);
        Job.getInstance().countdown.put(player, 10);

    }

}
