/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dea.connection;

import dea.beans.User;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author SS.555
 */
public class LoginRepository extends DataManager {
    
    public static Long staticId;
    
    
    public User findUser(User o) {
        String query = "select * from users where username = ? and password = ?";

        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, o.getUsername());
            statement.setString(2, o.getPassword());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                o = new User();
                o.setId(resultSet.getLong("userId"));
                o.setUsername(resultSet.getString("username"));
                o.setPassword(resultSet.getString("password"));
                o.setName(resultSet.getString("user_name"));
                o.setSurname(resultSet.getString("user_surname"));
                o.setEmail(resultSet.getString("user_email"));
                staticId = resultSet.getLong("userId");
            } else {
                o = null;
            }
        } catch (IOException | SQLException e) {
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
            }
        }
        return o;
    }
    

}
