package ch.andu.job.lisstener.jobs;

import ch.andu.job.Job;
import ch.andu.job.jobevent.HolzfällerEvent;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WoodBreakEvent implements Listener {
    private  HikariDataSource hikari;
    private ArrayList<Material> materials = new ArrayList<>();
 private ArrayList<Block> blocks = new ArrayList<>();
    public WoodBreakEvent(HikariDataSource hikari){
        this.hikari = hikari;
        addMateriasl();

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(!Job.getInstance().worlds.contains(e.getPlayer().getWorld().getName()))return;
        if(materials.contains(e.getBlock().getType())){
            String playername = e.getPlayer().getName();
            if(blocks.contains(e.getBlock())) return;
            /*
            HolzfällerEvent holzfällerEvent = new HolzfällerEvent(hikari);
            JobSql jobSql = new JobSql(hikari);
            String playername = e.getPlayer().getName();
            JobManager.setPlayerjob(playername,"Holzfäller");
            JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(e.getPlayer().getUniqueId().toString(),"woodcuter"));
            JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(e.getPlayer().getUniqueId().toString(),"woodcuter"));
*/

            if(!JobManager.getPlayerjob(playername).equalsIgnoreCase("Holzfäller")){
                JobSql jobSql = new JobSql(hikari);
                if(JobManager.getPlayerjobxp(playername) != null || JobManager.getPlayerjoblevel(playername) != null){
                    jobSql.setPlayerJobXp(playername,"miner",JobManager.getPlayerjobxp(playername));
                    jobSql.setPlayerJobLevel(playername,"miner",JobManager.getPlayerjoblevel(playername));
                }
                //saved in database
                JobManager.setPlayerjob(playername,"Holzfäller");
                JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(e.getPlayer().getUniqueId().toString(),"woodcuter"));
                JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(e.getPlayer().getUniqueId().toString(),"woodcuter"));
            }

            HolzfällerEvent holzfällerEvent = new HolzfällerEvent(hikari);
            holzfällerEvent.inect(e.getPlayer());

        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(!Job.getInstance().worlds.contains(e.getPlayer().getWorld().getName()))return;
          addMateriasl();
        Player p = e.getPlayer();
        if(p.getItemInUse() !=null)return;
        if(materials.contains(e.getBlock().getType())){
            blocks.add(e.getBlock());
        }

    }

    private void addMateriasl(){

        materials.add(Material.ACACIA_LOG);
        materials.add(Material.BIRCH_LOG);
        materials.add(Material.DARK_OAK_LOG);
        materials.add(Material.JUNGLE_LOG);
        materials.add(Material.OAK_LOG);
        materials.add(Material.SPRUCE_LOG);
        materials.add(Material.OAK_WOOD);
        materials.add(Material.STRIPPED_BIRCH_LOG);
        materials.add(Material.STRIPPED_ACACIA_LOG);
        materials.add(Material.STRIPPED_BIRCH_LOG);
        materials.add(Material.STRIPPED_DARK_OAK_LOG);
        materials.add(Material.STRIPPED_JUNGLE_LOG);
        materials.add(Material.STRIPPED_OAK_LOG);
        materials.add(Material.STRIPPED_SPRUCE_LOG);
        materials.add(Material.CRIMSON_STEM);
        materials.add(Material.WARPED_STEM);
        materials.add(Material.STRIPPED_CRIMSON_STEM);
        materials.add(Material.STRIPPED_WARPED_STEM);
        materials.add(Material.OAK_WOOD);
        materials.add(Material.ACACIA_WOOD);
        materials.add(Material.DARK_OAK_WOOD);
        materials.add(Material.BIRCH_WOOD);
        materials.add(Material.CHERRY_WOOD);
        materials.add(Material.JUNGLE_WOOD);
        materials.add(Material.MANGROVE_WOOD);
    }
    public void getConfig(){
        Job jobs = Job.getInstance();
        String string = jobs.cfgblock.getString("wood").replace("[","").replace("]","").replace(" ","");
        String[] array = string.split(",");
        List<String> list = Arrays.stream(array).toList();
        ArrayList<Material> im = new ArrayList<>();
        list.forEach(itemstack ->{
            im.add(Material.getMaterial(itemstack));
        });
        materials = im;
    }

}
