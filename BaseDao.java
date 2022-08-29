import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class BaseDao {
    Connection mysqlConnection() {
       try {
            Connection connection = DriverManager.getConnection(Constants.DRIVE_URL,Constants.SQL_USER_ID,Constants.SQL_PASSWORD);
            return connection;
        } catch (Exception e) {
            
        }
        return null;
    }
    
    
}