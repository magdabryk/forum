package pl.camp.it.forum.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.forum.services.IUserService;
import pl.camp.it.forum.session.SessionObject;

@Controller
public class UserController {
    @Resource
    SessionObject sessionObject;
    @Autowired
    IUserService userService;

    @RequestMapping(path="/users", method = RequestMethod.GET)
    public String showUsers(Model model){
        model.addAttribute("users", this.userService.getUserList());
        model.addAttribute("sessionObject", this.sessionObject);
        return "userList";
    }
}
