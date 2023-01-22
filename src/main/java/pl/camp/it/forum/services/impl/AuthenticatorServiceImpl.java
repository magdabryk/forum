package pl.camp.it.forum.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import pl.camp.it.forum.database.IUserDAO;
import pl.camp.it.forum.model.User;
import pl.camp.it.forum.services.IAuthenticatorService;
import pl.camp.it.forum.session.SessionObject;

import java.util.Optional;

@Service
public class AuthenticatorServiceImpl implements IAuthenticatorService {
    @Resource
    SessionObject sessionObject;

    @Autowired
    IUserDAO userDAO;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.userDAO.getUserByLogin(login);
        if (userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setUser(new User.UserBuilder()
                    .clone(userBox.get())
                    .password(null)
                    .build()
            );
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setUser(null);
    }



    //todo weryfikacja login i hasło, imie i nazwisko
    //todo obsługa błędów usuwnie jedynego postu w temacie / blędne dane w loginie
    // todo JS
}
