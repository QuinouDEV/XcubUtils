package fr.quinou.xcubutils.sql;

import fr.quinou.xcubutils.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SqlPlayerListener
{
    private Main main;


    public SqlPlayerListener(Main main)
    {
        this.main = main;
    }
    private Connection connection;

    public void createTable(){
        connection = main.getSqlManager().getConnection();
        PreparedStatement ps;
        try{
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS players_money"
                    + "(Pseudo VARCHAR(100), UUID VARCHAR(100), XCoin INT(100) DEFAULT '0' NOT NULL, PRIMARY KEY (Pseudo))");
            ps.executeUpdate();
// DEFAULT '0' NOT NULL
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public boolean playerFound(UUID uuid){
        try{
            connection = main.getSqlManager().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM players_money WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                main.getServer().broadcastMessage(ChatColor.YELLOW + "Player found");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String getXCoins(UUID uuid){

        try {
            connection = main.getSqlManager().getConnection();
            PreparedStatement ps;
            ps = connection.prepareStatement("SELECT XCoin FROM players_money WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet rs = ps.executeQuery();
            int coins = 0;
            if (rs.next()){
                coins = rs.getInt("XCoin");
                return String.valueOf(coins);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Vide";
    }



    public void createPlayer(Player p){
        connection = main.getSqlManager().getConnection();
        if(main.sqlManager.isConnected()){
            try {
                UUID uuid = p.getUniqueId();
                if(!uuidExist(uuid)){
                    PreparedStatement ps2 = connection.prepareStatement("INSERT INTO players_money" +
                            "(Pseudo,UUID) VALUES (?,?)");
                    ps2.setString(1, p.getName());
                    ps2.setString(2, uuid.toString());
                    ps2.executeUpdate();



                }

            } catch (SQLException e){
                e.printStackTrace();
            }


        }


    }


    public boolean uuidExist(UUID uuid){
        if(main.sqlManager.isConnected()){
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM players_money WHERE UUID=?");
                ps.setString(1, uuid.toString());
                ResultSet results = ps.executeQuery();
                if (results.next()) {
                    return true;
                } return false;

            } catch (SQLException e){
                e.printStackTrace();
            }

        }
        return false;
    }

    public void addXCoin(UUID uuid, int coins){

        try {
            connection = main.getSqlManager().getConnection();
            PreparedStatement ps;
            ps = connection.prepareStatement("UPDATE players_money SET XCoin=? WHERE UUID=?");
            int res = Integer.parseInt(getXCoins(uuid)) + coins;
            ps.setInt(1, res);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public void setXCoins(UUID uuid, int coins){
        connection = main.getSqlManager().getConnection();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("UPDATE players_money SET XCoin=? WHERE UUID=?");
            ps.setInt(1, coins);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
