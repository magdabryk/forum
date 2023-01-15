package pl.camp.it.forum.services;

public interface IAuthenticatorService {

    void authenticate(String login, String password);
    void logout();
}
