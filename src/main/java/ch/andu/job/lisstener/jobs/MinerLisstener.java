package ch.andu.job.lisstener.jobs;

import ch.andu.job.Job;
import ch.andu.job.jobevent.Mienenarbeiter;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinerLisstener implements Listener {
    private ArrayList<Material> materials = new ArrayList<>();
    private ArrayList<Material> materialsun = new ArrayList<>();

    private HikariDataSource hikari;

    public MinerLisstener(HikariDataSource hikari){
        this.hikari = hikari;
        addMaterialsnormal();

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        if(!Job.getInstance().worlds.contains(e.getPlayer().getWorld().getName()))return;
        if(materials.contains(e.getBlock().getType())){
            String playername = e.getPlayer().getName();
            if(!JobManager.getPlayerjob(playername).equalsIgnoreCase("Mienenarbeiter")){
                JobSql jobSql = new JobSql(hikari);
                if(JobManager.getPlayerjobxp(playername) != null || JobManager.getPlayerjoblevel(playername) != null){
                    jobSql.setPlayerJobXp(playername,"miner",JobManager.getPlayerjobxp(playername));
                    jobSql.setPlayerJobLevel(playername,"miner",JobManager.getPlayerjoblevel(playername));
                }

                //saved in database
                JobManager.setPlayerjob(playername,"Mienenarbeiter");
                JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(e.getPlayer().getUniqueId().toString(),"miner"));
                JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(e.getPlayer().getUniqueId().toString(),"miner"));
            }
            new Mienenarbeiter(hikari).inect(e.getPlayer());
        }else{
            if(materialsun.contains(e.getBlock().getType())){
                String playername = e.getPlayer().getName();
                if(!JobManager.getPlayerjob(playername).equalsIgnoreCase("Mienenarbeiter")){
                    JobSql jobSql = new JobSql(hikari);
                    if(JobManager.getPlayerjobxp(playername) != null || JobManager.getPlayerjoblevel(playername) != null){
                        jobSql.setPlayerJobXp(playername,"miner",JobManager.getPlayerjobxp(playername));
                        jobSql.setPlayerJobLevel(playername,"miner",JobManager.getPlayerjoblevel(playername));
                    }

                    //saved in database
                    JobManager.setPlayerjob(playername,"Mienenarbeiter");
                    JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(e.getPlayer().getUniqueId().toString(),"miner"));
                    JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(e.getPlayer().getUniqueId().toString(),"miner"));
                }
                new Mienenarbeiter(hikari).inectrare(e.getPlayer());
            }
        }
    }

    public void getConfig(){
        Job jobs = Job.getInstance();
        String string = jobs.cfgblock.getString("miner").replace("[","").replace("]","").replace(" ","");
        String[] array = string.split(",");
        List<String> list = Arrays.stream(array).toList();
        ArrayList<Material> im = new ArrayList<>();
        list.forEach(itemstack ->{
            im.add(Material.getMaterial(itemstack));
        });
        materials = im;
    }

    private void addMaterialsnormal(){
        materials.add(Material.COAL_ORE);
        materials.add(Material.COPPER_ORE);
        materials.add(Material.LAPIS_ORE);
        materials.add(Material.IRON_ORE);
        materials.add(Material.REDSTONE_ORE);

        materials.add(Material.DEEPSLATE_COPPER_ORE);
        materials.add(Material.DEEPSLATE_LAPIS_ORE);
        materials.add(Material.DEEPSLATE_IRON_ORE);
        materials.add(Material.DEEPSLATE_REDSTONE_ORE);
        materials.add(Material.DIAMOND_ORE);
        materials.add(Material.EMERALD_ORE);
        materials.add(Material.GOLD_ORE);

        materials.add(Material.DEEPSLATE_COAL_ORE);
        materials.add(Material.DEEPSLATE_DIAMOND_ORE);
        materials.add(Material.DEEPSLATE_EMERALD_ORE);
        materials.add(Material.DEEPSLATE_GOLD_ORE);

        materialsun.add(Material.STONE);
        materialsun.add(Material.COBBLESTONE);


    }

    private void addMaterialsrare(){

    }


}
