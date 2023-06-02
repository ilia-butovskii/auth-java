package server.http.controllers.admin.users.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetUserResponseDto {
    private Long id;

    private String login;
    private String password = "********";
    private boolean isSuper;

    public GetUserResponseDto() {}

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return login;
    }

    public void setIsSuper(boolean isSuper){
        this.isSuper = isSuper;
    }

    public boolean getIsSuper(){
        return this.isSuper;
    }

}