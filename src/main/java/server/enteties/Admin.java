package server.enteties;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;

    private Boolean isSuper = false;

    public Admin(String login, String password){
        this.login = login;
        this.password = password;
    }

    public Admin(){

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword(String password){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsSuper(Boolean value) {
        this.isSuper = value;
    }

    public Boolean getIsSuper(){
        return this.isSuper;
    }
}
