import java.sql.*;
import java.util.*;

public class AtmMachine {
    public static void main(String arg[]) throws ClassNotFoundException, SQLException {
        int balance = 100000, withdraw, deposit;
        Scanner sc = new Scanner(System.in);
        ResultSet res = null;
        Connection c = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pydb", "root", "Aakash@1");
            System.out.println("Connection Established");
            
            Statement s = c.createStatement();
            res = s.executeQuery("SELECT * FROM atm");
            
            // Fetching data from ResultSet
            while (res.next()) {
                String loginid = res.getString("loginid");
                String pin = res.getString("pin");
                System.out.println("Enter your loginid:");
                String a = sc.nextLine();
                System.out.println("Enter pin:");
                String b = sc.nextLine();
                
                if (a.equals(loginid) && b.equals(pin)) {
                    while (true) {
                        System.out.println("Automated Teller Machine");
                        System.out.println("Choose 1 for Withdraw");
                        System.out.println("Choose 2 for Deposit");
                        System.out.println("Choose 3 for Check Balance");
                        System.out.println("Choose 4 for EXIT");
                        System.out.print("Choose the operation you want to perform:");

                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.print("Enter money to be withdrawn:");
                                withdraw = sc.nextInt();
                                if (balance >= withdraw) {
                                    balance = balance - withdraw;
                                    System.out.println("Please collect your money");
                                } else {
                                    System.out.println("Insufficient Balance");
                                }
                                System.out.println("");
                                break;
                            case 2:
                                System.out.print("Enter money to be deposited:");
                                deposit = sc.nextInt();
                                balance = balance + deposit;
                                System.out.println("Your Money has been successfully deposited");
                                System.out.println("");
                                break;
                            case 3:
                                System.out.println("Balance : " + balance);
                                System.out.println("");
                                break;
                            case 4:
                                System.exit(0);
                            default:
                                System.out.println("Invalid choice. Please choose again.");
                        }
                    }
                } else {
                    System.out.println("Invalid login credentials.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            if (res != null) {
                res.close();
            }
            if (c != null) {
                c.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
}
