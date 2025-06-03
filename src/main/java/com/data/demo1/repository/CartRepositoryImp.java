package com.data.demo1.repository;

import com.data.demo1.entity.ProductCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CartRepositoryImp implements CartRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProductCart> findAll() {
        Session session = sessionFactory.openSession();
        Query<ProductCart> query = session.createQuery("FROM ProductCart", ProductCart.class);

        List<ProductCart> cart = query.getResultList();
        return cart;
    }

    @Override
    public void delete(int cartId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("DELETE FROM ProductCart WHERE id=:cartId");
            query.setParameter("cartId", cartId);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateQuantityById(int id, int quantity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            ProductCart existingCart = session.get(ProductCart.class, id);
            if (existingCart != null) {
                existingCart.setQuantity(quantity);
                session.update(existingCart);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public ProductCart findCartById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ProductCart.class, id);
        }
    }

}
