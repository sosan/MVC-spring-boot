package com.creacionesbrunila.biblioteca.servicios;

import com.creacionesbrunila.biblioteca.entidades.Autor;
import com.creacionesbrunila.biblioteca.entidades.Editorial;
import com.creacionesbrunila.biblioteca.entidades.Libros;
import com.creacionesbrunila.biblioteca.excepciones.MiException;
import com.creacionesbrunila.biblioteca.repositorios.AutorRepositorio;
import com.creacionesbrunila.biblioteca.repositorios.EditorialRepositorio;
import com.creacionesbrunila.biblioteca.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicios {

    @Autowired
    private LibroRepositorio libroRepositorio;//crea una instancia para persistir en el servidor
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional//(en nuestra base de datos)si el mtodo se ejecuta sin lanzar excepciones se realiza un commit a la base de datos y se aplica los cambios en cambio si lanza y no es atrapada se vulve atras con la transaccion(se hace un rollback)y no se aplica nda en la base de datos
    public void crearLibro(Long isbm, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {
        //todos los metodos que hagan modificaiones permanentes de la base datos deben ser anotados como transancionales

        validar(isbm, titulo, ejemplares, idAutor, idEditorial);

        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libros libros = new Libros();

        libros.setIsbm(isbm);
        libros.setTitulo(titulo);
        libros.setEjemplares(ejemplares);

        libros.setAlta(new Date());
        libros.setAutor(autor);
        libros.setEditorial(editorial);

        libroRepositorio.save(libros);

    }

    public List<Libros> listarLibros() {
        List<Libros> libros = new ArrayList();
        libros = libroRepositorio.findAll();
        return libros;
    }

    public void modificarLibro(Long isbm, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {

        validar(isbm, titulo, ejemplares, idAutor, idEditorial);

        Optional<Libros> respuesta = libroRepositorio.findById(isbm);//objeto contenedor que puede tner un valor o no.
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {//verifico que exista
            autor = respuestaAutor.get();

        }

        if (respuestaEditorial.isPresent()) {//verifico que exista
            editorial = respuestaEditorial.get();

        }

        if (respuesta.isPresent()) {
            Libros libros = respuesta.get();
            libros.setTitulo(titulo);
            libros.setAutor(autor);
            libros.setEditorial(editorial);
            libros.setEjemplares(ejemplares);

            libroRepositorio.save(libros);
        }

    }

    public void validar(Long isbm, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {
        if (isbm == null) {
            throw new MiException("el isbm  no puede ser nulo");
        }

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el titulo no puede ser nulo o estar vacio");
        }

        if (ejemplares == null) {
            throw new MiException("el ejemplares  no puede ser nulo");
        }

        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("el id autor no puede ser nulo o estar vacio");
        }
        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("el id editorial no puede ser nulo o estar vacio");
        }

    }

}
