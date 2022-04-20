package elearning;

import org.flywaydb.database.mysql.mariadb.MariaDBDatabase;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        MariaDbDataSource dataSource;

        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/bookstore?useUnicode=true");
            dataSource.setUser("***");
            dataSource.setPassword("***");
        } catch (
                SQLException sqle) {
            throw new IllegalStateException("Cannot reach DataBase!", sqle);
        }

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            try /* Statement vagy PreparedStatem. */ {
                // utasítások
                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
            }

        } catch (SQLException sqle) {

        }

    }
}