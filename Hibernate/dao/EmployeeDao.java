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

/**
 * <p>
 * It will communicate with Employee service and
 * it store the data to database
 * </p> 
 * @author Sivadharshini Mohan
 * @version 1.0
 */
public class EmployeeDao extends BaseDao {
    private static Logger logger = Logger.getLogger(EmployeeDao.class);
    private Connection connection = mysqlConnection();
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = null;
    private Employee employee = new Employee();

    /**
     * <p>
     * Insert the employee data in to database
     * </p> 
     * @param Employee object
     * @throws CustomException
     * @return {@link int}
     */
    public int insertEmployee(Employee employee) {

        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            int employeeId = (Integer)session.save(employee);
            transaction.commit();
            logger.debug("Employee added Succesfully");
            return employeeId;
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException);
            logger.error(hibernateException.getMessage());
            hibernateException.printStackTrace();
        } finally {
            if(session != null) {
                 session.close();
             }
        }
       return 0;
    }

    public List<Employee> retriveEmployees() throws CustomException {  
        try {
            session = factory.openSession();
            session.beginTransaction();
            return session.createQuery("from Employee").list();
        } catch (Exception error) {
            error.printStackTrace();
            throw new CustomException(error.getMessage());
        }  finally {
            if(session != null) {
                 session.close();
             }
        }  
    }

    public Employee retrieveEmployeeById(int employeeId) throws CustomException {
        try {
            session = factory.openSession(); 
            session.beginTransaction();
            return (Employee) session.createQuery("from Employee where id = :employeeId")
            .setParameter("employeeId", employeeId)
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
    
    public void updateEmployeeById(Employee employee) throws CustomException {
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            logger.debug("Employee Updtaed Succesfully");
        } catch(Exception e) {
            throw new CustomException(e.getMessage());
        } finally {
            if(session != null) {
                 session.close();
             }
        }      
    }
  
    public void deleteEmployee(Employee employee) throws CustomException {
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            logger.debug("Employee Updtaed Succesfully");
        } catch(Exception e) {
            throw new CustomException(e.getMessage());
        } finally {
            if(session != null) {
                 session.close();
             }
        }      
    }
}

