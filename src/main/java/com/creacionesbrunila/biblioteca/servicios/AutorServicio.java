package com.creacionesbrunila.biblioteca.servicios;

import com.creacionesbrunila.biblioteca.entidades.Autor;
import com.creacionesbrunila.biblioteca.excepciones.MiException;
import com.creacionesbrunila.biblioteca.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
//una instancia unica

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {
        validar(nombre);
        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);

    }

    public List<Autor> listarLibros() {
        List<Autor> libros = new ArrayList();
        libros = autorRepositorio.findAll();
        return libros;
    }

    @Transactional
    public void modificarAutor(String nombre, String id) throws MiException {
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);//persistencia
        }
    }

    public Autor getOn(String id) {
        return autorRepositorio.getOne(id);
    }

    private void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre del autor no puede ser nulo o vacío");
        }
    }

    @Transactional
    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList();

        autores = autorRepositorio.findAll();

        return autores;
    }

}
