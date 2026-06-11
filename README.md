# GTICS LAB 8 - Northwind Spring Boot

Este repositorio contiene dos proyectos separados:

1. `northwind-api`: Servidor API REST usando Spring Boot, JPA y MySQL.
2. `northwind-client`: Cliente web usando Spring Boot y Thymeleaf, consume el API REST.

## Ejecutar API

```bash
cd northwind-api
mvn spring-boot:run
```

API disponible en:

```txt
http://localhost:8080/product
```

## Ejecutar cliente Thymeleaf

En otra terminal:

```bash
cd northwind-client
mvn spring-boot:run
```

Cliente disponible en:

```txt
http://localhost:8081/products
```

## Base de datos configurada

```properties
spring.datasource.url=jdbc:mysql://lewisrp.dev:3306/Northwind
spring.datasource.username=root
spring.datasource.password=root
```

## Documentación

La documentación del API está en:

```txt
northwind-api/API_DOCUMENTATION.md
```
