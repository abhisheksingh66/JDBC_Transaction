import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url ="jdbc:mysql://localhost:3306/school";
        String username = "root";
        String password = "@Anshulsingh66";
        String widthDrawQuery =" UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successful");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");
            con.setAutoCommit(false);
            try {

                PreparedStatement withdrwaStatement = con.prepareStatement(widthDrawQuery);
                PreparedStatement dipositStatement = con.prepareStatement(depositQuery);
                withdrwaStatement.setDouble(1, 500.00);
                withdrwaStatement.setString(2, "account123");

                dipositStatement.setDouble(1, 500.00);
                dipositStatement.setString(2, "account456");

               int rowseffectedWidraw= withdrwaStatement.executeUpdate();
               int rowseffectDiposit= dipositStatement.executeUpdate();
               if(rowseffectedWidraw>0 && rowseffectDiposit>0){
                   con.commit();
                   System.out.println("Transaction successfully");
               }else {
                   con.rollback();

                   System.out.println("Transaction failed");
               }
            }catch (SQLException e){

                System.out.println(e.getMessage());
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}