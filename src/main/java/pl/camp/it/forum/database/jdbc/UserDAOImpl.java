package pl.camp.it.forum.database.jdbc;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.forum.database.IUserDAO;
import pl.camp.it.forum.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    Connection connection;

    @Override
    public Optional<User> getUserByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                return Optional.of(new User(
                      rs.getInt("id"),
                      rs.getString("login"),
                      rs.getString("password"),
                      rs.getString("name"),
                      rs.getString("surname"),
                      User.Role.valueOf(rs.getString("role"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(int id) {
        try {
            String sql = "SELECT * FROM tuser WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        User.Role.valueOf(rs.getString("role"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO tuser (login, password, name, surname, role) VALUES (?,?,?,?,?);";
            PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, DigestUtils.md5Hex(user.getPassword()));
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getRole().toString());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<User> getUserList() {
        List<User> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tuser;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
           ResultSet rs =  ps.executeQuery();
           while (rs.next()){
               result.add(new User(
                       rs.getInt("id"),
                       rs.getString("login"),
                       rs.getString("password"),
                       rs.getString("name"),
                       rs.getString("surname"),
                       User.Role.valueOf(rs.getString("role"))
               ));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void editUser(User user, String roleStr) {
    Optional<User> userBox = getUserById(user.getId());
    if(userBox.isPresent()){
        try {
            String sql = "UPDATE tuser SET login = ?, password = ?, name = ?, surname =?, role =? WHERE id = ?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.setString(2, DigestUtils.md5Hex(user.getPassword()));
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getRole().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
}
