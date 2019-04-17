package dea.connection;

import dea.beans.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SS.555     ssmdbyli@gmail.com
 */
public class UserRepository extends DataManager implements Repository<User> {

     @Override
    public User save(User o) {

        try {
            String query = "insert into users "
                    + "(username, password, user_name, user_surname, user_email) "
                    + "values(?,?,?,?,?)";
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, o.getUsername());
            statement.setString(2, o.getPassword());
            statement.setString(3, o.getName());
            statement.setString(4, o.getSurname());
            statement.setString(5, o.getEmail());
            statement.execute();

            return o;
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        return null;
    }
    public User update(User o) throws SQLException{
        String query = "update users set username = ?, password = ?, user_name = ?, user_surname = ?, user_email = ? "
                + " where userId = '" + o.getId()+"'";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, o.getUsername());
            statement.setString(2, o.getPassword());
            statement.setString(3, o.getName());
            statement.setString(4, o.getSurname());
            statement.setString(5, o.getEmail());
            statement.execute();
            return o;
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        }finally{
            disconnect();
        }
        return null;
    }

    @Override
    public User find(User o) {
        System.out.println(o.getUsername());
        String query = "select * from users where userId = ? and password = ?";
        try {
            if (!o.getUsername().equals(" ")) {
                connect();
                statement = connection.prepareStatement(query);
                statement.setLong(1, LoginRepository.staticId);
                statement.setString(2, o.getPassword());
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("userId"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setName(resultSet.getString("user_name"));
                    user.setSurname(resultSet.getString("user_surname"));
                    user.setEmail(resultSet.getString("user_email"));
                    return user;
                }
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String query = "select * from users";
        List<User> userList = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (true) {

                User user = new User();
                user.setId(resultSet.getLong("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("user_name"));
                user.setSurname(resultSet.getString("user_surname"));
                userList.add(user);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        }
        try {
            disconnect();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return userList;
    }
    
    @Override
    public boolean delete(User o) {

        return false;
    }
    
    public User findUser(Long id) throws SQLException{
        String query = "select * from users where userId = " + id;
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User u = new User();
                u.setId(resultSet.getLong("userId"));
                u.setName(resultSet.getString("user_name"));
                u.setSurname(resultSet.getString("user_surname"));
                u.setEmail(resultSet.getString("user_email"));
                u.setUsername(resultSet.getString("username"));
                u.setPassword(resultSet.getString("password"));
                return u;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace(System.err);
        }finally{
            disconnect();
        }
        return null;
    }
}
