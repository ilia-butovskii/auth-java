package server.services.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import server.components.jwt.IJwtComponent;
import server.enteties.Project;
import server.repositories.ProjectRepository;

@Service
public class ProjectService implements IProjectService{

//    @Autowired
//    private final IJwtComponent jwtComponent;
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    @Autowired
//    ProjectRepository projectRepository;
//
//    public Project createProject(String name, Long adminId) {
//        boolean isProjectExists = projectRepository.existsByName(name);
//
//        if(isProjectExists){
//            return null;
//        }
//
//        Project project = new Project();
//        project.setName(name);
//
//
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//
//    }
}
