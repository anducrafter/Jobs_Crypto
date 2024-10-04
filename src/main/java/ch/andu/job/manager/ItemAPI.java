package ch.andu.job.manager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemAPI {

    private Material material;
    private Enchantment enchantment;
    private boolean enchsee;
    private int enchantementlevel;
    private String displayname;
    private int amount;
    private  byte id;
    private List<String> lore;
    public  ItemAPI(Material material,int amount){
        this.amount = amount;
        this.material = material;

    }

    public void display(String displayname){
        this.displayname = displayname;
    }

    public void setLore(List<String> lore){
        this.lore = lore;
    }
    public void addEnchantement(Enchantment enchantment){
        this.enchantment = enchantment;
    }

    public void seeEnch(int i){
        this.enchantementlevel = i;
    }

    public void setenchsee(Boolean b){
        this.enchsee = b;
    }

    public  ItemStack Build(){
        ItemStack itemStack = new ItemStack(this.material,this.amount,this.id);
        ItemMeta im= itemStack.getItemMeta();
        im.setLore(lore);
        im.setDisplayName(displayname);
        itemStack.setItemMeta(im);
        return itemStack;


    }





}
