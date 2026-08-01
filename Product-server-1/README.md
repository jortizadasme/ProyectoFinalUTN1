# product-service

Microservicio del ecosistema del TP Final — Diplomatura en Desarrollo de Software FinTech: IA y Microservicios (UTN BA). Ver arquitectura general en el README raíz del repo (`ProyectoFinalUTN1`).

Gestiona productos financieros (cuentas, tarjetas, préstamos, plazos fijos, seguros) y expone los productos asociados a un cliente por su ID.

## Stack

Java 21 · Spring Boot 4.0.7 · Spring Cloud 2025.1.2 (`spring-cloud-starter-config`, `spring-cloud-starter-netflix-eureka-client`) · Spring Data JPA + H2

## Cómo ejecutar

Requisito: JDK 21. No hace falta Maven instalado (incluye el wrapper). Debe levantarse después de `config-server` y `eureka-server`.

```bash
mvnw.cmd spring-boot:run      # Windows
./mvnw spring-boot:run        # Linux/Mac
```

Levanta en el puerto **8082**. Configuración (puerto, datasource H2) centralizada en [`tp-config-repo/product-service.yml`](https://github.com/jortizadasme/tp-config-repo/blob/main/product-service.yml).

## Endpoints (`/productos`)

| Método | Path | Descripción |
|---|---|---|
| POST | `/productos/agregar` | Crea un producto |
| GET | `/productos` | Lista todos los productos |
| GET | `/productos/{id}` | Consulta un producto por id |
| GET | `/productos/cliente/{clienteId}` | **Productos de un cliente** (el que consume `customer-service` vía Feign) |
| GET | `/productos/tipo/{tipo}` | Filtra por `TipoProducto` |
| GET | `/productos/activos` | Solo productos activos |
| PUT | `/productos/actualizar/{id}` | Actualiza un producto |
| DELETE | `/productos/eliminar/{id}` | Elimina un producto |

Consola H2: `http://localhost:8082/h2-console` (JDBC URL: `jdbc:h2:mem:productdb`, usuario `sa`, sin contraseña).

## Manejo de excepciones

`GlobalExceptionHandler` (`@RestControllerAdvice`): `ProductoNotFoundException` → 404, `Exception` genérica → 500. Respuesta `ErrorResponse { status, message }`.

## Origen

Migrado desde el proyecto base del profesor ([`TarjetasApi`](https://github.com/Pedroottaviano/TarjetasApi)), reemplazando MySQL por H2, agregando DTOs/mapper/manejo de excepciones y adaptándolo a Config Server + Eureka.
