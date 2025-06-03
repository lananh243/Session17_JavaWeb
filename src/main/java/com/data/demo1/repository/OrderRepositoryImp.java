package com.data.demo1.repository;

import com.data.demo1.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderRepositoryImp implements OrderRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
    }

    @Override
    public List<Order> findByCustomerId(Integer customerId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Order WHERE customerId = :customerId", Order.class)
                    .setParameter("customerId", customerId)
                    .getResultList();
        }
    }

    @Override
    public List<Order> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Order", Order.class).list();
        }
    }

    @Override
    public Order findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Order.class, id);
        }
    }
}
