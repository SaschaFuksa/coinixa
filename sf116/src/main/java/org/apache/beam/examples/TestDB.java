package org.apache.beam.examples;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class TestDB {
    
    private static final String DB_NAME = "35.240.83.255";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";
    private static final String DB_CONNECTION = "sixth-oath-339016:europe-west1:coinixa-db"; //85.214.211.180
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HikariConfig config = new HikariConfig();
        
        // Configure which instance and what database user to connect with.
        config.setJdbcUrl(String.format("jdbc:mysql:///%s", DB_NAME));
        config.setUsername(DB_USER); // e.g. "root", "postgres"
        config.setPassword(DB_PASS); // e.g. "my-password"
        
        // For Java users, the Cloud SQL JDBC Socket Factory can provide authenticated connections.
        // See https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory for details.
        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", DB_CONNECTION);
        config.addDataSourceProperty("useSSL", "false");
        config.addDataSourceProperty("ipTypes", "PUBLIC,PRIVATE");
    
        // ... Specify additional connection properties here.
        // ...
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(5);
    
        config.setConnectionTimeout(10000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000); // 30 minutes
    
        // Initialize the connection pool using the configuration object.
        DataSource pool = new HikariDataSource(config);
        try (Connection conn = pool.getConnection()) {
        
            // PreparedStatements can be more efficient and project against injections.
            String stmt = "INSERT INTO votes (time_cast, candidate) VALUES (?, ?);";
            try (PreparedStatement voteStmt = conn.prepareStatement(stmt);) {
                //voteStmt.setTimestamp(1, now);
                //voteStmt.setString(2, team);
            
                // Finally, execute the statement. If it fails, an error will be thrown.
                voteStmt.execute();
            }
        } catch (SQLException ex) {
            // If something goes wrong, handle the error in this section. This might involve retrying or
            // adjusting parameters depending on the situation.
            // ...
        }
        /**
        String instanceConnectionName = "sixth-oath-339016:europe-west3"; //<Your instanceConnectionName>
        String databaseName = "coinixa-db"; //<Database name from which u want to list tables>;
        
        
        String IP_of_instance = "34.159.35.130"; //<IP address of the instance>;
        String username = "root"; // <mysql username>;
        String password = "NKe4HzDp13Ombg2i"; // <mysql password>; NKe4HzDp13Ombg2i
        
        String jdbcUrl = String.format(
                "jdbc:mysql://%s/%s?cloudSqlInstance=%s"
                        + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
                IP_of_instance,
                databaseName,
                instanceConnectionName);
        //Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");
            System.out.println(resultSet);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        **/
        
    }
}
