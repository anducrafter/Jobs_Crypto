package ch.andu.job.placeholderAPI;
import ch.andu.job.manager.JobSql;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Placeholder extends PlaceholderExpansion {

    JobSql sql;

    public Placeholder(JobSql sql){
        this.sql = sql;
    }
    @Override
    public @NotNull String getIdentifier() {
        return "Jobs";
    }

    @Override
    public @NotNull String getAuthor() {
        return "anducrafter";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.2";
    }

    public String onPlaceholderRequest(Player player, String identifier) {


        //Keine AHnung wiso, muss aber bei dieser methode ein String übergeben. Bin Selber überrascht gewesen!
        //
        if (identifier.equalsIgnoreCase("getjob")) {
            String job  = sql.getPlayerJob(player.getUniqueId().toString());
            if(!job.isEmpty()){
                return job;

            }
            return "kein Beruf";
        }
        return ""+0.0;
    }


}
