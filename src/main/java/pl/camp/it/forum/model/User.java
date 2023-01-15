package pl.camp.it.forum.model;


public class User {

    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;

    private Role role;

    public User(int id, String login, String password, String name, String surname, Role role) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static class UserBuilder{
        User user = new User();

        public UserBuilder id(int id){
            this.user.setId(id);
            return this;
        }
        public UserBuilder name(String name){
            this.user.setName(name);
            return this;
        }
        public UserBuilder surname(String surname){
            this.user.setSurname(surname);
            return this;
        }
        public UserBuilder login(String login){
            this.user.setLogin(login);
            return this;
        }
        public UserBuilder password(String password){
            this.user.setPassword(password);
            return this;
        }
        public UserBuilder role(Role role){
            this.user.setRole(role);
            return this;
        }

        public User build(){
            return this.user;
        }

        public UserBuilder clone(User user){
            id(user.getId())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .login(user.getLogin())
                    .password(user.getPassword())
                    .role(user.getRole());
            return this;
        }

    }

      public enum Role {
        USER,
        MODERATOR,
        ADMIN

    }
}
