Biblioteca
El proyecto Biblioteca es una aplicación completa basada en el patrón de arquitectura MVC (Modelo-Vista-Controlador) que utiliza tecnologías como Java, Spring Boot y Spring Security en el backend. La base de datos se gestiona con MySQL, proporcionando una solución robusta para el manejo de datos.

Frontend y Backend
En el frontend, se han incorporado tecnologías como Thymeleaf y Bootstrap para asegurar una experiencia de usuario intuitiva y visualmente atractiva, asegurando que la interfaz sea amigable y fácil de usar en dispositivos móviles y de escritorio.

Funcionalidades del Proyecto
La aplicación permite a los usuarios registrarse y crear cuentas para acceder a listas de libros, autores y editoriales. Los usuarios, en su rol inicial, tienen acceso solo a la visualización de estas listas. Sin embargo, al convertirse en administradores, una vez habilitados por el administrador principal, los usuarios adquieren permisos adicionales que les permiten modificar información relacionada con libros, autores, editoriales y usuarios.

Roles de Usuario
Usuario común: Solo puede visualizar las listas de libros, autores y editoriales.
Administrador: Además de tener acceso a las listas, tiene permisos para gestionar y modificar el contenido relacionado con libros, autores, editoriales y usuarios. Esta capacidad de gestión le otorga un control más profundo sobre el sistema.
Arquitectura
Este enfoque sigue de cerca los principios del patrón MVC, donde:

Modelo: Representa la lógica de negocio y las interacciones con la base de datos (MySQL).
Vista: Gestiona la presentación de los datos utilizando Thymeleaf para generar las vistas dinámicas.
Controlador: Se encarga de recibir las peticiones del usuario, interactuar con el modelo y devolver la vista adecuada.
