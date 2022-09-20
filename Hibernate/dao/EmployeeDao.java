package com.i2i.annotation.dao;

import com.i2i.annotation.common.CustomException;
import com.i2i.annotation.dao.BaseDao;
import com.i2i.annotation.model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * <p>
 * It will communicate with Employee service and
 * it store the data to database
 * </p> 
 * @author Sivadharshini Mohan
 * @version 1.0
 */
public class EmployeeDao {
    private static Logger logger = Logger.getLogger(EmployeeDao.class);
    Session session = null;
    private static SessionFactory factory = BaseDao.getInstance();

    /**
     * <p>
     * Insert the employee detail into database
     * </p> 
     * @param Employee object
     * @throws CustomException
     * @return {@link int}
     *
     */
    public int insertEmployee(Employee employee) throws CustomException {
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

    /**
     * <p>
     * Retrive the all employee data from database
     * </p> 
     * @param employeRole
     * @throws CustomException
     * @return {@link List} 
     *
     */
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
    
    /**
     * <p>
     * Retrive the particular employee data from database
     * </p> 
     * @param employeId
     * @throws CustomerException
     * @return {@link object} 
     *
     **/
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
    
    /**
     * <p>
     * Update the employee data to database
     * </p>
     * @param employee 
     * @throws CustomerException
     * @return {@link void} return nothing 
     *
     */
    public void updateEmployeeById(Employee employee) throws CustomException {
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            logger.debug("Employee Updated Succesfully");
        } catch(Exception e) {
            throw new CustomException(e.getMessage());
        } finally {
            if(session != null) {
                 session.close();
            }
        }      
    }
}

