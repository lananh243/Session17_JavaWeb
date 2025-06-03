package com.data.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_book")
public class Book {
    @Id // đánh dấu là khoá chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự tăng
    private int id;
    @Column(name = "title", length = 100, unique = true, nullable = false)
    private String bookTitle;

    private String author;

    private int price;

    @Column(columnDefinition = "bigint")
    private int price2;

    // mã sách
    private String ISBN;

    private String image;

    // new column: String: varchar(255)
    // text: độ dài lớn hơn

//    @Column(columnDefinition = "text DEFAULT 'ga.png'")
//    // image2 text (not image varchar(255))
    private String image2;

//    @Column(columnDefinition = "DATE DEFAULT NOW()")
//    private Date createdDate;
}
