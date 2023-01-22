package pl.camp.it.forum.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.forum.database.IUserDAO;
import pl.camp.it.forum.model.User;
import pl.camp.it.forum.services.IUserService;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDAO userDAO;

    @Override
    public void addUser(User user) {
        user.setRole(User.Role.USER);
        this.userDAO.addUser(user);
    }

    @Override
    public void editUser(User user) {

    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> getUserList() {
        return this.userDAO.getUserList();
    }
}
