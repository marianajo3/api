package com.example.clase9ws20232.controller;

import com.example.clase9ws20232.entity.Product;
import com.example.clase9ws20232.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductoController {

    private final ProductRepository productRepository;

    public ProductoController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET /product
    @GetMapping(value = {"", "/"})
    public List<Product> listarProductos() {
        return productRepository.findAll();
    }

    // GET /product/{id}
    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarProducto(@PathVariable("id") String idStr) {
        HashMap<String, Object> respuesta = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            Optional<Product> byId = productRepository.findById(id);

            if (byId.isPresent()) {
                respuesta.put("result", "ok");
                respuesta.put("producto", byId.get());
                return ResponseEntity.ok(respuesta);
            } else {
                respuesta.put("result", "error");
                respuesta.put("msg", "El producto no existe");
                return ResponseEntity.badRequest().body(respuesta);
            }

        } catch (NumberFormatException e) {
            respuesta.put("result", "error");
            respuesta.put("msg", "El ID debe ser un número válido");
            return ResponseEntity.badRequest().body(respuesta);
        }
    }

    // POST /product
    @PostMapping(value = {"", "/"})
    public ResponseEntity<HashMap<String, Object>> guardarProducto(@RequestBody Product product) {
        HashMap<String, Object> responseJson = new HashMap<>();

        Product productoGuardado = productRepository.save(product);

        responseJson.put("result", "ok");
        responseJson.put("estado", "creado");
        responseJson.put("producto", productoGuardado);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    // PUT /product
    @PutMapping(value = {"", "/"})
    public ResponseEntity<HashMap<String, Object>> actualizar(@RequestBody Product productRecibido) {
        HashMap<String, Object> rpta = new HashMap<>();

        if (productRecibido.getId() == null || productRecibido.getId() <= 0) {
            rpta.put("result", "error");
            rpta.put("msg", "Debe enviar un producto con ID");
            return ResponseEntity.badRequest().body(rpta);
        }

        Optional<Product> byId = productRepository.findById(productRecibido.getId());

        if (byId.isEmpty()) {
            rpta.put("result", "error");
            rpta.put("msg", "El ID del producto enviado no existe");
            return ResponseEntity.badRequest().body(rpta);
        }

        Product productFromDb = byId.get();

        if (productRecibido.getProductName() != null)
            productFromDb.setProductName(productRecibido.getProductName());

        if (productRecibido.getUnitPrice() != null)
            productFromDb.setUnitPrice(productRecibido.getUnitPrice());

        if (productRecibido.getUnitsInStock() != null)
            productFromDb.setUnitsInStock(productRecibido.getUnitsInStock());

        if (productRecibido.getUnitsOnOrder() != null)
            productFromDb.setUnitsOnOrder(productRecibido.getUnitsOnOrder());

        if (productRecibido.getSupplier() != null)
            productFromDb.setSupplier(productRecibido.getSupplier());

        if (productRecibido.getCategory() != null)
            productFromDb.setCategory(productRecibido.getCategory());

        if (productRecibido.getQuantityPerUnit() != null)
            productFromDb.setQuantityPerUnit(productRecibido.getQuantityPerUnit());

        if (productRecibido.getReorderLevel() != null)
            productFromDb.setReorderLevel(productRecibido.getReorderLevel());

        if (productRecibido.getDiscontinued() != null)
            productFromDb.setDiscontinued(productRecibido.getDiscontinued());

        Product productoActualizado = productRepository.save(productFromDb);

        rpta.put("result", "ok");
        rpta.put("msg", "Producto actualizado correctamente");
        rpta.put("producto", productoActualizado);

        return ResponseEntity.ok(rpta);
    }

    // DELETE /product/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> borrar(@PathVariable("id") String idStr) {
        HashMap<String, Object> rpta = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            Optional<Product> byId = productRepository.findById(id);

            if (byId.isPresent()) {
                productRepository.deleteById(id);
                rpta.put("result", "ok");
                rpta.put("msg", "Producto eliminado correctamente");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El producto no existe");
                return ResponseEntity.badRequest().body(rpta);
            }

        } catch (NumberFormatException e) {
            rpta.put("result", "error");
            rpta.put("msg", "El ID debe ser un número válido");
            return ResponseEntity.badRequest().body(rpta);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();

        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un producto válido en formato JSON");
        }

        return ResponseEntity.badRequest().body(responseMap);
    }
}
