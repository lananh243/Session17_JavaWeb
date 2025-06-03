package com.data.demo1.repository;

import com.data.demo1.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImp implements CustomerRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer);
        transaction.commit();
    }
}
