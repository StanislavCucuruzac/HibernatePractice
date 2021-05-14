package com.example.homework.repository;

import com.example.homework.Model.Employee;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeRepository {
    SessionFactory sessionFactory;

    EmployeeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
        return employee;
    }

    @Transactional
    public List<Employee> getAll() {
        Session session = sessionFactory.openSession();
        NativeQuery query = session.createNativeQuery("select * from employee");
        query.addEntity(Employee.class);
        List<Employee> list = query.list();
        session.close();
        return list;
    }

    @Transactional
    public Employee getById(int id) {
        Session session = sessionFactory.openSession();
        NativeQuery query = session.createNativeQuery("select * from employee where id=:id");
        query.addEntity(Employee.class);
        query.setParameter("id", id);
        Employee employee = (Employee) query.getSingleResult();
        session.close();
        return employee;
    }

    @Transactional
    public void updateEmployee(Employee newEmployee, int id) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(newEmployee);
    }

    @Transactional
    public void deleteEmployeeById(int id) {
        Employee employee = getById(id);
        sessionFactory.getCurrentSession()
                .remove(employee);
    }


}
