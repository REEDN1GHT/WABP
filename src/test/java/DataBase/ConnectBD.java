package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {

    private String BudgetXX = "budget24";
    Connection connectionBudget23UKT;

    public Connection getConnectionBudget23UKT() throws ClassNotFoundException, SQLException {
        try {
            String dbURLBudget23 = "jdbc:sqlserver://10.69.0.169:1433;database=budget23" ;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectionBudget23UKT = DriverManager.getConnection(dbURLBudget23, "UKT_Riabtsev", "UKT_Riabtsev1");
        } catch (Exception ex23) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(ex23);
            System.exit(-1);
        }
        return connectionBudget23UKT;
    }

}
