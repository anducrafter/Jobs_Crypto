package ch.andu.job.commands;

import ch.andu.job.Job;
import ch.andu.job.manager.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class JobCommand implements CommandExecutor {

    private Job main;
    public JobCommand(Job main){
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                InventoryManager invmanager = new InventoryManager();
                if(args.length == 0) {
                    Inventory inv = invmanager.shopInventory(p);
                    p.openInventory(inv);
                    p.sendMessage(main.cfgconfig.getString("command_message").replace("&","§"));
                }


            }
        return false;
    }
}
