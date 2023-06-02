package server.http.controllers.admin.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.enteties.Admin;
import server.http.controllers.admin.auth.dto.LoginBodyDto;
import server.http.errors.NotFoundHttpException;
import server.services.adminAuth.IAdminAuthService;
import server.services.auth.JwtTokens;
import server.services.user.IAdminService;
import java.util.Objects;

@RestController
@RequestMapping("/admin/auth")

public class AuthController {
    @Autowired
    IAdminService adminService;
    @Autowired
    IAdminAuthService adminAuthService;


    @PostMapping(path="login", consumes="application/json", produces="application/json")
    public ResponseEntity login(@Validated @RequestBody LoginBodyDto body){
        Admin admin = adminService.findAdmin(body.login, body.password);

        if(Objects.isNull(admin)){
            return new NotFoundHttpException("Wrong login or password");
        }

        JwtTokens jwtTokens = adminAuthService.getTokens(admin);

        return ResponseEntity.ok().body(jwtTokens);
    }
}
