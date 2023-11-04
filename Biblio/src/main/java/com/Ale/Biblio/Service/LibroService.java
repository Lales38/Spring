package com.Ale.Biblio.Service;

// @author Alejandro
import com.Ale.Biblio.Entidades.Autor;
import com.Ale.Biblio.Entidades.Editorial;
import com.Ale.Biblio.Entidades.Libro;
import com.Ale.Biblio.Exception.MyException;
import com.Ale.Biblio.Repositorio.AutorRepositorio;
import com.Ale.Biblio.Repositorio.EditorialRepositorio;
import com.Ale.Biblio.Repositorio.LibroRepositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MyException {

        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibro() {
        List<Libro> libros = new ArrayList();
        libros = libroRepositorio.findAll();
        return libros;
    }

    public void modificarLibro(Long isbn, String titulo,  String idAutor, String idEditorial, Integer ejemplares) throws MyException {

        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
            autor.setNombre(titulo);
        }
        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
            editorial.setId(idEditorial);
        }
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);
        }
    }
    
    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MyException{
     if (isbn==null) {
           throw new MyException ("El isbn no puede ser nulo");
        }
        if (titulo.isEmpty()  || titulo==null) {
             throw new MyException ("El titulo no puede ser nulo o estar vacio");
        }
 if (ejemplares==null) {
           throw new MyException ("Ejemplares  no puede ser nulo");
        }
    if (idAutor.isEmpty()  || idAutor==null) {
             throw new MyException ("El nombre del autor no puede ser nulo o estar vacio");
        }
    if (idEditorial.isEmpty()  || titulo==null) {
             throw new MyException ("El nombre de la editorial no puede ser nulo o estar vacio");
        }
}
}
