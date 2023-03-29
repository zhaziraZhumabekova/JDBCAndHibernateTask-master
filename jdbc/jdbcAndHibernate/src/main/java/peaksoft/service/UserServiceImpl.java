package peaksoft.service;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable (){
        String SQL = "CREATE TABLE IF NOT EXISTS users(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "last_name VARCHAR(50) NOT NULL," +
                "age INTEGER);";
        try(Connection connection = Util.connection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(SQL);
        }catch (SQLException e) {
            e.printStackTrace();;
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users";
        try(Connection con = Util.connection();
            PreparedStatement statement = con.prepareStatement(SQL)){
        } catch ( SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users(name, last_name, age)" +
                "VALUES(?, ?, ?)";
        try(Connection con = Util.connection();
            PreparedStatement statement = con.prepareStatement(SQL)){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println(name + " successfully saved");
        } catch ( SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.connection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try(Connection con = Util.connection();
            Statement statement = con.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE  FROM users";
        try (Connection connection = Util.connection();
             PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.executeUpdate();
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}
