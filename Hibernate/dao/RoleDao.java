import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class RoleDao extends BaseDao {
    private Connection connection = mysqlConnection();
    private static SessionFactory factory;
    
    public void setDefaultRole() {
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Role role = new Role();
        List<Role> roles = role.getDefaultRoles();
        for(Role roleList : roles) {
            session.save(roleList);
        }
        transaction.commit();
        session.close();
    }
    public  void insertRoleName(List<Role> employeeRole) {
    
    }

    public int retriveRoleIdByName(String name) throws CustomException {
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Role role = new Role();
        return role.getId();
       
        }
        
}