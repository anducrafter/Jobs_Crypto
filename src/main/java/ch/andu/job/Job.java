package ch.andu.job;
import ch.andu.job.commands.JobCommand;
import ch.andu.job.lisstener.*;
import ch.andu.job.lisstener.inventoryclick.*;
import ch.andu.job.lisstener.jobs.*;
import ch.andu.job.manager.BossBarManager;
import ch.andu.job.manager.JobSql;
import ch.andu.job.placeholderAPI.Placeholder;
import com.earth2me.essentials.Essentials;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;


public final class Job extends JavaPlugin {
    public static Job instance;


    public HikariDataSource hikari;
    private HashMap<Player,Boolean> countboolean = new HashMap<>();
    private File fileconfig = new File("plugins//Jobs//config.yml");
    private File file = new File("plugins//Jobs//mysql.yml");
    public File fileblock = new File("plugins//Jobs//blocks.yml");
    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    public YamlConfiguration cfgconfig = YamlConfiguration.loadConfiguration(fileconfig);
    public YamlConfiguration cfgblock = YamlConfiguration.loadConfiguration(fileblock);

   public Essentials essentials;




    public String prefix;
    public ArrayList<String> worlds;
    public HashMap<Player,Integer> countdown = new HashMap<>();
    int taskid;

    public Double round2(Double val) {
        return new BigDecimal(val.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    @Override
    public void onEnable() {
        instance = this;
        Plugin plugin = getServer().getPluginManager().getPlugin("Essentials");

        if (plugin instanceof Essentials) {
            essentials = (Essentials) plugin;
        } else {
            getLogger().severe("Essentials not found! Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
        }

        //Job HikariConfig add
        addCfg();
        connectToDatabase();
        loadTables();
        loadLisstenr();
        loadCommands();

        prefix = "§e§lJob-System";
        if( Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            JobSql jobSql = new JobSql(hikari);
            new Placeholder(jobSql).register();
            Bukkit.getConsoleSender().sendMessage("§eWICHTIG §7Economysystem ist mit placeholder APi verbunden");

        }
        //Add worlds für plugins
       worlds = (ArrayList<String>) cfgconfig.get("world");
        //Start to rmv BossBar
        remouveBosBar();

    }

    @Override
    public void onDisable() {

    }

    public static Job getInstance() {
        return instance;
    }

    private void addBlocks(){
        try {
            cfgblock.save(fileblock);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(cfgblock.isSet("farmer")){
           return;
        }

        ArrayList<Material> farmer = new ArrayList<>();
        farmer.add(Material.CARROTS);
        cfgblock.set("farmer",farmer.toString());
        ArrayList<Material> miner = new ArrayList<>();
        miner.add(Material.COAL_ORE);
        miner.add(Material.DIAMOND_ORE);
        cfgblock.set("miner",miner.toString());
        ArrayList<Material> wood = new ArrayList<>();
        wood.add(Material.OAK_LOG);
        wood.add(Material.BIRCH_LOG);
        cfgblock.set("wood",wood.toString());

        try {
            cfgblock.save(fileblock);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void addCfg() {
        if(!cfg.isSet("address")){
            cfg.set("address","localhost");
            cfg.set("port",3306);
            cfg.set("database","skysuchttest");
            cfg.set("user","anducrafter");
            cfg.set("password","anducrafter");
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        addBlocks();
        if(!cfgconfig.isSet("warrior_xp")){
            cfgconfig.set("farmer_xp",1);
            cfgconfig.set("farmer_money",0.2);

            cfgconfig.set("warrior_xp",1);
            cfgconfig.set("warrior_money",0.2);

            cfgconfig.set("woodcuter_xp",1);
            cfgconfig.set("woodcuter_money",0.2);

            cfgconfig.set("miner_xp",1);
            cfgconfig.set("miner_money",0.2);

            cfgconfig.set("command_message","&c&lDu hast das Job menü geöffnet");
            cfgconfig.set("levelup","&e&lJobSystem &7Du bist aufgelevelt auf level %level%");
            try {
                cfgconfig.save(fileconfig);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!cfgconfig.isSet("world")){
            //Setze nochmals nacher die Message


        ArrayList<String> worlds = new ArrayList<>();
        worlds.add("world");
            cfgconfig.set("world",worlds);

            try {
                cfgconfig.save(fileconfig);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void connectToDatabase() {
        //Database details
        String address = cfg.getString("address");
        String name = cfg.getString("database");
        String username =cfg.getString("user");
        String password = cfg.getString("password");
        //Initialise hikari instace
        hikari = new HikariDataSource();
        //Setting Hikari properties
        hikari.setMaximumPoolSize(10);
        hikari.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", address);
        hikari.addDataSourceProperty("port", cfg.getString("port"));
        hikari.addDataSourceProperty("databaseName", name);
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);

    }

    private void loadTables(){
        JobSql jobSql = new JobSql(hikari);
        jobSql.loadtables();

    }

    public void loadLisstenr(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinLisstener(hikari),this);
        pm.registerEvents(new JobGuiLisstener(hikari),this);
        pm.registerEvents(new FarmerGuiLisstenr(hikari),this);
        pm.registerEvents(new BauerLisstener(hikari),this);
        pm.registerEvents(new DisconectLisstener(hikari),this);
        pm.registerEvents(new WoodBreakEvent(hikari),this);
        pm.registerEvents(new WoodcuterGuiLisstenr(hikari),this);
        pm.registerEvents(new MinerGuiLisstenr(hikari),this);
        pm.registerEvents(new MinerLisstener(hikari),this);
        pm.registerEvents(new WarriorLisstener(hikari),this);
        pm.registerEvents(new WarriorGuiLisstenr(hikari),this);
        pm.registerEvents(new BuilderLisstener(hikari),this);
        pm.registerEvents(new BuilderLsstenr(hikari),this);
        pm.registerEvents(new EntdeckerLisstener(hikari),this);
        pm.registerEvents(new EntdeckerGuiLisstenr(hikari),this);

        pm.registerEvents(new Fischerisstener(hikari),this);
        pm.registerEvents(new FischerGuiLisstenr(hikari),this);


    }

    public void loadCommands(){
        getCommand("job").setExecutor(new JobCommand(this));
    }

    public void remouveBosBar(){
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if(!countdown.containsKey(onlinePlayer)) countdown.put(onlinePlayer,10);
                    int cooldown  = countdown.get(onlinePlayer);
                    if(cooldown == 0){
                        new BossBarManager().remouvetoBar(onlinePlayer);
                    }else if(cooldown >=1){
                        int amount = cooldown-1;
                        countdown.put(onlinePlayer,amount);

                    }




                }
            }
        },0,20);
    }

}
