package com.Ale.Biblio.Controler;

// @author Alejandro
import com.Ale.Biblio.Exception.MyException;
import com.Ale.Biblio.Service.EditorialService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")

public class EditorialControlador {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/registrar") //localhost:8080/editorial/registrar
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
        try {
            editorialService.crearEditorial(nombre);
        } catch (MyException ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_form.html";
        }
        return "index.html";
    }
}
