package pl.camp.it.forum.database;

import pl.camp.it.forum.model.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(int id);
    void addUser(User user);

    List<User>  getUserList();
    void editUser(User user, String roleStr);
}
