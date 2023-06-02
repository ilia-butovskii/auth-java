package server.services.auth;

public class JwtTokens {
    public JwtTokens(String access, String refresh){
        this.access = access;
        this.refresh = refresh;
    }
    public String refresh;
    public String access;
}
