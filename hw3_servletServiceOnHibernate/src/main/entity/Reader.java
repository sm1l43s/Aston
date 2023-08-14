package main.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "readers")
public class Reader extends BaseEntity {

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String address;

    @ManyToMany
    List<Book> books;



}
