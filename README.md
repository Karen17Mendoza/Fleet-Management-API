# Fleet Management Software API

### Descripción

**Fleet Management Software API** es una solución backend desarrollada en Java con Spring Boot, diseñada para gestionar vehículos de flotas, conductores, rutas y tareas de mantenimiento. Esta API proporciona endpoints seguros para administrar y monitorear de manera eficiente todos los aspectos relacionados con la operación de flotas.

### Características

- Autenticación y autorización mediante JWT.
- Gestión de vehículos: obtener información detallada.
- Gestión de usuarios: registro, actualización y eliminacion.
- Gestión de trayectorias.
- Soporte para registros de mantenimiento y alertas de vencimientos.
- Manejo global de excepciones para proporcionar respuestas claras de errores.

### Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security (JWT)**
- **Hibernate (JPA)**
- **PostgreSQL** (o cualquier base de datos relacional compatible)
- **Maven** para la gestión de dependencias.
  
### Endpoints Principales

- /taxis: listado de taxis.
- /trajectories: historial de ubicaciones.
- /trajectories/latest: última ubicación.
- /users: gestión de usuarios(CRUD)
- /auth/signup: registro de nuevo usuario.
- /auth/login: Autenticación de usuarios.
  
### Instalación

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/Karen17Mendoza/Fleet-Management-API.git
