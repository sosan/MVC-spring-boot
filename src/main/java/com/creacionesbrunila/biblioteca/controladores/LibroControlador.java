package com.creacionesbrunila.biblioteca.controladores;

import com.creacionesbrunila.biblioteca.entidades.Autor;
import com.creacionesbrunila.biblioteca.entidades.Editorial;
import com.creacionesbrunila.biblioteca.entidades.Libros;
import com.creacionesbrunila.biblioteca.excepciones.MiException;
import com.creacionesbrunila.biblioteca.servicios.AutorServicio;
import com.creacionesbrunila.biblioteca.servicios.EditorialServicio;
import com.creacionesbrunila.biblioteca.servicios.LibroServicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicios libroServicios;

    @Autowired
    private AutorServicio autorServicio;
    @Autowired

    private EditorialServicio editorialServicio;

    @GetMapping("/registrar")//localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbm, @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam(required = false) String idAutor, @RequestParam(required = false) String idEditorial,
            ModelMap modelo) {//este va ser un parametro que va a viajar por la url/mostrar informacion por pantalla.
        try {
            libroServicios.crearLibro(isbm, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "el libro fue cargado correctamente");
        } catch (MiException ex) {
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            modelo.put("error", ex.getMessage());
            return "libro_form.html";//vuelve a cargar la pagina
        }
        return "index.html";
    }
      @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Libros> libros = libroServicios.listarLibros();
        modelo.addAttribute("libros", libros);
        return "libros_list.html";
    }
}
