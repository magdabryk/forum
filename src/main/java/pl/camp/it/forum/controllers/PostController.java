package pl.camp.it.forum.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.forum.model.Post;
import pl.camp.it.forum.model.Title;
import pl.camp.it.forum.sequence.IPostIdSequence;
import pl.camp.it.forum.sequence.ITitleIdSequence;
import pl.camp.it.forum.services.IPostService;
import pl.camp.it.forum.services.ITitleService;
import pl.camp.it.forum.session.SessionObject;

import java.util.Optional;

@Controller
public class PostController {
    @Resource
    SessionObject sessionObject;

    @Autowired
    IPostService postService;
    @Autowired
    ITitleService titleService;
    @Autowired
    IPostIdSequence postIdSequence;
    @Autowired
    ITitleIdSequence titleIdSequence;


    @RequestMapping(path = "/post/show/{titleId}", method = RequestMethod.GET)
    public String showPost(@PathVariable int titleId, Model model) {
        Optional<Title> titleBox = this.titleService.getTitleById(titleId);
        if (titleBox.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("posts", this.postService.getPostByTitleId(titleId));
        model.addAttribute("titles", titleBox.get());
        model.addAttribute("sessionObject", this.sessionObject);
        return "postList";
    }

    @RequestMapping(path = "/title/add/", method = RequestMethod.GET)
    public String addTitle(Model model) {
        model.addAttribute("title", new Title());
        model.addAttribute("post", new Post());
        model.addAttribute("sessionObject", this.sessionObject);
        return "titleForm";
    }

    @RequestMapping(path = "/title/add/", method = RequestMethod.POST)
    public String addTitle1(@ModelAttribute Title title, @ModelAttribute Post post) {
        title.setId(this.titleIdSequence.getId());
        this.postService.addPost(post, title.getId(), this.sessionObject.getUser().getId());
        this.titleService.addTitle(title);
        return "redirect:/";
    }

    @RequestMapping(path = "/post/add/{titleId}", method = RequestMethod.GET)
    public String addPost(Model model, @PathVariable int titleId){
    Optional<Title> titleBox = this.titleService.getTitleById(titleId);
    model.addAttribute("sessionObject", this.sessionObject);
    model.addAttribute("post", new Post());
    if(titleBox.isEmpty()){
        return "redirect:/";
    }
    model.addAttribute("title", titleBox.get());
        return "postForm";
    }

    @RequestMapping(path = "/post/add/{titleId}", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Post post, @PathVariable int titleId){
        this.postService.addPost(post, titleId, this.sessionObject.getUser().getId());
        return "redirect:/";
    }
}
