# Documentación API REST - Northwind Products

## Base URL

http://localhost:8080

---

## 1. Listar productos

**Método:** GET  
**URL:** `/product`

Devuelve una lista de todos los productos registrados en la base de datos Northwind.

### Entrada

No requiere parámetros.

### Respuesta exitosa

**Código HTTP:** 200 OK

```json
[
  {
    "id": 1,
    "productName": "Chai",
    "quantityPerUnit": "10 boxes x 20 bags",
    "unitPrice": 18.0000,
    "unitsInStock": 39,
    "supplier": {
      "id": 1,
      "companyName": "Exotic Liquids"
    },
    "category": {
      "id": 1,
      "categoryName": "Beverages"
    }
  }
]
```

---

## 2. Obtener producto por ID

**Método:** GET  
**URL:** `/product/{id}`

Busca un producto usando su ID.

### Parámetros de entrada

| Parámetro | Tipo | Descripción |
|---|---|---|
| id | Integer | ID del producto |

### Respuesta exitosa

**Código HTTP:** 200 OK

```json
{
  "result": "ok",
  "producto": {
    "id": 1,
    "productName": "Chai",
    "quantityPerUnit": "10 boxes x 20 bags",
    "unitPrice": 18.0000,
    "unitsInStock": 39
  }
}
```

### Respuesta de error

**Código HTTP:** 400 Bad Request

```json
{
  "result": "error",
  "msg": "El producto no existe"
}
```

```json
{
  "result": "error",
  "msg": "El ID debe ser un número válido"
}
```

---

## 3. Crear producto

**Método:** POST  
**URL:** `/product`

Crea un nuevo producto en la base de datos.

### Body JSON

```json
{
  "productName": "Nuevo producto",
  "quantityPerUnit": "10 unidades",
  "unitPrice": 25.50,
  "unitsInStock": 20,
  "unitsOnOrder": 0,
  "reorderLevel": 5,
  "discontinued": false,
  "supplier": {
    "id": 1
  },
  "category": {
    "id": 1
  }
}
```

### Respuesta exitosa

**Código HTTP:** 201 Created

```json
{
  "result": "ok",
  "estado": "creado",
  "producto": {
    "id": 78,
    "productName": "Nuevo producto"
  }
}
```

### Respuesta de error

**Código HTTP:** 400 Bad Request

```json
{
  "estado": "error",
  "msg": "Debe enviar un producto válido en formato JSON"
}
```

---

## 4. Actualizar producto

**Método:** PUT  
**URL:** `/product`

Actualiza los datos de un producto existente. El ID debe enviarse en el cuerpo JSON.

### Body JSON

```json
{
  "id": 1,
  "productName": "Producto actualizado",
  "quantityPerUnit": "20 unidades",
  "unitPrice": 30.00,
  "unitsInStock": 15
}
```

### Respuesta exitosa

**Código HTTP:** 200 OK

```json
{
  "result": "ok",
  "msg": "Producto actualizado correctamente"
}
```

### Respuesta de error

**Código HTTP:** 400 Bad Request

```json
{
  "result": "error",
  "msg": "Debe enviar un producto con ID"
}
```

```json
{
  "result": "error",
  "msg": "El ID del producto enviado no existe"
}
```

---

## 5. Eliminar producto

**Método:** DELETE  
**URL:** `/product/{id}`

Elimina un producto según su ID.

### Parámetros de entrada

| Parámetro | Tipo | Descripción |
|---|---|---|
| id | Integer | ID del producto |

### Respuesta exitosa

**Código HTTP:** 200 OK

```json
{
  "result": "ok",
  "msg": "Producto eliminado correctamente"
}
```

### Respuesta de error

**Código HTTP:** 400 Bad Request

```json
{
  "result": "error",
  "msg": "El producto no existe"
}
```

```json
{
  "result": "error",
  "msg": "El ID debe ser un número válido"
}
```
