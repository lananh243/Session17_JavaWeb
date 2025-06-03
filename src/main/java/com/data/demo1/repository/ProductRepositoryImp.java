package com.data.demo1.repository;

import com.data.demo1.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductRepositoryImp implements ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll(int pageNumber, int pageSize) {
        Session session = sessionFactory.openSession();
        Query<Product> query = session.createQuery("from Product", Product.class);


        int firstResult = (pageNumber - 1) * pageSize;
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

        List<Product> products = query.getResultList();
        return products;
    }

    @Override
    public Product findById(int id) {
        Session session = sessionFactory.openSession();
        Product product = (Product) session.get(Product.class, id);
        return product;
    }
}
