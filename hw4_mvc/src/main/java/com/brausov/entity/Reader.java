package com.brausov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
