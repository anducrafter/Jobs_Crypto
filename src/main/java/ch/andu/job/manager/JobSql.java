package ch.andu.job.manager;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class JobSql {

    private HikariDataSource hikari;

    public JobSql(HikariDataSource hikari){
        this.hikari = hikari;

    }

    public void loadtables(){
        createTableWarrior();
        createTablePlayer();
        createTableBuilder();
        createTableWoodcuter();
        createTableEndtdecker();
        createTableFisher();
        createTableMiner();
        createTableFarmer();
    }

    private void createTableFarmer() {
        String update = "CREATE TABLE IF NOT EXISTS Jobs_farmer(player_uuid varchar(64), player_level int, player_xp int);";
        Connection connection = null;
        PreparedStatement p = null;
        try {
            connection = hikari.getConnection();
            connection = hikari.getConnection();
            p = connection.prepareStatement(update);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createTableBuilder() {
        String update = "CREATE TABLE IF NOT EXISTS Jobs_builder(player_uuid varchar(64), player_level int, player_xp int);";
        Connection connection = null;
        PreparedStatement p = null;
        try {
            connection = hikari.getConnection();
            connection = hikari.getConnection();
            p = connection.prepareStatement(update);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void createTableWarrior() {
        String update = "CREATE TABLE IF NOT EXISTS Jobs_warrior(player_uuid varchar(64), player_level int, player_xp int);";
        Connection connection = null;
        PreparedStatement p = null;
        try {
            connection = hikari.getConnection();
            connection = hikari.getConnection();
            p = connection.prepareStatement(update);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createTableWoodcuter() {
        String update = "CREATE TABLE IF NOT EXISTS Jobs_woodcuter(player_uuid varchar(64), player_level int, player_xp int);";
        Connection connection = null;
        PreparedStatement p = null;
        try {
            connection = hikari.getConnection();
            connection = hikari.getConnection();
            p = connection.prepareStatement(update);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createTableFisher() {
        String update = "CREATE TABLE IF NOT EXISTS Jobs_fischer(player_uuid varchar(64), player_level int, player_xp int);";
        Connection connection = null;
        PreparedStatement p = null;
        try {
            connection = hikari.getConnection();
            p = connection.prepareStatement(update);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createTableEndtdecker() {
        String update = "CREATE TABLE IF NOT EXISTS Jobs_entdecker(player_uuid varchar(64), player_level int, player_xp int);";
        Connection connection = null;
        PreparedStatement p = null;
        try {
            connection = hikari.getConnection();
            connection = hikari.getConnection();
            p = connection.prepareStatement(update);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void createTableMiner() {
        String update = "CREATE TABLE IF NOT EXISTS Jobs_miner(player_uuid varchar(64), player_level int, player_xp int);";
        Connection connection = null;
        PreparedStatement p = null;
        try {
            connection = hikari.getConnection();
            connection = hikari.getConnection();
            p = connection.prepareStatement(update);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public boolean playerExist(String uuid, String table)  {
        Connection connection  = null;
        PreparedStatement stmt = null;
        String update = "SELECT player_uuid FROM Jobs_"+table+" WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(1,uuid);
            ResultSet rs = stmt.executeQuery();
           if(rs.next()){
               return true;
           }
            stmt.execute();

        } catch (SQLException e) {
          e.printStackTrace();
        } finally {

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return  false;
    }



    private void createTablePlayer() {
        String update = "CREATE TABLE IF NOT EXISTS Jobs_player(player_uuid varchar(64), player_job char(36));";
        Connection connection = null;
        PreparedStatement p = null;
        try {
            connection = hikari.getConnection();
            connection = hikari.getConnection();
            p = connection.prepareStatement(update);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createPlayerJobs(String uuid,String jobtable){
        if(playerExist(uuid,jobtable)){
            return;
        }
        Connection connection  = null;
        PreparedStatement stmt = null;
            String update = "INSERT INTO Jobs_"+jobtable+"(player_uuid, player_level, player_xp) VALUES (?, ?,?)";
            try {
                connection = hikari.getConnection();
                stmt = connection.prepareStatement(update);
                stmt.setString(1,uuid);
                stmt.setInt(2,1);
                stmt.setInt(3,0);
                stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }




    //Hier ist zeug um spieler job zu geben
    public void createPlayer(String uuid){
        if(playerExist(uuid,"player")){
            return;
        }
        Connection connection  = null;
        PreparedStatement stmt = null;
        String update = "INSERT INTO Jobs_player(player_uuid, player_job) VALUES (?, ?)";
        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(1,uuid);
            stmt.setString(2,"");
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    public void setPlayerJob(String uuid, String job){
        if(!playerExist(uuid,"player")){
          return;
        }
        Connection connection  = null;
        PreparedStatement stmt = null;
        String update = "UPDATE Jobs_player set player_job=? WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(2,uuid);
            stmt.setString(1,job);
            ResultSet rs = stmt.getResultSet();

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public String getPlayerJob(String uuid) {

        if (!playerExist(uuid, "player")) {
            return "";
        }
        Connection connection = null;
        PreparedStatement stmt = null;
        String update = "SELECT player_job FROM Jobs_player WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(1, uuid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("player_job");
            }
            stmt.execute();

        } catch (SQLException e) {
           return "";
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
        return  "";
    }

    //Jobs level und Xp geter and seter
    public int getPlayerJobLevel(String uuid, String jobtable){
        if(!playerExist(uuid,jobtable)){
            createPlayerJobs(uuid,jobtable);
            return 0;
        }
        Connection connection  = null;
        PreparedStatement stmt = null;
        String update = "SELECT player_level FROM Jobs_"+ jobtable+" WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(1,uuid);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){

                return rs.getInt("player_level");
            }
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return 0;
    }

    public ArrayList<String> getMaxJob(String jobtable){
        Connection connection  = null;
        PreparedStatement stmt = null;
        String update = "SELECT player_uuid FROM Jobs_"+ jobtable+" ORDER BY player_level DESC";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            ResultSet rs =stmt.executeQuery();
            int amount = 0;
           ArrayList<String> map = new ArrayList<>();
            while (rs.next() && amount<10){
                amount++;
                String uuid = rs.getString("player_uuid");
               map.add("§e"+Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName() +" §7"+getPlayerJobLevel(uuid,jobtable));
            }

            stmt.execute();
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    public int getPlayerJobXp(String uuid, String jobtable){
        if(!playerExist(uuid,jobtable)){
            createPlayerJobs(uuid,jobtable);
            return 0;
        }
        Connection connection  = null;
        PreparedStatement stmt = null;
        String update = "SELECT player_xp FROM Jobs_"+ jobtable+" WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(1,uuid);
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){

                return rs.getInt("player_xp");
            }
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return 0;
    }

    public int setPlayerJobLevel(String uuid, String jobtable,int amount){
        if(!playerExist(uuid,jobtable)){
            return 0;
        }
        Connection connection  = null;
        PreparedStatement stmt = null;
        String update = "UPDATE Jobs_"+jobtable+" set player_level=? WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(2,uuid);
            stmt.setInt(1,amount);


            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return 0;
    }

    public void setPlayerJobXp(String uuid, String jobtable,int amount){
        if(!playerExist(uuid,jobtable)){
            return;
        }
        Connection connection  = null;
        PreparedStatement stmt = null;
        String update = "UPDATE Jobs_"+jobtable+" set player_xp=? WHERE player_uuid=?";

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(update);
            stmt.setString(2,uuid);
            stmt.setInt(1,amount);
          stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
