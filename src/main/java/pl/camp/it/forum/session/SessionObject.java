package pl.camp.it.forum.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.forum.model.User;

@Component
@SessionScope
public class SessionObject {

    private User user = null;

    public boolean isLogged() {
        return this.user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAdmin(){
        return this.user != null && this.user.getRole() == User.Role.ADMIN;
    }
    public  boolean isModerator(){
        return  (this.user != null && this.user.getRole() == User.Role.MODERATOR) || isAdmin();
    }
}
