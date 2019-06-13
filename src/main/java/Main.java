import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static Connection db = null;

    public static void main(String[] args) {
        openDatabase("Database.db");

        //inserts a record//
        selectAll();
        insertRecord();

        closeDatabase();
    }

    private static void openDatabase(String dbFile) {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");
        } catch (Exception exception) {
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }

    private static void closeDatabase() {
        try {
            db.close();
            System.out.println("Disconnected from database.");
        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public static void selectAll() {
        try {

            PreparedStatement ps = db.prepareStatement("SELECT * FROM food");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int FoodID = results.getInt(1);
                String Name = results.getString(2);
                String ExpiryDate = results.getString(3);
                int Quantity = results.getInt(4);
                System.out.println(FoodID + " " + Name + " " + ExpiryDate + "" + Quantity);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    public static void insertRecord() {

        try {

            PreparedStatement ps = db.prepareStatement("INSERT INTO food (FoodId, Name, ExpiryDate, Quantity) VALUES (?, ?, ?, ?)");

            ps.setInt(1, 6);
            ps.setString(2, "Scampi");
            ps.setString(3, "2019-01-12");
            ps.setInt(4, 87);

            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }


    }



}