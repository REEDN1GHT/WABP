package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CleanUserData extends ConnectBD{
    private static final String sqlDeleteTestData = "EXECUTE butest24.dbo.DeleteTestData 'UKT_Riabtsev'";

    public void cleanData(){
        try {
            Statement statement = getConnectionButest24().createStatement();
            statement.execute(sqlDeleteTestData);
        } catch (SQLException e) {
            if ("S0001".equals(e.getSQLState()) && e.getErrorCode()==50000) {
                System.out.println("Ошибка: Данные таблицы AP_Obj_History не доступны для редактирования");
            } else {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
