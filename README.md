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
fetch("http://localhost:8080/product", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({
    id: 100,
    productName: "Producto de prueba",
    supplier: {
      id: 1
    },
    category: {
      id: 1
    },
    quantityPerUnit: "10 unidades",
    unitPrice: 25.5,
    unitsInStock: 20,
    unitsOnOrder: 0,
    reorderLevel: 5,
    discontinued: false
  })
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error("Error:", error));
