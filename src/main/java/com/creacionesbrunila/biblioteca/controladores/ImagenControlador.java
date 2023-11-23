package com.creacionesbrunila.biblioteca.controladores;

import com.creacionesbrunila.biblioteca.entidades.Usuario;
import com.creacionesbrunila.biblioteca.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired//instancia
    UsuarioServicio usuarioServicio;

    @GetMapping("/perfil/{id}")//por la url llega la variable id
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id) {

        Usuario usuario = usuarioServicio.getOne(id);

        byte[] imagen = usuario.getImagen().getContenido();//en este arreglo de bytes va estar guardado el contenido

        HttpHeaders headers = new HttpHeaders();//esta es la cabecera que le va a decir al navegador que es una imagen

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);//devolvemos con un codigo 200 que esta todo ok
    }
}
