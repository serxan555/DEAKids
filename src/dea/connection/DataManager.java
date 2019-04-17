
package dea.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author SS.555
 */
public class DataManager {
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet resultSet;
    
    protected void connect() throws SQLException, IOException{
        
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        
        String filePath = "dea/connection/config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream in = loader.getResourceAsStream(filePath);
        
        Properties properties = new Properties();
        properties.load(in);
        
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        
        connection = DriverManager.getConnection(url, username, password);
        if (!connection.equals(null)) {
            System.out.println("***Success***");
        }
        in.close();
    }
    
    protected void disconnect() throws SQLException{
        if (resultSet!=null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    
    
    
}
