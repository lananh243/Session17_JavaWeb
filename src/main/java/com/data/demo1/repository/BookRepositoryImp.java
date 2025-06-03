package com.data.demo1.repository;


import com.data.demo1.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImp implements BookRepository {
    private SessionFactory sessionFactory;

    public BookRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Book> getAll() {
        Session session = sessionFactory.openSession();

        // b2: tạo query dùng HQL
        Query<Book> query = session.createQuery("FROM Book", Book.class);
        // b3: thực thi query
        List<Book> books = query.getResultList();
        //use hibernate to get data
        return books;
    }

    // khi thay đổi dữ liệu cần dùng transaction
    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        // b2: tạo query dùng HQL
        Query query = session.createQuery("DELETE FROM Book WHERE id = " + id);
        // b3: thực thi query
        query.executeUpdate();

        transaction.commit();
    }
}
