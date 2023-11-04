package com.Ale.Biblio.Service;

// @author Alejandro
import com.Ale.Biblio.Entidades.Editorial;
import com.Ale.Biblio.Exception.MyException;
import com.Ale.Biblio.Repositorio.EditorialRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MyException {

        validar(nombre, nombre);
        
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);
    }

    public List<Editorial> listarEditorial() {
        List<Editorial> editorial = new ArrayList();
        editorial = editorialRepositorio.findAll();
        return editorial;
    }

    public void modificarEditorial(String id, String nombre) throws MyException {
        
        validar(id, nombre);
        
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(id);
        if (respuestaEditorial.isPresent()) {
            Editorial editorial = respuestaEditorial.get();
            editorial.setId(nombre);
            editorialRepositorio.save(editorial);
        }
    }
    private void validar (String id, String nombre) throws MyException{
         if (nombre.isEmpty()  || nombre==null) {
             throw new MyException ("El nombre de la editorial no puede ser nulo o estar vacio");
        }
         if (id==null) {
           throw new MyException ("El id no puede ser nulo");
        }
        
    }
}
