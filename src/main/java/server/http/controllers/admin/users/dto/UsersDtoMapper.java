package server.http.controllers.admin.users.dto;

import org.springframework.stereotype.Component;
import server.enteties.Admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UsersDtoMapper implements IUsersDtoMapper {
    public GetUserResponseDto userToDto(Admin admin){
        GetUserResponseDto getUserResponseDto = new GetUserResponseDto();

        getUserResponseDto.setId(admin.getId());
        getUserResponseDto.setLogin(admin.getLogin());
        getUserResponseDto.setIsSuper(admin.getIsSuper());

        return getUserResponseDto;
    }

    public GetUsersResponseDto usersToDto(List<Admin> admins){
        GetUsersResponseDto getUsersResponseDto = new GetUsersResponseDto();
        List<GetUserResponseDto> dtoList = new ArrayList<GetUserResponseDto>();

        for (Admin admin : admins) {
            GetUserResponseDto getUserResponseDto = this.userToDto(admin);

            dtoList.add(getUserResponseDto);
        }

        getUsersResponseDto.setUsers(dtoList);

        return getUsersResponseDto;
    }
}
