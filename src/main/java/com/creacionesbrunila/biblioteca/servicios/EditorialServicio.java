package com.creacionesbrunila.biblioteca.servicios;

import com.creacionesbrunila.biblioteca.entidades.Editorial;
import com.creacionesbrunila.biblioteca.excepciones.MiException;
import com.creacionesbrunila.biblioteca.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired//persistir
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {

        validar(nombre);
        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);//PERSISTIR YA LLENO
    }

    public List<Editorial> listarLibros() {
        List<Editorial> libros = new ArrayList();
        libros = editorialRepositorio.findAll();
        return libros;
    }

    @Transactional
    public void modificarEditorial(String id, String nombre) throws MiException {// El id se utiliza para identificar la editorial que se va a modificar, y nombre es el nuevo nombre que se le asignará a esa editorial.
        validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);// El resultado de esta búsqueda se almacena en una variable llamada respuesta

        if (respuesta.isPresent()) {//si existe devuelve true
            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        }
    }

    public Editorial getOn(String id) {
        return editorialRepositorio.getOne(id);
    }

    private void validar(String nombre) throws MiException {
        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre del autor no puede ser nulo o vacío");
        }
    }

    @Transactional
    public List<Editorial> listarEditoriales() {
        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRepositorio.findAll();

        return editoriales;
    }

}
