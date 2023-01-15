package pl.camp.it.forum.database.memory;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.forum.database.IUserDAO;
import pl.camp.it.forum.exceptions.LoginExsistException;
import pl.camp.it.forum.model.User;
import pl.camp.it.forum.sequence.IUserIdSequence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class UserDB implements IUserDAO {

    private final List<User> users = new ArrayList<>();

    @Autowired
    IUserIdSequence userIdSequence;

    public UserDB(@Autowired IUserIdSequence userIdSequence) {
        this.userIdSequence = userIdSequence;
        this.users.add(new User(this.userIdSequence.getId(), "admin", "21232f297a57a5a743894a0e4a801fc3", "jan", "kowalski", User.Role.ADMIN));
        this.users.add(new User(this.userIdSequence.getId(), "user", "ee11cbb19052e40b07aac0ca060c23ee", "adam", "nowak", User.Role.USER));
        this.users.add(new User(this.userIdSequence.getId(), "magda", "3aa99d4bac1ffaa7be5a0c4e03595699", "magda", "nowak", User.Role.MODERATOR));
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        for(User user: this.users){
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {
        if(getUserByLogin(user.getLogin()).isPresent()){
            throw  new LoginExsistException();
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setRole(User.Role.USER);
        users.add(user);

    }

    @Override
    public List<User> getUserList() {
        return null;
    }

    @Override
    public void userSetRole(String roleStr, int id) {
        Optional<User> userBox = getUserById(id);
        if(userBox.isPresent()){
            userBox.get().setRole(User.Role.valueOf(roleStr));
        }
    }

    @Override
    public void editUser(User user, String roleStr) {
        userSetRole(roleStr, user.getId());
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        addUser(user);
    }
}
