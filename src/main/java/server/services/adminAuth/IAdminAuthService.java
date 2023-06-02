package server.services.adminAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.components.jwt.IJwtComponent;
import server.enteties.Admin;
import server.services.auth.JwtTokens;

@Service
public interface IAdminAuthService {
    public JwtTokens getTokens(Admin admin);
}
