package com.Ale.Biblio.Service;

// @author Alejandro
import com.Ale.Biblio.Entidades.Autor;
import com.Ale.Biblio.Exception.MyException;
import com.Ale.Biblio.Repositorio.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MyException {

        validar(nombre, nombre);

        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
    }

    public List<Autor> listarAutor() {
        List<Autor> autor = new ArrayList();
        autor = autorRepositorio.findAll();
        return autor;
    }

    @Transactional
    public void modificarAutor(String nombre, String id) throws MyException {

        Optional<Autor> respuestaAutor = autorRepositorio.findById(id);

        if (respuestaAutor.isPresent()) {
            Autor autor = respuestaAutor.get();
            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        }
    }

    public Autor getOne(String id) {

        return autorRepositorio.getOne(id);
    }

    private void validar(String id, String nombre) throws MyException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("El nombre del autor no puede ser nulo o estar vacio");
        }
        if (id == null) {
            throw new MyException("El id no puede ser nulo");
        }

    }

}
