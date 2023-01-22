package pl.camp.it.forum.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.forum.exceptions.LoginExsistException;
import pl.camp.it.forum.exceptions.UserValidationException;
import pl.camp.it.forum.model.User;
import pl.camp.it.forum.services.IAuthenticatorService;
import pl.camp.it.forum.services.IUserService;
import pl.camp.it.forum.session.SessionObject;
import pl.camp.it.forum.validator.UserValidator;

@Controller
public class AuthenticationController {

    @Resource
    SessionObject sessionObject;
    @Autowired
    IAuthenticatorService authenticatorService;
    @Autowired
    IUserService userService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        try{
            UserValidator.validateLogin(login);
            UserValidator.validatePassword(password);
        }catch (UserValidationException e){
            e.printStackTrace();
            return "redirect:/login";
        }
        this.authenticatorService.authenticate(login, password);
        if (!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        return "redirect:/main";
    }

    @RequestMapping(path="/logout", method = RequestMethod.GET)
    public String logout(){
        this.authenticatorService.logout();
        return "redirect:/main";
    }

    @RequestMapping(path="/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("sessionObject", this.sessionObject);
        model.addAttribute("user", new User());
        return "userForm";
    }

    @RequestMapping(path="/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, @RequestParam String password2){
        try{
            UserValidator.validateRegistration(user, password2);
            this.userService.addUser(user);
        }catch(UserValidationException e){
            e.printStackTrace();
            return "redirect:/register";
        }catch (LoginExsistException e){
            e.printStackTrace();
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
