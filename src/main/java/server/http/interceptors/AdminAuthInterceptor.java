package server.http.interceptors;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import server.components.jwt.IJwt;
import server.components.jwt.IJwtComponent;
import server.enteties.Admin;
import server.http.HttpContext;
import server.http.errors.ErrorResponse;
import server.services.user.IAdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AdminAuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    IJwtComponent jwtComponent;

    @Autowired
    Environment env;

    @Autowired
    IAdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authHeader = request.getHeader("Authorization");
        IJwt jwt = jwtComponent.factory(env.getProperty("jwt.key.adminProject"));

        try {
            HttpContext context = (HttpContext) request.getAttribute("context");

            Claims tokenPayload =  jwt.verifyToken(authHeader);
            Long adminId = Long.parseLong((String) tokenPayload.get("adminId"));
            Optional<Admin> admin = this.adminService.getAdminById(adminId);

            if(!admin.isPresent()){
                throw new Exception("Admin not found");
            }

            context.admin = admin.get();

            return true;
        }
        catch (Exception e){
            InterceptorUtils.sendError(response, new ErrorResponse("Invalid token: " + e.getMessage()), HttpStatus.UNAUTHORIZED);
            return false;
        }
    }
}
