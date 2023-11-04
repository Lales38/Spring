
package com.Ale.Biblio.Repositorio;

import com.Ale.Biblio.Entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EditorialRepositorio  extends JpaRepository<Editorial, String>{
    
}
