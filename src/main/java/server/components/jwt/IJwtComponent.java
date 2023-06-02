package server.components.jwt;

public interface IJwtComponent {
    public void addProject(String projectName, String publicKey, String privateKey, int accessLifetime, int refreshLifetime);

    public IJwt factory(String projectName);
}
