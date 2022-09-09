import java.sql.PreparedStatement;
import org.apache.log4j.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao extends BaseDao {
    private Connection connection = mysqlConnection();
    private static Logger logger = Logger.getLogger(ProjectDao.class);
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = null;
    
    public void insertProject(Project project) throws CustomException {
         try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();
            logger.debug("Project added Succesfully");
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException);
            logger.error(hibernateException.getMessage());
            hibernateException.printStackTrace();
        } finally {
            if(session != null) {
                 session.close();
             }
        }
    }
     
    public Project retrieveProjectById(int id) throws CustomException {

        try {
            session = factory.openSession(); 
            session.beginTransaction();
            return (Project) session.createQuery("from Project where id = :id")
            .setParameter("id", id)
            .uniqueResult();       
            
        } catch (Exception error) {
            error.printStackTrace();
            throw new CustomException(error.getMessage());
        } finally {
            if(session != null) {
                 session.close();
             }
        }
    }

    public void assingemployees(EmployeeProject employeeProject) throws CustomException {
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(employeeProject);
            transaction.commit();
            logger.debug("Employee assigned Succesfully");
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException);
            logger.error(hibernateException.getMessage());
            hibernateException.printStackTrace();
        } finally {
            if(session != null) {
                 session.close();
             }
        }
    }

    

}