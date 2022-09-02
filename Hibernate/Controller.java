import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

public class Controller {

    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();
            Employee employee = new Employee("shivu", 5);
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            session.close();
        } catch (HibernateException hibernateException) {
            System.out.println(hibernateException);
            System.out.println(hibernateException.getMessage());
            hibernateException.printStackTrace();
        }
    }
}