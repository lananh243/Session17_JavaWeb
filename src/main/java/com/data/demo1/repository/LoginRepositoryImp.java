package com.data.demo1.repository;

import com.data.demo1.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LoginRepositoryImp implements LoginRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Customer findByCustomer(String username, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Customer where username = :username and password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        Customer customer = (Customer) query.uniqueResult();

        transaction.commit();

        return customer;

    }
}
