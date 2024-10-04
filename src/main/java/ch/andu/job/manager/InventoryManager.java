package ch.andu.job.manager;

import ch.andu.job.Job;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InventoryManager {



    private int getXP(String table,Player player){
        JobSql jobSql = new JobSql(Job.getInstance().hikari);
        return jobSql.getPlayerJobXp(player.getUniqueId().toString(),""+table);
    }

    private int getLevel(String table,Player player){
        JobSql jobSql = new JobSql(Job.getInstance().hikari);
        return jobSql.getPlayerJobLevel(player.getUniqueId().toString(),""+table);
    }

    private ArrayList<String> addList(String table,Player player){
        ArrayList<String> liste = new ArrayList<>();
        int xp = 0;
        int level = 0;
        level = getLevel(table,player);
        xp = getXP(table,player);
        liste.add("§eLevel §7"+level);
        liste.add("§eXP §7"+xp+"/"+((level*10)+10));
        return liste;
    }

    public Inventory shopInventory(Player player){

        int xp = 0;
        int level = 0;
        Inventory inv = Bukkit.createInventory(null, 9*6, "§b§lJobs");
        ItemStack farmer= new ItemStack(Material.DIAMOND_HOE,1);
        ItemMeta farmermeta = farmer.getItemMeta();
        farmermeta.setDisplayName("§eBauer");

        farmermeta.setLore(addList("farmer",player));
        farmer.setItemMeta(farmermeta);

        ItemStack Maurer= new ItemStack(Material.BRICKS,1);
        ItemMeta maurermeta = Maurer.getItemMeta();
        maurermeta.setDisplayName("§eMaurer");
        Maurer.setItemMeta(maurermeta);

        ItemStack Holzfäller= new ItemStack(Material.DIAMOND_AXE,1);
        ItemMeta Holzfällermeta = Holzfäller.getItemMeta();
        Holzfällermeta.setDisplayName("§eHolzfäller");
        Holzfällermeta.setLore(addList("woodcuter",player));
        Holzfäller.setItemMeta(Holzfällermeta);

        ItemStack Jäger= new ItemStack(Material.BOW,1);
        ItemMeta Jägermeta = Jäger.getItemMeta();
        Jägermeta.setDisplayName("§eJäger");
        Jägermeta.setLore(addList("warrior",player));
        Jäger.setItemMeta(Jägermeta);

        ItemStack Miner= new ItemStack(Material.DIAMOND_PICKAXE,1);
        ItemMeta Minermeta = Miner.getItemMeta();
        Minermeta.setDisplayName("§eMienenarbeiter");
        Minermeta.setLore(addList("miner",player));
        Miner.setItemMeta(Minermeta);

        ItemStack entdecker= new ItemStack(Material.COMPASS,1);
        ItemMeta entdeckermeta = entdecker.getItemMeta();
        entdeckermeta.setDisplayName("§eEntdecker");
        entdecker.setItemMeta(entdeckermeta);

        ItemStack Fischer= new ItemStack(Material.FISHING_ROD,1);
        ItemMeta Fischermeta = Fischer.getItemMeta();
        Fischermeta.setDisplayName("§eFischer");
        Fischer.setItemMeta(Fischermeta);

        ItemStack zurück= new ItemStack(Material.RED_DYE,1);
        ItemMeta zurückmeta = farmer.getItemMeta();
        zurückmeta.setDisplayName("§cJob verlassen");
        zurück.setItemMeta(zurückmeta);

     /*   inv.setItem(2,Holzfäller);
        inv.setItem(6,Maurer);
        inv.setItem(4,farmer);
        inv.setItem(9,Miner);
        inv.setItem(17,Jäger);
        inv.setItem(20,entdecker);
        inv.setItem(24,Fischer);
        inv.setItem(22,zurück);

        */

        ItemStack i = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName("");
        i.setItemMeta(im);
        for(int it = 0; it<9; it++){
            inv.setItem(it,i);
        }
        for(int it = 45; it<54; it++){
            inv.setItem(it,i);
        }

        inv.setItem(9,Miner);
        inv.setItem(18,Holzfäller);
        inv.setItem(27,farmer);
        inv.setItem(36,Jäger);

        JobSql sql = new JobSql(Job.getInstance().hikari);
        ItemStack is = new ItemStack(Material.PAPER);
        ItemMeta ims = is.getItemMeta();
        ims.setLore(sql.getMaxJob("miner"));
        is.setItemMeta(ims);
        inv.setItem(17,is);
        ims.setLore(sql.getMaxJob("woodcuter"));
        is.setItemMeta(ims);
        inv.setItem(26,is);
        ims.setLore(sql.getMaxJob("farmer"));
        is.setItemMeta(ims);
        inv.setItem(35,is);
        ims.setLore(sql.getMaxJob("warrior"));
        is.setItemMeta(ims);
        inv.setItem(44,is);

        //Set Job ranking

        return inv;
    }

    private List<String> getlist(String job){
        Job jobs = Job.getInstance();
        String string = jobs.cfgblock.getString(job).replace("[","").replace("]","").replace(" ","");
        String[] array = string.split(",");
         List<String>  list = Arrays.stream(array).toList();
    return list;
    }
    public Inventory minerGUI(){

        Inventory inv = Bukkit.createInventory(null,9*6, "§b§lMienenarbeiter");

        List<Material>  materials = new ArrayList<>();
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

        materials.add(Material.STONE);
        materials.add(Material.COBBLESTONE);
        for(int i = 0; i<materials.size(); i++){
            if(materials.get(i) != null){
                inv.setItem(i,new ItemStack(materials.get(i)));
            }
        }
        return inv;
    }

    public Inventory farmerGUI(){
        Inventory inv = Bukkit.createInventory(null,9*6, "§b§lBauer");
        List<Material>  materials = new ArrayList<>();
        materials.add(Material.CARROT);
        materials.add(Material.POTATO);
        materials.add(Material.WHEAT);
        materials.add(Material.CARROT);
        materials.add(Material.MELON);
        materials.add(Material.PUMPKIN);
        for(int i = 0; i<materials.size(); i++){
            if(materials.get(i) != null){

                inv.setItem(i,new ItemStack(materials.get(i)));
            }
        }
        return inv;
    }

    public Inventory woodGUI(){

        Inventory inv = Bukkit.createInventory(null,9*6, "§b§lHolzfäller");
        List<Material>  materials = new ArrayList<>();
        materials.add(Material.ACACIA_LOG);
        materials.add(Material.BIRCH_LOG);
        materials.add(Material.DARK_OAK_LOG);
        materials.add(Material.JUNGLE_LOG);
        materials.add(Material.OAK_LOG);
        materials.add(Material.SPRUCE_LOG);
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
        for(int i = 0; i<materials.size(); i++){
            if(materials.get(i) != null){
                inv.setItem(i,new ItemStack(materials.get(i)));
            }
        }
        return inv;
    }
    //Die allte GUI
    /*


    public Inventory farmerGUI(){
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, "§b§lBauer");

        ItemStack farmer= new ItemStack(Material.BEETROOT,1);
        ItemMeta farmermeta = farmer.getItemMeta();
        farmermeta.setDisplayName("§eBauer");
        farmer.setItemMeta(farmermeta);

        ItemStack Annehmen= new ItemStack(Material.LIME_DYE,1);
        ItemMeta Annehmenmeta = farmer.getItemMeta();
        Annehmenmeta.setDisplayName("§eJob Annehmen");
        Annehmen.setItemMeta(Annehmenmeta);

        ItemStack zurück= new ItemStack(Material.RED_DYE,1);
        ItemMeta zurückmeta = farmer.getItemMeta();
        zurückmeta.setDisplayName("§cZurück zur Übersicht");
        zurück.setItemMeta(zurückmeta);

        ItemAPI auftrag = new ItemAPI(Material.FLOWER_BANNER_PATTERN,1);
        auftrag.display("§eAuftrag");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§fIn diesem Job erhältst du für das Abbauen von");
        lore.add("§fGemüse und Getreide Geld.");
        lore.add("§fDesto höher das Level deines Berufes,");
        lore.add("§nfje mehr wirst du verdiene.");
        auftrag.setLore(lore);



        //Items Setzen
        inv.setItem(4,farmer);
        inv.setItem(10,auftrag.Build());
        inv.setItem(16,Annehmen);
        inv.setItem(22,zurück);



        return inv;
    }

    public Inventory woodcuterGUI(){
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, "§b§lHolzfäller");

        ItemStack farmer= new ItemStack(Material.OAK_LOG,1);
        ItemMeta farmermeta = farmer.getItemMeta();
        farmermeta.setDisplayName("§eHolzfäller");
        farmer.setItemMeta(farmermeta);

        ItemStack Annehmen= new ItemStack(Material.LIME_DYE,1);
        ItemMeta Annehmenmeta = farmer.getItemMeta();
        Annehmenmeta.setDisplayName("§eJob Annehmen");
        Annehmen.setItemMeta(Annehmenmeta);

        ItemStack zurück= new ItemStack(Material.RED_DYE,1);
        ItemMeta zurückmeta = farmer.getItemMeta();
        zurückmeta.setDisplayName("§cZurück zur Übersicht");
        zurück.setItemMeta(zurückmeta);

        ItemAPI auftrag = new ItemAPI(Material.FLOWER_BANNER_PATTERN,1);
        auftrag.display("§eAuftrag");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§fIn diesem Job erhältst du für das Abbauen von");
        lore.add("§fHolzstämme Geld.");
        lore.add("§fDesto höher das Level deines Berufes,");
        lore.add("§fje mehr wirst du verdienen.");
        auftrag.setLore(lore);

        //Items Setzen
        inv.setItem(4,farmer);
        inv.setItem(10,auftrag.Build());
        inv.setItem(16,Annehmen);
        inv.setItem(22,zurück);



        return inv;
    }

    public Inventory minerGUI(){
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, "§b§lMienenarbeiter");

        ItemStack farmer= new ItemStack(Material.NETHERITE_PICKAXE,1);
        ItemMeta farmermeta = farmer.getItemMeta();
        farmermeta.setDisplayName("§eMienenarbeiter");
        farmer.setItemMeta(farmermeta);

        ItemStack Annehmen= new ItemStack(Material.LIME_DYE,1);
        ItemMeta Annehmenmeta = farmer.getItemMeta();
        Annehmenmeta.setDisplayName("§eJob Annehmen");
        Annehmen.setItemMeta(Annehmenmeta);

        ItemStack zurück= new ItemStack(Material.RED_DYE,1);
        ItemMeta zurückmeta = farmer.getItemMeta();
        zurückmeta.setDisplayName("§cZurück zur Übersicht");
        zurück.setItemMeta(zurückmeta);

        ItemAPI auftrag = new ItemAPI(Material.FLOWER_BANNER_PATTERN,1);
        auftrag.display("§eAuftrag");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§fIn diesem Job erhältst du für das Abbauen von");
        lore.add("§fErzen Geld. Bei seltene Erzen bekommst du das Zwei Fache.");
        lore.add("§fDesto höher das Level deines Berufes,");
        lore.add("§fje mehr wirst du verdienen.");
        auftrag.setLore(lore);

        //Items Setzen
        inv.setItem(4,farmer);
        inv.setItem(10,auftrag.Build());
        inv.setItem(16,Annehmen);
        inv.setItem(22,zurück);



        return inv;
    }

    public Inventory warriorGUI(){
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, "§b§lJäger");

        ItemStack farmer= new ItemStack(Material.NETHERITE_SWORD,1);
        ItemMeta farmermeta = farmer.getItemMeta();
        farmermeta.setDisplayName("§eJäger");
        farmer.setItemMeta(farmermeta);

        ItemStack Annehmen= new ItemStack(Material.LIME_DYE,1);
        ItemMeta Annehmenmeta = farmer.getItemMeta();
        Annehmenmeta.setDisplayName("§eJob Annehmen");
        Annehmen.setItemMeta(Annehmenmeta);

        ItemStack zurück= new ItemStack(Material.RED_DYE,1);
        ItemMeta zurückmeta = farmer.getItemMeta();
        zurückmeta.setDisplayName("§cZurück zur Übersicht");
        zurück.setItemMeta(zurückmeta);

        ItemAPI auftrag = new ItemAPI(Material.FLOWER_BANNER_PATTERN,1);
        auftrag.display("§eAuftrag");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§fIn diesem Job erhältst du für das Killen von Tiere Geld");
        lore.add("§fDesto höher das Level deines Berufes,");
        lore.add("§fje mehr wirst du verdienen.");
        auftrag.setLore(lore);

        //Items Setzen
        inv.setItem(4,farmer);
        inv.setItem(10,auftrag.Build());
        inv.setItem(16,Annehmen);
        inv.setItem(22,zurück);



        return inv;
    }

    public Inventory builderGUI(){
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, "§b§lMaurer");

        ItemStack farmer= new ItemStack(Material.BRICKS,1);
        ItemMeta farmermeta = farmer.getItemMeta();
        farmermeta.setDisplayName("§eMaurer");
        farmer.setItemMeta(farmermeta);

        ItemStack Annehmen= new ItemStack(Material.LIME_DYE,1);
        ItemMeta Annehmenmeta = farmer.getItemMeta();
        Annehmenmeta.setDisplayName("§eJob Annehmen");
        Annehmen.setItemMeta(Annehmenmeta);

        ItemStack zurück= new ItemStack(Material.RED_DYE,1);
        ItemMeta zurückmeta = farmer.getItemMeta();
        zurückmeta.setDisplayName("§cZurück zur Übersicht");
        zurück.setItemMeta(zurückmeta);
        ItemAPI auftrag = new ItemAPI(Material.FLOWER_BANNER_PATTERN,1);
        auftrag.display("§eAuftrag");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§fIn diesem Job erhältst du für das Platzieren ");
        lore.add("§fvon jeweils 10 Blöcken Geld");
        lore.add("§fDesto höher das Level deines Berufes,");
        lore.add("§fje mehr wirst du verdienen.");
        auftrag.setLore(lore);

        //Items Setzen
        inv.setItem(4,farmer);
        inv.setItem(10,auftrag.Build());
        inv.setItem(16,Annehmen);
        inv.setItem(22,zurück);



        return inv;
    }

    public Inventory entdekcerGUI(){
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, "§b§lEntdecker");

        ItemStack farmer= new ItemStack(Material.COMPASS,1);
        ItemMeta farmermeta = farmer.getItemMeta();
        farmermeta.setDisplayName("§eEntdecker");
        farmer.setItemMeta(farmermeta);

        ItemStack Annehmen= new ItemStack(Material.LIME_DYE,1);
        ItemMeta Annehmenmeta = farmer.getItemMeta();
        Annehmenmeta.setDisplayName("§eJob Annehmen");
        Annehmen.setItemMeta(Annehmenmeta);

        ItemStack zurück= new ItemStack(Material.RED_DYE,1);
        ItemMeta zurückmeta = farmer.getItemMeta();
        zurückmeta.setDisplayName("§cZurück zur Übersicht");
        zurück.setItemMeta(zurückmeta);

        ItemAPI auftrag = new ItemAPI(Material.FLOWER_BANNER_PATTERN,1);
        auftrag.display("§eAuftrag");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§fIn diesem Job erhältst du für entdecken ");
        lore.add("§fvon neuen Gebieten Geld");
        lore.add("§cWichtig, dieser Job kannst du nur in der Farmwelt benutzen");
        lore.add("§fDesto höher das Level deines Berufes,");
        lore.add("§fje mehr wirst du verdienen.");
        auftrag.setLore(lore);

        //Items Setzen
        inv.setItem(4,farmer);
        inv.setItem(10,auftrag.Build());
        inv.setItem(16,Annehmen);
        inv.setItem(22,zurück);



        return inv;
    }

    public Inventory fischerGUI(){
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, "§b§lFischer");

        ItemStack farmer= new ItemStack(Material.FISHING_ROD,1);
        ItemMeta farmermeta = farmer.getItemMeta();
        farmermeta.setDisplayName("§eEntdecker");
        farmer.setItemMeta(farmermeta);

        ItemStack Annehmen= new ItemStack(Material.LIME_DYE,1);
        ItemMeta Annehmenmeta = farmer.getItemMeta();
        Annehmenmeta.setDisplayName("§eJob Annehmen");
        Annehmen.setItemMeta(Annehmenmeta);

        ItemStack zurück= new ItemStack(Material.RED_DYE,1);
        ItemMeta zurückmeta = farmer.getItemMeta();
        zurückmeta.setDisplayName("§cZurück zur Übersicht");
        zurück.setItemMeta(zurückmeta);

        ItemAPI auftrag = new ItemAPI(Material.FLOWER_BANNER_PATTERN,1);
        auftrag.display("§eAuftrag");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§fIn diesem Job erhältst du für fischen Geld");
        lore.add("§fDesto höher das Level deines Berufes,");
        lore.add("§fje mehr wirst du verdienen.");
        auftrag.setLore(lore);

        //Items Setzen
        inv.setItem(4,farmer);
        inv.setItem(10,auftrag.Build());
        inv.setItem(16,Annehmen);
        inv.setItem(22,zurück);



        return inv;
    }
    */

}
