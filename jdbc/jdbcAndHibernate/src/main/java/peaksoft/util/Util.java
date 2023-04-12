package peaksoft.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

    private static SessionFactory buildSessionFactory(){
        SessionFactory sessionFactory = null;
        try{
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
//                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return sessionFactory;
    }
    public static SessionFactory getSessionFactory(){
        return buildSessionFactory();
    }
}
