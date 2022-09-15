package com.i2i.annotation.dao;

import com.i2i.annotation.common.CustomException;
import com.i2i.annotation.dao.BaseDao;
import com.i2i.annotation.model.Employee;
import com.i2i.annotation.model.Role;

import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;  

public class RoleDao{
    private static SessionFactory factory = BaseDao.getInstance();
    Session session = null;    

    public void setDefaultRole(){  
        try {
            session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            Role role = new Role();
            List<Role> roles = role.getDefaultRoles();
            for(Role roleList : roles) {
                session.save(roleList);
            }
            transaction.commit();
            session.close();
        } catch(Exception exception) {
            exception.printStackTrace(); 
           
        }   
    }

    public Role retrieveRoleByName(String name) throws CustomException {
        try {
            session = factory.openSession(); 
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("name", name));
            Role role = (Role) criteria.uniqueResult();
            return role;
        } catch(Exception exception) {
            exception.printStackTrace(); 
            throw new CustomException(exception.getMessage()); 
        }   
    }    
        
}