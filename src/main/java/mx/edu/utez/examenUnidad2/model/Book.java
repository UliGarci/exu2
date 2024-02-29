package mx.edu.utez.examenUnidad2.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table( name = "books" ) @Data
public class Book {
    @Id @GeneratedValue( strategy = GenerationType.IDENTITY) @Column( name = "book_id")
    private Long id;

    @Column( nullable = false )
    private String title;

    @Column( nullable = false )
    private String author;

    @Column( nullable = false, name = "publication_date" )
    private Date publicationDate;

    @Column(name = "image")
    private String image;
}
