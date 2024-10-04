package ch.andu.job.lisstener.inventoryclick;

import ch.andu.job.Job;
import ch.andu.job.manager.InventoryManager;
import ch.andu.job.manager.JobManager;
import ch.andu.job.manager.JobSql;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class FischerGuiLisstenr implements Listener {

    private HikariDataSource hikari;
    public FischerGuiLisstenr(HikariDataSource hikari){
        this.hikari = hikari;
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase("§b§lFischer")){
            e.setCancelled(true);

            if(e.getCurrentItem()!=null){
                ItemStack i = e.getCurrentItem();
                if(i.getType() == Material.RED_DYE){
                    p.openInventory(new InventoryManager().shopInventory(p));
                }
                if(i.getType() == Material.LIME_DYE){
                    JobSql jobSql = new JobSql(hikari);
                    jobSql.createPlayerJobs(p.getUniqueId().toString(),"fischer");
                    jobSql.setPlayerJob(p.getUniqueId().toString(),"Fischer");

                    String playername = p.getName();
                    JobManager.setPlayerjob(playername,"Fischer");
                    JobManager.setPlayerjoblevel(playername,jobSql.getPlayerJobLevel(p.getUniqueId().toString(),"fischer"));
                    JobManager.setPlayerjobxp(playername,jobSql.getPlayerJobXp(p.getUniqueId().toString(),"fischer"));
                    p.closeInventory();
                    p.sendMessage(Job.getInstance().prefix+" §7Du hast dein Job zu §d"+JobManager.getPlayerjob(playername)+" §7geändert");

                }

            }
        }


    }

}
