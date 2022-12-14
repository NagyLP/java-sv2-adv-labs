package elearning;

import org.flywaydb.database.mysql.mariadb.MariaDBDatabase;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

// KIEG. Pl.: StatementFactory
//    public static Connection continuousConnection(DataSource dataSource) throws SQLException {
//        @SuppressWarnings("java:S2095")
//        Connection connection = dataSource.getConnection();
//        connection.setAutoCommit(false);
//        return connection;
//    }
//
//    public static PreparedStatement getParameterizedStatement
//            (Connection connection, String stringSql, Object... values)
//            throws SQLException {
//        PreparedStatement stmt = connection.prepareStatement(stringSql);
//        for (int i = 1; i <= values.length; i++) {
//            Object parameters = values[i - 1];
//
//            if (parameters instanceof String s) {
//                stmt.setString(i, s);
//
//            } else if (parameters instanceof Integer j) {
//                stmt.setInt(i, j);
//
//            } else if (parameters instanceof Long l) {
//                stmt.setLong(i, l);
//
//            } else if (parameters instanceof Double d) {
//                stmt.setDouble(i, d);
//
//            } else if (parameters instanceof LocalDate date) {
//                stmt.setDate(i, Date.valueOf(date));
//
//            } else if (parameters instanceof LocalDateTime dateTime) {
//                stmt.setTimestamp(i, Timestamp.valueOf(dateTime));
//
//            } else if (parameters instanceof LocalTime time) {
//                stmt.setTime(i, Time.valueOf(time));
//
//            } else {
//                throw new IllegalArgumentException(String.format("Invalid parameter type at SQL-index %d: %s",
//                        i, parameters.getClass().getName()));
//            }
//        }
//        return stmt;
//    }
}
