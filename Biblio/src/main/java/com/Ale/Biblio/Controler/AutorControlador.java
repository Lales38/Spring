package com.Ale.Biblio.Controler;

// @author Alejandro
import com.Ale.Biblio.Entidades.Autor;
import com.Ale.Biblio.Exception.MyException;
import com.Ale.Biblio.Service.AutorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")  //localhost:8080/autor
public class AutorControlador {

    @Autowired
    private AutorService autorService;

    @GetMapping("/registrar")  //localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        
        try {
            autorService.crearAutor(nombre);
            modelo.put("exito", "El Autor ya fue cargado");

        } catch (MyException ex) {

            modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Autor> autores = autorService.listarAutor();

        modelo.addAttribute("autores", autores);

        return "autorList.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("autor", autorService.getOne(id));
        return "autorModificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo) {
        try {
            autorService.modificarAutor(nombre, id);
            return "redirect:../lista";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "autorModificar.html";
        }
    }

}
