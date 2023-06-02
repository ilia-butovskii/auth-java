package server.http.controllers.admin.users.dto;

import java.util.List;

public class GetUsersResponseDto {

    List<GetUserResponseDto> users;

    public GetUsersResponseDto() {}

    public void setUsers(List<GetUserResponseDto> users){
        this.users = users;
    }

    public List<GetUserResponseDto> getUsers(){
        return this.users;
    }
}

