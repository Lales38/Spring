
package com.Ale.Biblio.Repositorio;

import com.Ale.Biblio.Entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
// @author Alejandro
public interface LibroRepositorio  extends  JpaRepository<Libro, Long>{

    @Query ( "SELECT l From Libro l Where l.titulo = :titulo ")
    public Libro buscarPorTitulo(@Param ("titulo") String titulo);
    
     @Query ( "SELECT l From Libro l Where l.autor.nombre = :nombre ")
     public List <Libro> buscarPorAutor(@Param ("nombre") String nombre);  
}
