package server.http.controllers.admin.users.dto;

import server.enteties.Admin;

import java.util.List;

public interface IUsersDtoMapper {
    public GetUserResponseDto userToDto(Admin admin);

    public GetUsersResponseDto usersToDto(List<Admin> admins);
}
