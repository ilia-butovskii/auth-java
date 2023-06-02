package server.components.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class JwtComponent implements IJwtComponent {
    private final TreeMap<String, TreeMap<EJwtConfigProperties, String>> conatiner;

    public JwtComponent(@Value("${jwt.key.public}") final String adminPublicKey,
                        @Value("${jwt.key.private}") final String adminPrivateKey,
                        @Value("${jwt.key.adminProject}") final String adminProjectName,
                        @Value("${jwt.access.lifetime}") final String adminAccessLifetime,
                        @Value("${jwt.refresh.lifetime}") final String adminRefreshLifetime
    ){

        this.conatiner = new TreeMap<String, TreeMap<EJwtConfigProperties, String>>();

        this.addProject(adminProjectName, adminPublicKey, adminPrivateKey, Integer.parseInt(adminAccessLifetime), Integer.parseInt(adminRefreshLifetime));
    }

    public void addProject(String projectName, String publicKey, String privateKey, int accessLifetime, int refreshLifetime){
        boolean isProjectExists = conatiner.containsKey(projectName);

        if(isProjectExists){
            throw new RuntimeException("Keys for project with name " + projectName + "already added");
        }

        TreeMap<EJwtConfigProperties, String> config = new TreeMap<EJwtConfigProperties, String>();

        config.put(EJwtConfigProperties.PRIVATE_KEY, privateKey);
        config.put(EJwtConfigProperties.PUBLIC_KEY, publicKey);
        config.put(EJwtConfigProperties.PROJECT_NAME, projectName);
        config.put(EJwtConfigProperties.REFRESH_LIFETIME, Integer.toString(refreshLifetime));
        config.put(EJwtConfigProperties.ACCESS_LIFETIME, Integer.toString(accessLifetime));

        conatiner.put(projectName, config);
    }

    @Override
    public IJwt factory(String projectName) {
        TreeMap<EJwtConfigProperties, String> config = this.conatiner.get(projectName);

        if(Objects.isNull(config)){
            throw new RuntimeException("Project " + projectName + " not found");
        }

        return new Jwt(config);
    }
}

class Jwt implements IJwt{
    TreeMap<EJwtConfigProperties, String> config;
    public Jwt(TreeMap<EJwtConfigProperties, String> config){
        this.config = config;
    }

    public String generateAccess(Map<String, String> payload){
        int accessLifetime = Integer.parseInt(this.config.get(EJwtConfigProperties.ACCESS_LIFETIME));
        String projectName = this.config.get(EJwtConfigProperties.PROJECT_NAME);
        PrivateKey privateKey = this.getPrivateKey();
        Instant now = Instant.now();

        JwtBuilder jwtBuilder = Jwts.builder()
                .claim("type", "access")
                .claim("project", projectName);

        for (Map.Entry<String,String> entry : payload.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }

        jwtBuilder.setId(UUID.randomUUID().toString())
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plus(accessLifetime, ChronoUnit.MINUTES)))
        .signWith(SignatureAlgorithm.RS256, privateKey);

        return jwtBuilder.compact();
    }

    public Claims verifyToken(String token){
        PublicKey publicKey = this.getPublicKey();

        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
    }

    public String generateRefresh(Map<String, String> payload){
        int accessLifetime = Integer.parseInt(this.config.get(EJwtConfigProperties.REFRESH_LIFETIME));
        PrivateKey privateKey = this.getPrivateKey();
        Instant now = Instant.now();

        JwtBuilder jwtBuilder = Jwts.builder()
                .claim("type", "refresh");

        for (Map.Entry<String,String> entry : payload.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }

        jwtBuilder.setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(accessLifetime, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.RS256, privateKey);

        return jwtBuilder.compact();
    }

    private PrivateKey getPrivateKey(){
        try {
            String privateKeyString = this.config.get(EJwtConfigProperties.PRIVATE_KEY);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePrivate(keySpec);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private PublicKey getPublicKey() {
        try {
            String publicKeyString = this.config.get(EJwtConfigProperties.PUBLIC_KEY);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(keySpec);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
