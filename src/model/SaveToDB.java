package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by coder on 02.08.15.
 */
public class SaveToDB {
    private final String DB_CONNECTION = "jdbc:mysql://localhost/JATanks";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "12345";
    private Connection connection;
    private String name;
    private int armor;
    private int health;
    private LocalDate date;

    public SaveToDB(String name, int armor, int health){
        this.name = name;
        this.armor = armor;
        this.health = health;
    }

    private Connection getDBConnection(){
        try {
            connection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
        }catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
        return connection;
    }

    public void saveToDB() throws SQLException{
        if (connection == null) this.getDBConnection();
        if (connection == null){
            System.out.println("Fails to set connection");
            return;
        }

        connection.setAutoCommit(false);
        String query = "INSERT INTO WinnerTable (`Tank`, `Armor`, `Health`, `Date`)" +
                "VALUES(?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, name);
            ps.setInt(2, armor);
            ps.setInt(3, health);
            date = LocalDate.now();
            ps.setDate(4, Date.valueOf(date));
            ps.executeUpdate();
            System.out.println("Saved");
        }catch (SQLException ex){
            connection.rollback();
            ex.printStackTrace();
        }finally {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }
}
