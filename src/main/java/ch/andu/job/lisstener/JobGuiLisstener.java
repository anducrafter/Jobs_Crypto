package ch.andu.job.lisstener;

import ch.andu.job.Job;
import ch.andu.job.manager.InventoryManager;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.zaxxer.hikari.HikariDataSource;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class JobGuiLisstener implements Listener {

    private HikariDataSource hikari;
    public JobGuiLisstener(HikariDataSource hikari){
        this.hikari = hikari;
    }
    @EventHandler
    public void onJobsClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        JobSql jobSql = new JobSql(hikari);
        if(e.getView().getTitle().equalsIgnoreCase("§b§lJobs")){
            e.setCancelled(true);
            if(e.getCurrentItem()!=null){
                ItemStack i = e.getCurrentItem();
                InventoryManager invmanager = new InventoryManager();
                checkJob((Player) e.getWhoClicked());
                if(i.getType() == Material.DIAMOND_HOE){
                    p.openInventory(invmanager.farmerGUI());
                }else if(i.getType() == Material.DIAMOND_AXE){
                    p.openInventory(invmanager.woodGUI());
                }else if(i.getType() == Material.DIAMOND_PICKAXE){
                    p.openInventory(invmanager.minerGUI());
                }
                    /*
                    if(i.getType() == Material.NETHERITE_SWORD){
                    p.openInventory(invmanager.warriorGUI());
                }else if(i.getType() == Material.BRICKS){
                    p.openInventory(invmanager.builderGUI());
                }else if(i.getType() == Material.COMPASS){
                    p.openInventory(invmanager.entdekcerGUI());
                }else if(i.getType() == Material.FISHING_ROD){
                    p.openInventory(invmanager.fischerGUI());
                }else if(i.getType() == Material.RED_DYE){
                    p.sendMessage(Job.getInstance().prefix+"§cDu hast deinen Job verlassen.");
                    jobSql.setPlayerJob(p.getUniqueId().toString(),"");
                    JobManager.setPlayerjob(p.getName(),"");
                }
                    */

            }
        }


    }

    private void checkJob(Player p){
        String uuid = p.getUniqueId().toString();
        JobSql jobSql = new JobSql(hikari);
        String job = JobManager.getPlayerjob(p.getName());
        if(job.equalsIgnoreCase("Bauer")){
            jobSql.setPlayerJobLevel(uuid,"farmer", JobManager.getPlayerjoblevel(p.getName()));
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
