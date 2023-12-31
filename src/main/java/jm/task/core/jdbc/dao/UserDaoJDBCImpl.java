package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection conn;

    public UserDaoJDBCImpl() {
        conn = Util.getConnection();
    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS Users"
                + "  (id              INTEGER generated by default as identity,"
                + "   name            VARCHAR(256),"
                + "   lastName        VARCHAR(256),"
                + "   age             INTEGER)";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String sqlCreate = "DROP TABLE IF EXISTS Users";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL_INSERT = "INSERT INTO Users (name, lastName, age) VALUES (?,?,?)";
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String SQL_INSERT = "DELETE FROM Users WHERE id=?";
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String SQL_INSERT = "SELECT * FROM Users";
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");

                User newUser = new User();
                newUser.setId(id);
                newUser.setName(name);
                newUser.setLastName(lastName);
                newUser.setAge((byte) age);

                users.add(newUser);

            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return users;
    }

    public void cleanUsersTable() {
        String SQL_INSERT = "DELETE FROM Users";
        try (
                PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
