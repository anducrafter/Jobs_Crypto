package ch.andu.job.lisstener.jobs;
import ch.andu.job.Job;
import ch.andu.job.jobevent.BauerEvent;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Material;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BauerLisstener implements Listener{

    private  HikariDataSource hikari;
    private ArrayList<Material> blocks = new ArrayList<>();
    public BauerLisstener(HikariDataSource hikari){
        this.hikari = hikari;
    }



    @EventHandler
    public void blockBreak(BlockBreakEvent e){
        getConfig();
        String playername = e.getPlayer().getName();
        if(blocks.contains(e.getBlock().getType())){

            if(e.getBlock().getData() == (byte )7){
                BauerEvent bauerEvent = new BauerEvent(hikari);
                //Alte version
                /*

                JobSql jobSql = new JobSql(hikari);
                String playername = e.getPlayer().getName();
                JobManager.setPlayerjob(playername,"Bauer");
                JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(e.getPlayer().getUniqueId().toString(),"farmer"));
                JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(e.getPlayer().getUniqueId().toString(),"farmer"));

*/
                if(!JobManager.getPlayerjob(playername).equalsIgnoreCase("Bauer")){
                    JobSql jobSql = new JobSql(hikari);
                    if(JobManager.getPlayerjobxp(playername) != null || JobManager.getPlayerjoblevel(playername) != null){
                        jobSql.setPlayerJobXp(playername,"miner",JobManager.getPlayerjobxp(playername));
                        jobSql.setPlayerJobLevel(playername,"miner",JobManager.getPlayerjoblevel(playername));
                    }
                    //saved in database
                    JobManager.setPlayerjob(playername,"Bauer");
                    JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(e.getPlayer().getUniqueId().toString(),"farmer"));
                    JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(e.getPlayer().getUniqueId().toString(),"farmer"));
                }
                bauerEvent.inect(e.getPlayer());

            }
        }else if(e.getBlock().getType() == Material.BEETROOTS){
            if(e.getBlock().getData() == (byte) 3){
                if(!JobManager.getPlayerjob(playername).equalsIgnoreCase("Bauer")){
                    JobSql jobSql = new JobSql(hikari);
                    if(JobManager.getPlayerjobxp(playername) != null || JobManager.getPlayerjoblevel(playername) != null){
                        jobSql.setPlayerJobXp(playername,"miner",JobManager.getPlayerjobxp(playername));
                        jobSql.setPlayerJobLevel(playername,"miner",JobManager.getPlayerjoblevel(playername));
                    }
                    //saved in database
                    JobManager.setPlayerjob(playername,"Bauer");
                    JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(e.getPlayer().getUniqueId().toString(),"farmer"));
                    JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(e.getPlayer().getUniqueId().toString(),"farmer"));
                }
                BauerEvent bauerEvent = new BauerEvent(hikari);
                bauerEvent.inect(e.getPlayer());

            }
        }
    }

    public void getConfig(){
        Job jobs = Job.getInstance();
      /*  String string = jobs.cfgblock.getString("farmer").replace("[","").replace("]","").replace(" ","");
        String[] array = string.split(",");
        List<String> list = Arrays.stream(array).toList();
        ArrayList<Material> im = new ArrayList<>();
        list.forEach(itemstack ->{
            im.add(Material.getMaterial(itemstack));
        });
       */
        blocks.add(Material.CARROT);
        blocks.add(Material.POTATO);
        blocks.add(Material.WHEAT);
        blocks.add(Material.CARROT);
        blocks.add(Material.MELON);
        blocks.add(Material.PUMPKIN);
    }








}
