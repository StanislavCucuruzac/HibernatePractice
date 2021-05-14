package com.example.homework.repository;

import com.example.homework.Model.Employee;
import com.example.homework.Model.Wheel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class WheelReposiotry {
    SessionFactory sessionFactory;

    WheelReposiotry(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    public Wheel createWheel(Wheel wheel){
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.save(wheel);
        transaction.commit();
        session.close();
        return wheel;
    }
    @Transactional
    public List<Wheel> getAllWhell(){
        Session session=sessionFactory.openSession();
        NativeQuery query= session.createNativeQuery("select * from wheel");
        query.addEntity(Wheel.class);
        List<Wheel> list=query.list();
        session.close();
        return list;
    }
    public Wheel getById(int id){
        Session session = sessionFactory.openSession();
        NativeQuery query = session.createNativeQuery("select * from wheel where id=:id");
        query.addEntity(Wheel.class);
        query.setParameter("id", id);
        Wheel wheel = (Wheel) query.getSingleResult();
        session.close();
        return wheel;
    }
}
