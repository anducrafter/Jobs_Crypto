package ch.andu.job.manager;


import ch.andu.job.Job;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class JobManager {

    public static HashMap<String,String> playerjob = new HashMap<>();
    public  static HashMap<String,Integer> playerjoblevel = new HashMap<>();
    public static HashMap<String,Integer>playerjobxp = new HashMap<>();

    public static Integer getPlayerjoblevel(String player) {
        return playerjoblevel.get(player);
    }

    public static Integer getPlayerjobxp(String player){
        return playerjobxp.get(player);
    }

    public   static String getPlayerjob(String player) {
        return playerjob.get(player);
    }

    public static   void setPlayerjobxp(String player,Integer playerjobxp) {
        JobManager.playerjobxp.put(player,playerjobxp);
    }

    public  static void setPlayerjoblevel(String player,Integer playerjoblevel) {
        JobManager.playerjoblevel.put(player,playerjoblevel);
    }

    public static void setPlayerjob(String player,String playerjob) {
        JobManager.playerjob.put(player,playerjob);
    }
}
