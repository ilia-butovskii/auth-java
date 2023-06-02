package server.services.adminAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import server.components.jwt.IJwt;
import server.components.jwt.IJwtComponent;
import server.enteties.Admin;
import server.services.auth.JwtTokens;

import java.util.TreeMap;

@Service
public class AdminAuthService implements IAdminAuthService{
    @Autowired
    private final IJwtComponent jwtComponent;

    private final IJwt jwt;

    @Autowired
    public AdminAuthService(@Value("${jwt.key.adminProject}") final String adminProjectName, IJwtComponent jwtComponent){
        this.jwtComponent = jwtComponent;

        this.jwt = this.jwtComponent.factory(adminProjectName);
    }

    public JwtTokens getTokens(Admin admin) {
        TreeMap<String, String> payload = new TreeMap<String, String>();

        payload.put("adminId", Long.toString(admin.getId()));
        payload.put("login", admin.getLogin());

        String accessToken = jwt.generateAccess(payload);
        String refreshToken = jwt.generateRefresh(payload);

        return new JwtTokens(accessToken, refreshToken);
    }
}
