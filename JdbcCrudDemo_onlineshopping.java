package jdbc;
import java.sql.*;
import java.util.Scanner;

public class JdbcCrudDemo_onlineshopping {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/online_store";
        String user = "root";  // your MySQL username
        String pass = "@shree101";  // your MySQL password

        Scanner sc = new Scanner(System.in);

        try {
            // 1️⃣ Load and register driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2️⃣ Establish connection
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Database connected successfully! - JdbcCrudDemo.java:21");

            while (true) {
                System.out.println("\n=== Products Database Menu === - JdbcCrudDemo.java:24");
                System.out.println("1. Insert Product - JdbcCrudDemo.java:25");
                System.out.println("2. Display Products - JdbcCrudDemo.java:26");
                System.out.println("3. Update Product - JdbcCrudDemo.java:27");
                System.out.println("4. Delete Product - JdbcCrudDemo.java:28");
                System.out.println("5. Exit - JdbcCrudDemo.java:29");
                System.out.print("Enter your choice: - JdbcCrudDemo.java:30");
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        // ➕ INSERT Operation
                        System.out.print("Enter Product ID: - JdbcCrudDemo.java:37");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Product Name: - JdbcCrudDemo.java:40");
                        String name = sc.nextLine();

                        System.out.print("Enter Category: - JdbcCrudDemo.java:42");
                        String category = sc.nextLine();

                        System.out.print("Enter Price: - JdbcCrudDemo.java:44");
                        double price = sc.nextDouble();

                        System.out.print("Enter Stock: - JdbcCrudDemo.java:46");
                        int stock = sc.nextInt();

                        String insertQuery = "INSERT INTO products VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement psInsert = con.prepareStatement(insertQuery);

                        psInsert.setInt(1, id);
                        psInsert.setString(2, name);
                        psInsert.setString(3, category);
                        psInsert.setDouble(4, price);
                        psInsert.setInt(5, stock);

                        int rowsInserted = psInsert.executeUpdate();
                        System.out.println(rowsInserted + " record inserted successfully! - JdbcCrudDemo.java:52");
                        psInsert.close();
                        break;

                    case 2:
                        // 📋 DISPLAY Operation
                        String selectQuery = "SELECT * FROM products";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(selectQuery);

                        System.out.println("\nID\tName\tCategory\tPrice\tStock - JdbcCrudDemo.java:62");
                        System.out.println("");

                        while (rs.next()) {
                            System.out.println(
                                rs.getInt("product_id") + "\t" +
                                rs.getString("product_name") + "\t" +
                                rs.getString("category") + "\t" +
                                rs.getDouble("price") + "\t" +
                                rs.getInt("stock")
                            );
                        }

                        rs.close();
                        stmt.close();
                        break;

                    case 3:
                        // ✏ UPDATE Operation
                        System.out.print("Enter Product ID to update: - JdbcCrudDemo.java:73");
                        int uid = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new name: - JdbcCrudDemo.java:76");
                        String newName = sc.nextLine();

                        System.out.print("Enter new category: - JdbcCrudDemo.java:78");
                        String newCategory = sc.nextLine();

                        System.out.print("Enter new price: - JdbcCrudDemo.java:80");
                        double newPrice = sc.nextDouble();

                        System.out.print("Enter new stock: - JdbcCrudDemo.java:82");
                        int newStock = sc.nextInt();

                        String updateQuery = "UPDATE products SET product_name=?, category=?, price=?, stock=? WHERE product_id=?";
                        PreparedStatement psUpdate = con.prepareStatement(updateQuery);

                        psUpdate.setString(1, newName);
                        psUpdate.setString(2, newCategory);
                        psUpdate.setDouble(3, newPrice);
                        psUpdate.setInt(4, newStock);
                        psUpdate.setInt(5, uid);

                        int rowsUpdated = psUpdate.executeUpdate();
                        System.out.println(rowsUpdated + " record updated successfully! - JdbcCrudDemo.java:88");
                        psUpdate.close();
                        break;

                    case 4:
                        // ❌ DELETE Operation
                        System.out.print("Enter Product ID to delete: - JdbcCrudDemo.java:94");
                        int did = sc.nextInt();

                        String deleteQuery = "DELETE FROM products WHERE product_id=?";
                        PreparedStatement psDelete = con.prepareStatement(deleteQuery);

                        psDelete.setInt(1, did);

                        int rowsDeleted = psDelete.executeUpdate();
                        System.out.println(rowsDeleted + " record deleted successfully! - JdbcCrudDemo.java:102");
                        psDelete.close();
                        break;

                    case 5:
                        // 🚪 EXIT
                        System.out.println("Exiting program... Goodbye! - JdbcCrudDemo.java:108");
                        con.close();
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("❌ Invalid choice! Try again. - JdbcCrudDemo.java:114");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

