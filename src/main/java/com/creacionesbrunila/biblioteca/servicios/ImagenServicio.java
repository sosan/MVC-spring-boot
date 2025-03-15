package com.creacionesbrunila.biblioteca.servicios;

import com.creacionesbrunila.biblioteca.entidades.Imagen;
import com.creacionesbrunila.biblioteca.excepciones.MiException;
import com.creacionesbrunila.biblioteca.repositorios.ImagenRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;// para persistir llamamos al repositorio

    public Imagen guardar(MultipartFile archivo) throws MiException {//archivo sale de multipartfile
        if (archivo != null) {
            try {

                Imagen imagen = new Imagen();

                imagen.setMime(archivo.getContentType());

                imagen.setNombre(archivo.getName());

                imagen.setContenido(archivo.getBytes());//este es un arreglo de bytes 

                return imagenRepositorio.save(imagen);//aca persistimos

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;//si todo lo anterior no se cumple esto va retornar una entidad vacia
    }

    public Imagen actualizar(MultipartFile archivo, String idImagen) throws MiException {
        // Paso 1: Verificar si el archivo no es nulo
        if (archivo != null) {
            try {
                // Paso 2: Crear una nueva instancia de Imagen
                Imagen imagen = new Imagen();
                // Paso 3: Verificar si hay un idImagen proporcionado
                if (idImagen != null) {
                    // Paso 3.1: Buscar la imagen existente por el idImagen
                    Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
                    // Paso 3.2: Si la imagen existe, actualizar la instancia de imagen
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();//este es referencia a la imagen no crea una nueva instancia
                    }
                }
                // Paso 4: Configurar las propiedades de la Imagen con la información del archivo
                imagen.setMime(archivo.getContentType());

                imagen.setNombre(archivo.getName());

                imagen.setContenido(archivo.getBytes());
                // Paso 5: Guardar la imagen en el repositorio y devolverla
                return imagenRepositorio.save(imagen);

            } catch (Exception e) {
                // Paso 6: Manejar excepciones imprimiendo un mensaje de error
                System.err.println(e.getMessage());
            }
        }
        return null;// Paso 7: Si el archivo es nulo, devolver nulo

    }
}
