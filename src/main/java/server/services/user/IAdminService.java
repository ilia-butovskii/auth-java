package server.services.user;

import server.enteties.Admin;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
     public Admin createAdmin(String login, String password);

     public Admin findAdmin(String login, String password);

     public List<Admin> getAllAdmins();

     public Optional<Admin> getAdminById(Long id);

     public boolean isAdminExists(Long id);

     public void deleteAdmin(Long id);
}
