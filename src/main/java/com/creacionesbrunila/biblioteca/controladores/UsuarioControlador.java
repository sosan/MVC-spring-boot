package com.creacionesbrunila.biblioteca.controladores;

import com.creacionesbrunila.biblioteca.entidades.Usuario;
import com.creacionesbrunila.biblioteca.excepciones.MiException;
import com.creacionesbrunila.biblioteca.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    //este
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        // Obtiene un usuario por su ID utilizando el servicio de usuario y lo agrega al modelo
        modelo.put("usuario", usuarioServicio.getOne(id));
        // Devuelve el nombre de la vista que se mostrará, en este caso, "usuario_modificar.html"
        return "usuario_modificar.html";
    }

    //este 
    @PostMapping("/modificar/{id}")
    public String modificar(MultipartFile archivo, @PathVariable String nombre, String id, String email, String password, String password2, ModelMap modelo) {
        try {
            usuarioServicio.actualizar(archivo, password2, nombre, email, password, password2);
            // Si la modificación es exitosa, redirige a la ruta "/admin/usuarios"
            return "redirect:/admin/usuarios";
        } catch (MiException ex) {
            //Si ocurre una excepción (MiException), agrega un mensaje de error al modelo y vuelve a la vista "usuario_modificar.html"
            modelo.put("error", ex.getMessage());
            return "usuario_modificar.html";
        }
    }

}
