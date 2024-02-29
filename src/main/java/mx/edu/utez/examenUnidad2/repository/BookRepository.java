package mx.edu.utez.examenUnidad2.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.examenUnidad2.model.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
    
    Optional<Book> findByTitle(String title);
    
    List<Book> findAllByOrderByAuthorAsc();

    List<Book> findAllByOrderByPublicationDateAsc();

    List<Book> findAllByOrderByImageAsc();
}
