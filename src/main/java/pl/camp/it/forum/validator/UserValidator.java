package pl.camp.it.forum.validator;

import pl.camp.it.forum.exceptions.UserValidationException;
import pl.camp.it.forum.model.User;

public class UserValidator {

    public static void validateName(String name){
        String regex = "^[A-Z]{1}[a-ząćęłńóśźż]+$";
        if(!name.matches(regex)){
            throw new UserValidationException();
        }


    }

    public static void validateSurname(String surname){
        String regex = "^[A-Z]{1}[a-za-ząćęłńóśźż]+(-[A-Z]{1}[a-za-ząćęłńóśźż]+)?$";
        if(!surname.matches(regex)){
            throw new UserValidationException();
        }
    }

    public static void validateLogin(String login) {
    String regex = "^[a-zA-Z0-9]{5,30}$";
    if(!login.matches(regex)){
        throw new UserValidationException();
    }
    }

    public static void validatePassword(String password){
    String regex = "^[a-zA-Z0-9]{5,30}$";
    if(!password.matches(regex)){
        throw new UserValidationException();
        }
    }

    public static void  validatePasswordsEquals(String password, String password2) {
        if(!password.equals(password2)){
            throw new UserValidationException();
        }
    }

    public static void validateRegistration(User user, String password2 ){
        validateName(user.getName());
        validateSurname(user.getSurname());
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validatePasswordsEquals(user.getPassword(), password2);
    }



}
