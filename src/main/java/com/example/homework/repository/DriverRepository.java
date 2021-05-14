package com.example.homework.repository;

import com.example.homework.Model.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DriverRepository {
    SessionFactory sessionFactory;

    DriverRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
   public Driver addDriver(Driver driver) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(driver);
        transaction.commit();
        session.close();
        return driver;
    }
    @Transactional
    public List<Driver> getAllDrivers(){
        Session session=sessionFactory.openSession();
        NativeQuery query=session.createNativeQuery("select * from driver");
        query.addEntity(Driver.class);
        List<Driver> list=query.list();
        session.close();
        return list;
    }
    @Transactional
    public Driver getDriverById(int id){
        Session session= sessionFactory.openSession();
        NativeQuery query=session.createNativeQuery("select * from driver where id=:id");
        query.addEntity(Driver.class);
        query.setParameter("id",id);
        Driver driver= (Driver) query.getSingleResult();
        session.close();
        return driver;
    }
    @Transactional
    public void updateDriver(Driver newDriver){
        sessionFactory.getCurrentSession()
                .saveOrUpdate(newDriver);
    }
    @Transactional
    public void deleteDriver(int id){
        Driver driver= getDriverById(id);
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
            session.remove(driver);
            transaction.commit();
            session.close();
    }

}
