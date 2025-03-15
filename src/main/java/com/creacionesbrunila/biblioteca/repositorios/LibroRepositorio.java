package com.creacionesbrunila.biblioteca.repositorios;

import com.creacionesbrunila.biblioteca.entidades.Libros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;//lo que significa que hereda métodos de operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y otros métodos útiles proporcionados por Spring Data JPA.
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//componente
@Repository// La interfaz está anotada con @Repository, lo que le indica a Spring que esta interfaz es un componente de repositorio y debe ser administrada por el contenedor de Spring. Esto permite la inyección de dependencias y la gestión automática de transacciones.
public interface LibroRepositorio extends JpaRepository<Libros, Long> {//id de tipo long.Esta interfaz se utiliza para interactuar con la base de datos y realizar operaciones relacionadas con la entidad Libros.
//declara dos métodos personalizados

    @Query("SELECT l FROM Libros l WHERE l.titulo =: titulo")//Este método utiliza una consulta JPQL (Java Persistence Query Language)
    public Libros buscarxtitulo(@Param("titulo") String titulo);//@Param("titulo")es igual l.titulo y String titulo =: titulo

    @Query("SELECT l FROM Libros l WHERE l.autor.nombre =: nombre")
    public List<Libros> buscarxnombre(@Param("nombre") String nombre);//@Param se utiliza para especificar explícitamente la asociación entre los parámetros del método y los nombres de los parámetros en las consultas JPQL.

}
