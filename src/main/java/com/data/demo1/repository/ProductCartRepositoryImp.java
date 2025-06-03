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
public class ProductCartRepositoryImp implements ProductCartRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addProduct(int customerId, int productId, int quantity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            ProductCart productCart = session.createQuery(
                            "FROM ProductCart WHERE customerId = :customerId AND productId = :productId", ProductCart.class)
                    .setParameter("customerId", customerId)
                    .setParameter("productId", productId)
                    .uniqueResult();

            if (productCart != null) {
                // Nếu đã tồn tại, cập nhật số lượng
                productCart.setQuantity(productCart.getQuantity() + quantity);
                session.update(productCart);
            } else {
                // Nếu chưa, thêm mới
                ProductCart newCart = new ProductCart();
                newCart.setCustomerId(customerId);
                newCart.setProductId(productId);
                newCart.setQuantity(quantity);
                session.save(newCart);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Bạn có thể thay bằng logging framework
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<ProductCart> findAllByCustomerId(int customerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM ProductCart WHERE customerId = :customerId");
        query.setParameter("customerId", customerId);
        List<ProductCart> productCartList = query.getResultList();
        transaction.commit();
        return productCartList;
    }

    @Override
    public void clearCartByCustomerId(int customerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE FROM ProductCart WHERE customerId = :customerId");
            query.setParameter("customerId", customerId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
