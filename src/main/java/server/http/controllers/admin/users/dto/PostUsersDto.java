package server.http.controllers.admin.users.dto;

import javax.validation.constraints.NotNull;

public class PostUsersDto {
    @NotNull
    public String login;

    @NotNull
    public String password;
}
