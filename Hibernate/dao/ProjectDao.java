package com.i2i.annotation.dao;

import com.i2i.annotation.common.CustomException;
import com.i2i.annotation.dao.BaseDao;
import com.i2i.annotation.model.EmployeeProject;
import com.i2i.annotation.model.Project;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
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
 * It will communicate with Project service and
 * it store the data to database
 * </p> 
 * @author Sivadharshini Mohan
 * @version 1.0
 */
public class ProjectDao {
    private static Logger logger = Logger.getLogger(ProjectDao.class);
    Session session = null;
    private static SessionFactory factory = BaseDao.getInstance();
    
    /**
     * <p>
     * Insert the project detail into database
     * </p> 
     * @param Employee object
     * @throws CustomException
     * @return {@link void} return nothing
     */
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
     
    /**
     * <p>
     * Retrive the all projects from database
     * </p> 
     * @throws CustomException
     * @return {@link List} 
     *
     */
    public List<Project> retriveProjects() throws CustomException {  
        try {
            session = factory.openSession();
            session.beginTransaction();

            return session.createQuery("from Project").list();
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
     * Retrive the project by id 
     * </p> 
     * @param project id
     * @throws CustomException
     * @return {@link Object} 
     *
     */
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

    /**
     * <p>
     * Update the project detail to database
     * </p>
     * @param project
     * @throws CustomerException
     * @return {@link void} return nothing 
     */
    public void updateProjectById(Project project) throws CustomException {
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
            logger.debug("Project updated Succesfully");
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

    /**
     * <p>
     * Delete the project
     * </p> 
     * @throws CustomException
     * @return {@link void} return nothing 
     *
     **/
    public void deleteProject(Project project) {
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
            logger.debug("project deleted Succesfully");
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
    public void assingEmployees(EmployeeProject employeeProject) throws CustomException {
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

    public List<EmployeeProject> retriveEmployeeProject() throws CustomException {
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            return session.createQuery("from EmployeeProject").list();
        } catch (Exception error) {
            error.printStackTrace();
            throw new CustomException(error.getMessage());
        }  finally {
            if(session != null) {
                 session.close();
             }
        }  
    }
}