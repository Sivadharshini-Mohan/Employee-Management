package com.i2i.annotation.dao;

import org.hibernate.cfg.Configuration;      
import org.hibernate.SessionFactory;

/**
 * <p>
 * 
 * 
 * 
 * </p> 
 * @author Sivadharshini Mohan
 * @version 1.0
 **/
public class BaseDao {
    protected static SessionFactory factory = null; 

    private BaseDao() { 
 
    }

    public static SessionFactory getInstance() {

        if (factory == null) {
            factory = new Configuration().configure().buildSessionFactory();
        }

        return factory;
    }
}