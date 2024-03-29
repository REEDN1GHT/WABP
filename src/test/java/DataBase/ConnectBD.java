package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {

    private String BudgetXX = "budget24";
    Connection connectionButest24;

    public Connection getConnectionButest24() throws ClassNotFoundException, SQLException {
        try {
            String dbURLBudget23 = "jdbc:sqlserver://10.241.3.15:1433;database=butest24" ;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectionButest24 = DriverManager.getConnection(dbURLBudget23, "UKT_Riabtsev", "UKT_Riabtsev1");
        } catch (Exception ex23) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(ex23);
            System.exit(-1);
        }
        return connectionButest24;
    }

}
