import java.sql.PreparedStatement;

public class SupplierController {

    public static void selectSuppliersWProducts() {
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT Suppliers.SupplierID,Suppliers.SupplierName,Suppliers.ContactNo,Food.ItemID FROM Suppliers JOIN Food ON Suppliers.ItemID = Food.ItemID");



        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
}
