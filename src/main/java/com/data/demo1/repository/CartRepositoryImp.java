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
    public void update(ProductCart productCart) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "UPDATE ProductCart p SET p.productId = :productId, p.quantity = :quantity WHERE p.id = :id");
            query.setParameter("productId", productCart.getProductId());
            query.setParameter("quantity", productCart.getQuantity()); // giả sử có quantity
            query.setParameter("id", productCart.getId());

            int updated = query.executeUpdate();

            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int productId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("DELETE FROM ProductCart WHERE productId=:productId");
            query.setParameter("productId", productId);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
