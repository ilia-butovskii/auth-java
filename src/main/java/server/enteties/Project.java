package server.enteties;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @Column(nullable = false)
    private Long Id;

    @Column(columnDefinition = "JSON", nullable = false)
    private String schema;

    @Column(length = 512, nullable = false)
    private String pubKey;

    @Column(length = 512, nullable = false)
    private String privKey;

    @Column(nullable = false)
    private Long adminId;

    @Column(length = 63, nullable = false)
    private String name;

    // Getters and setters
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getPubKey() {
        return this.pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getPrivKey() {
        return this.privKey;
    }

    public void setPrivKey(String privKey) {
        this.privKey = privKey;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}