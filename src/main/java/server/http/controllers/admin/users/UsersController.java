package server.http.controllers.admin.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.annotations.Context;
import server.enteties.Admin;
import server.http.HttpContext;
import server.http.controllers.admin.users.dto.GetUserResponseDto;
import server.http.controllers.admin.users.dto.GetUsersResponseDto;
import server.http.controllers.admin.users.dto.IUsersDtoMapper;
import server.http.controllers.admin.users.dto.PostUsersDto;
import server.http.errors.ForbiddenHttpException;
import server.http.errors.NotFoundHttpException;
import server.services.user.IAdminService;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class UsersController {
    @Autowired
    IAdminService adminService;

    @Autowired
    IUsersDtoMapper usersDtoMapper;

    @GetMapping(path="", produces="application/json")
    public ResponseEntity<GetUsersResponseDto> getUsers() {
        List<Admin> admins = this.adminService.getAllAdmins();

        GetUsersResponseDto responsePayload = usersDtoMapper.usersToDto(admins);

        return ResponseEntity.ok().body(responsePayload);
    }

    @PostMapping(path="", consumes="application/json", produces="application/json")
    public ResponseEntity postUsers(@Validated @RequestBody PostUsersDto body, @Context HttpContext ctx) {
        Admin admin = ctx.admin;

        if(!admin.getIsSuper()){
            return new ForbiddenHttpException("Admin is not super");
        }

        Admin createdAdmin = adminService.createAdmin(body.login, body.password);
        GetUserResponseDto responsePayload = usersDtoMapper.userToDto(createdAdmin);

        return ResponseEntity.ok().body(responsePayload);
    }

    @DeleteMapping(path="/{id}", produces="application/json")
    public ResponseEntity deleteUser(@PathVariable Long id, @Context HttpContext ctx) {
        Admin admin = ctx.admin;

        if(!admin.getIsSuper()){
            return new ForbiddenHttpException("Admin is not super");
        }

        if(!adminService.isAdminExists(id)){
            return new NotFoundHttpException("Admin not found");
        }

        adminService.deleteAdmin(id);

        return ResponseEntity.ok().build();
    }
}
