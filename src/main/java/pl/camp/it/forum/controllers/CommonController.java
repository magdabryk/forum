package pl.camp.it.forum.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.forum.services.ITitleService;
import pl.camp.it.forum.session.SessionObject;

@Controller
public class CommonController {
    @Resource
    SessionObject sessionObject;

    @Autowired
    ITitleService titleService;


    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String main() {
        return "redirect:/";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("titles",this.titleService.getTitle());
        model.addAttribute("sessionObject", this.sessionObject);
        return "main";
    }
}
