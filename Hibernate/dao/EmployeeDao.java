import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * <p>
 * It will communicate with Employee service and
 * it store the data to database
 * </p> 
 * @author Sivadharshini Mohan
 * @version 1.0
 */
public class EmployeeDao {
    //private Connection connection = mysqlConnection();
    private PreparedStatement preparedStatement;
    private static SessionFactory factory;

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
            factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            int employeeId = (Integer)session.save(employee);
            transaction.commit();
            System.out.println("Employee added Succesfully");
            session.close();
            return employeeId;
        } catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
        }
       return 0;
    }
    
    public List<Employee> retriveEmployeeByRole(String role) {
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        return session.createQuery("from Employee where role = " + role).list();    
                                    
    }
   

}

