# TP Final — Ecosistema de Microservicios (Diplomatura FinTech: IA y Microservicios, UTN BA)

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.4-6DB33F?logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-2025.0-6DB33F?logo=spring&logoColor=white)
![Eureka](https://img.shields.io/badge/Eureka-Service_Discovery-green)
![Config Server](https://img.shields.io/badge/Spring_Cloud-Config_Server-blue)
![API Gateway](https://img.shields.io/badge/Spring_Cloud-API_Gateway-0A66C2)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?logo=swagger&logoColor=black)
![JSON](https://img.shields.io/badge/Format-JSON-blue)

**Este repositorio (`ProyectoFinalUTN1`) es el enlace de entrega.** El ecosistema completo son 5 repositorios independientes en GitHub; localmente, por comodidad, 4 de ellos están anidados dentro de esta misma carpeta (cada uno conserva su propio `.git` y se sigue subiendo a su propio remoto, por separado):

| Repositorio (GitHub) | Carpeta local | Rol | Puerto |
|---|---|---|---|
| [`Config_Server-1`](https://github.com/jortizadasme/Config_Server-1) | `Config_Server-1/` | Config Server | 8888 |
| [`eureka-server-1`](https://github.com/jortizadasme/eureka-server-1) | `eureka-server-1/` | Eureka Server | 8761 |
| `ProyectoFinalUTN1` (este repo) | `Product-server-1/` | product-service | 8082 |
| [`Customer_Server-1`](https://github.com/jortizadasme/Customer_Server-1) | `Customer_Server-1/` | customer-service | 8081 |
| [`tp-config-repo`](https://github.com/jortizadasme/tp-config-repo) | (repo aparte, no anidado) | Configuración remota | — |

> Nota técnica: `Config_Server-1/`, `eureka-server-1/` y `Customer_Server-1/` están en el `.gitignore` de este repo — cada uno tiene su propio `.git` y remoto propio, así que **no** deben quedar trackeados como parte del historial de `ProyectoFinalUTN1`. Si abrís este repo en GitHub Desktop, solo vas a ver cambios de `Product-server-1/` (y de este README/LICENSE); para subir los otros 3, abrilos como repos independientes apuntando a esas mismas subcarpetas.

## Arquitectura

```
                 ┌─────────────────┐
                 │  tp-config-repo  │  (application.yml, customer-service.yml, product-service.yml)
                 └────────┬────────┘
                          │ lee al arrancar
                 ┌────────▼────────┐
                 │  config-server  │  :8888
                 └────────┬────────┘
          ┌───────────────┼───────────────┐
          │ pide config   │               │ pide config
   ┌──────▼──────┐  ┌─────▼──────┐  ┌──────▼───────┐
   │ eureka-srv  │  │  customer- │  │   product-   │
   │   :8761     │◄─┤  service   │  │   service    │
   └─────────────┘  │   :8081    │  │   :8082      │
        ▲  ▲        └─────┬──────┘  └──────▲───────┘
        │  └──────────────┘  Feign Client  │
        │       se registran en Eureka     │
        └──────────────────────────────────┘
```

`customer-service` llama a `product-service` por Feign, resolviendo la instancia vía Eureka. Toda la configuración (puertos, datasource, URL de Eureka) se centraliza en `tp-config-repo` y la sirve `config-server`.

## Orden de arranque

1. `Config_Server-1/` — 8888
2. `eureka-server-1/` — 8761
3. `Product-server-1/` (product-service) — 8082
4. `Customer_Server-1/` — 8081

Cada subcarpeta incluye el wrapper de Maven, así que no hace falta tener Maven instalado (solo JDK 21):

```bash
cd <subcarpeta>
mvnw.cmd spring-boot:run      # Windows
./mvnw spring-boot:run        # Linux/Mac
```

## Verificación end-to-end

1. `GET http://localhost:8888/customer-service/default` y `/product-service/default` → devuelven config desde `tp-config-repo`.
2. `http://localhost:8761` → dashboard de Eureka, deben listarse `CUSTOMER-SERVICE` y `PRODUCT-SERVICE`.
3. `GET http://localhost:8081/clientes/{id}/productos` → cliente + sus productos, obtenidos de `product-service` vía Feign.

Detalle de endpoints y manejo de excepciones de cada servicio: ver el README dentro de cada subcarpeta (`Product-server-1/README.md`, etc.).
