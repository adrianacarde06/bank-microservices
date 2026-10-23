# Prueba Técnica - Arquitectura de Microservicios Bancarios

Este proyecto implementa una solución backend basada en **Microservicios** para la gestión de clientes, cuentas y movimientos bancarios, cumpliendo con los principios de **Clean Architecture**, **Clean Code** y patrones de diseño como **Repository**.

## 🏗️ Arquitectura y Decisiones de Diseño

La solución está dividida en dos microservicios independientes que se comunican de forma **asíncrona**:

1.  **MS-Personas (`mspersonas`)**: Gestiona las entidades `Persona` y `Cliente`.
2.  **MS-Cuentas (`mscuentas`)**: Gestiona las entidades `Cuenta` y `Movimiento`.

### Clean Architecture
Cada microservicio está estructurado en capas concéntricas para garantizar el desacoplamiento:
*   **Domain**: Modelos de negocio puros (POJOs) y puertos (interfaces de repositorios). No tiene dependencias.
*   **Use Cases (Application)**: Lógica de negocio y orquestación.
*   **Infrastructure**: Adaptadores de entrada/salida (Controladores REST, Entidades JPA, Consumidores de RabbitMQ, Mappers).

### Patrones y Tecnologías Clave
*   **Java 21 & Spring Boot 3**: Framework principal.
*   **Spring Data JPA & MySQL**: Persistencia de datos (una base de datos por microservicio).
*   **RabbitMQ**: Comunicación asíncrona. Cuando se crea un cliente en MS-Personas, se emite un evento a MS-Cuentas.
*   **MapStruct**: Mapeo eficiente entre Entidades, Dominio y DTOs.
*   **Spring Security (BCrypt)**: Encriptación de contraseñas de clientes.
*   **Docker & Docker Compose**: Contenedores para el despliegue unificado.

---

## 🚀 Cómo ejecutar el proyecto (Docker)

La forma más sencilla de levantar toda la infraestructura (Bases de datos, RabbitMQ y los dos microservicios) es usando Docker Compose.

### Prerrequisitos
*   Tener instalado **Docker** y **Docker Compose**.
*   Asegurarse de que los puertos `8080`, `8081`, `3307` y `5672` estén libres.

### Pasos
1.  Clona este repositorio o descomprime el archivo entregable.
2.  Abre una terminal en la raíz del proyecto (donde se encuentra el archivo `docker-compose.yml`).
3.  Ejecuta el siguiente comando para construir y levantar los contenedores:
    ```bash
    docker-compose up --build ```
	
### Repositorio de Git
	```bash
	https://github.com/adrianacarde06/bank-microservices ```
	
### Tablas y bases de datos
	```bash
	BaseDatos.sql ```