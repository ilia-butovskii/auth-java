package server.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.enteties.Admin;
import server.repositories.AdminRepository;
import server.services.UserUtil;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private AdminRepository adminRepository;
    public Admin createAdmin(String login, String password){
        String passwordHash = UserUtil.calculateHash(password);
        Admin admin = new Admin(login, passwordHash);

        return this.adminRepository.save(admin);
    }

    public Admin findAdmin(String login, String password){
        String passwordHash = UserUtil.calculateHash(password);
        List<Admin> result = this.adminRepository.findByLoginAndPassword(login, passwordHash);

        if(result.size() == 0){
            return null;
        }

        return result.get(0);
    }

    public List<Admin> getAllAdmins(){
        return this.adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id){
        return this.adminRepository.findById(id);
    }

    public boolean isAdminExists(Long id) {
        return this.adminRepository.existsById(id);
    }

    public void deleteAdmin(Long id){
        this.adminRepository.deleteById(id);
    }
}
