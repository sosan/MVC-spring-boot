# Biblioteca

El proyecto **Biblioteca** es una aplicación completa basada en el patrón de arquitectura **MVC** (Modelo-Vista-Controlador), que utiliza **Java**, **Spring Boot** y **Spring Security** en el backend. La base de datos se gestiona con **MySQL**, proporcionando una solución robusta para el manejo de datos.

## Tecnologías Utilizadas

- **Backend:** Java, Spring Boot, Spring Security
- **Frontend:** Thymeleaf, Bootstrap
- **Base de Datos:** MySQL

## Funcionalidades del Proyecto

La aplicación permite a los usuarios **registrarse y crear cuentas** para acceder a listas de **libros**, **autores** y **editoriales**. Los usuarios, en su rol inicial, solo tienen acceso a la visualización de estas listas.

### Roles de Usuario

#### Usuario común:
- Solo puede visualizar las listas de libros, autores y editoriales.

#### Administrador:
- Además de tener acceso a las listas mencionadas, el administrador tiene permisos adicionales para modificar información sobre libros, autores, editoriales y usuarios.
- El administrador tiene acceso completo al sistema y puede gestionar el contenido de manera eficiente.

## Flujo de Administración

Un usuario solo puede convertirse en administrador cuando es **habilitado por el administrador principal**. Este enfoque asegura que el control de los permisos esté centralizado y bajo la supervisión de los administradores.

## Arquitectura

Este proyecto sigue el patrón de arquitectura **MVC**, que se estructura de la siguiente manera:

### Modelo:
Representa la lógica de negocio y las interacciones con la base de datos (**MySQL**).

### Vista:
Se encarga de la presentación de los datos al usuario utilizando **Thymeleaf** para generar las vistas dinámicas.

### Controlador:
Recibe las peticiones del usuario, interactúa con el modelo y devuelve la vista correspondiente.

El patrón **MVC** permite una organización eficiente del código, separando las responsabilidades de negocio, presentación y control. Esto facilita la escalabilidad, el mantenimiento y la claridad del sistema.

