package mx.edu.utez.examenUnidad2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mx.edu.utez.examenUnidad2.service.BookService;
import mx.edu.utez.examenUnidad2.utils.CustomResponse;
import mx.edu.utez.examenUnidad2.model.Book;

@RestController @RequestMapping("/api/books") @CrossOrigin(origins = {"*"})
public class BookController {
    
    @Autowired
    private BookService service;

    @GetMapping("/")
    public CustomResponse<List<Book>> getAll(){
        return this.service.getAll();
    }

    @GetMapping("/author")
    public CustomResponse<List<Book>> getAllByAuthor(){
        return this.service.orderByAuthor();
    }

    @GetMapping("/publicationDate")
    public CustomResponse<List<Book>> getAllByPublicationDate(){
        return this.service.orderByPublicationDate();
    }

    @GetMapping("/image")
    public CustomResponse<List<Book>> getAllByImage(){
        return this.service.orderByImage();
    }

    @PostMapping("/")
    public CustomResponse<Book> insert(@RequestBody Book book){
        return this.service.insert(book);
    }

    @PutMapping("/{id}")
    public CustomResponse<Book> update(@PathVariable("id") Long id,  @RequestBody Book book){
        return this.service.update(id, book);
    }

    @DeleteMapping("/{id}")
    public CustomResponse<String> delete(@PathVariable("id") Long id){
        return this.service.delete(id);
    }
}
