package com.example.homework.repository;

import com.example.homework.Model.Car;
import com.example.homework.Model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CarRepository {
    SessionFactory sessionFactory;
    CarRepository(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    @Transactional
    public Car createCar(Car car){
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
        return car;
    }
    @Transactional
    public List<Car> getAllCars(){
        Session session=sessionFactory.openSession();
        NativeQuery query= session.createNativeQuery("select * from car");
        query.addEntity(Car.class);
        List<Car> list=query.list();
        session.close();
        return list;
    }
    @Transactional
    public Car getCarById(int id){
        Session session=sessionFactory.openSession();
        NativeQuery query= session.createNativeQuery("select * from car where id=:id");
        query.addEntity(Car.class);
        query.setParameter("id",id);
        Car car= (Car) query.getSingleResult();
        session.close();
        return car;
    }
    @Transactional
    public Car getCarByName(String name){
        Session session=sessionFactory.openSession();
        NativeQuery query= session.createNativeQuery("select * from car where name=:name");
        query.addEntity(Car.class);
        query.setParameter("name", name);
        Car car= (Car) query.getSingleResult();
        session.close();
        return car;
    }
    @Transactional
    public void updateCar(Car car, int id){
                sessionFactory.getCurrentSession()
                .saveOrUpdate(car);
    }
    @Transactional
    public void deleteCarById(int id) {
        Car car = getCarById(id);
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
                session.remove(car);
                transaction.commit();
                session.close();
    }
}
