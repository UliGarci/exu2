package mx.edu.utez.examenUnidad2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import mx.edu.utez.examenUnidad2.model.Book;
import mx.edu.utez.examenUnidad2.repository.BookRepository;
import mx.edu.utez.examenUnidad2.utils.CustomResponse;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public CustomResponse<List<Book>> getAll() {
        try {
            List<Book> books = repository.findAll();
            if(books.isEmpty()){
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"No se encontraron registros en la base de datos");
            }
            
            return new CustomResponse<>(books, false, HttpStatus.OK.value(),"Lista de libros encontrada");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
        }
    }

    public CustomResponse<List<Book>> orderByAuthor(){
        try{
            List<Book> books = repository.findAllByOrderByAuthorAsc();
            if(books.isEmpty()){
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"No se encontraron registros");
            }
            return new CustomResponse<>(books, false, HttpStatus.OK.value(),"Lista de libros encontrada");
        }catch(Exception e){
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Server Error");
        }
    }

    public CustomResponse<List<Book>> orderByPublicationDate(){
        try{
            List<Book> books = repository.findAllByOrderByPublicationDateAsc();
            if(books.isEmpty()){
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"No hay libros");
            }
            return new CustomResponse<>(books, false, HttpStatus.OK.value(),"Success");
        }catch(Exception e){
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error en el servidor");
        }
    }

    public CustomResponse<List<Book>> orderByImage(){
        try {
            List<Book> books = repository.findAllByOrderByImageAsc();
            if(books.isEmpty()){
                return new CustomResponse<>(null,true,HttpStatus.NOT_FOUND.value(), "No hay libros con imagenes");
            }
            return new CustomResponse<>(books,false,HttpStatus.OK.value(),"Success");
        }catch (Exception e){
            return new CustomResponse<>(null,true,HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error en el servidor");
        }
    }




    

    public CustomResponse<Book> insert (Book book){
        try{
            if(repository.findByTitle(book.getTitle()).isPresent()){
                return new CustomResponse<>(null, true, HttpStatus.CONFLICT.value(),"Libro ya registrado");
            }
            return new CustomResponse<>(repository.saveAndFlush(book),false, HttpStatus.CREATED.value(),"Libro registrado exitosamente");
        }catch(Exception e){
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Server Error");
        }
    }

    @SuppressWarnings("null")
    public CustomResponse<Book> update (Long id, Book book){
        try{
                Optional<Book> currentBook = repository.findById(id);
                if(!currentBook.isPresent()){
                    return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"Libro no encontrado");
                }
                Book existBook = currentBook.get();
                existBook.setTitle(book.getTitle());
                existBook.setAuthor(book.getAuthor());
                existBook.setPublicationDate(book.getPublicationDate());
                return new CustomResponse<>(repository.saveAndFlush(existBook), false, HttpStatus.OK.value(),"Libro actualizado correctamente");
        }catch(Exception e){
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Server Error");
        }
    }

    @SuppressWarnings("null")
    public CustomResponse<String> delete(Long id){
        try{
            Optional<Book> existBook = repository.findById(id);

            if(!existBook.isPresent()){
                return new CustomResponse<>(null, true, HttpStatus.NOT_FOUND.value(),"Libro no encontrado");
            }

            repository.deleteById(id);
            return new CustomResponse<>(null, false, HttpStatus.OK.value(),"Libro eliminado exitosamente");
        }catch(Exception e){
            return new CustomResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Server Error");
        }
    }
    
}
