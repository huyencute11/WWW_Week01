package iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private final Connection connection;
    private static ConnectDB instance = null;
    public ConnectDB() throws ClassNotFoundException, SQLException {

//      FOR MYSQL
//        String jdbcUrl = "jdbc:mysql://localhost:3307/mydb?createDatabaseIfNotExist=true";
//        Class.forName("com.mysql.cj.jdbc.Driver"); // Note the different driver class
//        connection = DriverManager.getConnection(jdbcUrl, "root", "sapassword");

//        For mariadb
        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://localhost:3307/mydb?createDatabaseIfNotExist=true";
        connection = DriverManager.getConnection(url, "root", "sapassword");
    }
    public Connection getConnection(){
        return connection;
    }
    public static ConnectDB getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null)
            instance = new ConnectDB();
        return instance;

    }
}
