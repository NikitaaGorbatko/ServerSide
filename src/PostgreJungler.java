import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreJungler {
    private final String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=1";
    private Connection connection;

    PostgreJungler() {
        try {
            connection = DriverManager.getConnection(url);
            make();
        } catch (SQLException ex) {
            System.out.println("\nConnection is failed\nError: " + ex.getMessage() + "\nAsk the administrator for help.");
        }
    }

    private void reconnectIfDropped() throws SQLException {
         if (connection.isClosed() || connection == null) {
            connection = DriverManager.getConnection(url);
        }
    }

    private void make() throws SQLException {
        reconnectIfDropped();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS User (" +
                "user_id int,\n" +
                "name varchar(120),\n" +
                "@mail varchar(254));");
        statement.execute("CREATE TABLE IF NOT EXISTS Language (" +
                "language varchar(100) PRIMARY KEY;);");
        statement.execute("CREATE TABLE IF NOT EXISTS Word_set (" +
                "word_set_id int,\n" +
                "account_id int,\n" +
                "name_set int,\n" +
                "description_set varchar(120),\n" +
                "cost int);");//stop
        statement.close();
    }
}
