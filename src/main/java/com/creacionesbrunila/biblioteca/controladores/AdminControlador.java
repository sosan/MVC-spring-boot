package com.creacionesbrunila.biblioteca.controladores;

import com.creacionesbrunila.biblioteca.entidades.Usuario;


import com.creacionesbrunila.biblioteca.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/dashboard")
    public String panelAdministrativo() {
        return "panel.html";
    }

    // Define un método que responde a las solicitudes GET en la ruta "/usuarios"
    @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        // Llama al servicio para obtener una lista de usuarios
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        // Agrega la lista de usuarios al modelo con el nombre "usuarios"
        modelo.addAttribute("usuarios", usuarios);
        // Devuelve el nombre de la vista que se mostrará, en este caso, "usuarios_list"
        return "usuarios_list";
    }

    //La anotación @PathVariable en Spring se utiliza para extraer valores de variables de la URI (Uniform Resource Identifier) y pasarlos como parámetros a un controlador de Spring.
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id) {
        usuarioServicio.cambiarRol(id);
        // Redirige a la ruta "/admin/usuarios" después de cambiar el rol
        return "redirect:/admin/usuarios";
    }


}
