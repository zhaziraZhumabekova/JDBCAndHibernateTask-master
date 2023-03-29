package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
        private static final String URL ="jdbc:postgresql://localhost:5432/maven_project";
        private static final String USER_NAME = "postgres";
        private static final String PASSWORD = "0909";
        public static Connection connection() {
            Connection connection = null;
            try{
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println("Connection successful");
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return connection;
        }

}
