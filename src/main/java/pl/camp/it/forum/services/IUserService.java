package pl.camp.it.forum.services;

import pl.camp.it.forum.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void addUser(User user);
    void editUser(User user);
    void deleteUser(int userId);
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(int id);
    List<User> getUserList();
}
