package Repository.DB;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Konfiguracija.Konfiguracija;

public class DbConnectionFactory {
    private static DbConnectionFactory instance;
    Connection conn;
    Konfiguracija config;

    private DbConnectionFactory() {
        try {
            if (conn == null || conn.isClosed()) {
                config = Konfiguracija.getInstance();
                String url = config.getProperty("url");
                String username = config.getProperty("username");
                String password = config.getProperty("password");
                conn = DriverManager.getConnection(url, username, password);
                conn.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnectionFactory getInstance() {
        if (instance == null)
            instance = new DbConnectionFactory();
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}