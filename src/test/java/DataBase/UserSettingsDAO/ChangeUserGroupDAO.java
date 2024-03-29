package DataBase.UserSettingsDAO;

import DataBase.ConnectBD;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeUserGroupDAO extends ConnectBD {

    public static final String selectGroupID ="select Apps_GroupsID from Apps_Groups where GroupName = ?";
    public int getGroupId(String groupName) {
        int id = 0;
        try (PreparedStatement preparedStatement = getConnectionButest24().prepareStatement(selectGroupID)) {
            preparedStatement.setString(1, groupName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("Apps_GroupsID");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static final String updateUserGroup ="use budget\n" +
            "update Apps_Users Set Apps_GroupsID= ? " +
            "Where Username= ?";
    public void updateUserGroup(String groupName, String username) {
        int groupId = getGroupId(groupName);
        try (PreparedStatement preparedStatement = getConnectionButest24().prepareStatement(updateUserGroup)) {
            preparedStatement.setInt(1, groupId);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String selectUserGroupName ="select Apps_Groups.GroupName from Apps_Users\n" +
            "         inner join Apps_Groups on Apps_Users.Apps_GroupsID = Apps_Groups.Apps_GroupsID\n" +
            "             where Apps_Users.UserName = ?";
    public String checkUserGroup(String username){
        try (PreparedStatement preparedStatement = getConnectionButest24().prepareStatement(selectUserGroupName)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("GroupName");
                } else {
                    return null;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test (){
        updateUserGroup("7830002430/1599", "UKT_Riabtsev");
    }
}
