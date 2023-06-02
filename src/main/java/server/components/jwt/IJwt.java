package server.components.jwt;

import io.jsonwebtoken.Claims;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public interface IJwt {

    public String generateRefresh(Map<String, String> payload);

    public String generateAccess(Map<String, String> payload);

    public Claims verifyToken(String token);
}
