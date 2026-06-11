package com.example.northwindclient.controller;

import com.example.northwindclient.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class ProductWebController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${api.url}")
    private String apiUrl;

    @GetMapping("/")
    public String inicio() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listarProductos(Model model) {
        cargarListaProductos(model);
        return "products";
    }

    @PostMapping("/products/search")
    public String buscarProducto(@RequestParam String id, Model model) {
        try {
            LinkedHashMap<?, ?> respuesta = restTemplate.getForObject(
                    apiUrl + "/product/" + id,
                    LinkedHashMap.class
            );

            if (respuesta != null && "ok".equals(respuesta.get("result"))) {
                model.addAttribute("productFound", respuesta.get("producto"));
            } else {
                model.addAttribute("error", "Producto no encontrado");
            }

        } catch (Exception e) {
            model.addAttribute("error", "Producto no encontrado o ID inválido");
        }

        cargarListaProductos(model);
        return "products";
    }

    private void cargarListaProductos(Model model) {
        try {
            List<ProductDTO> products = restTemplate.exchange(
                    apiUrl + "/product",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProductDTO>>() {}
            ).getBody();

            model.addAttribute("products", products);

        } catch (Exception e) {
            model.addAttribute("products", List.of());
            model.addAttribute("error", "No se pudo conectar con el API. Verifique que northwind-api esté ejecutándose en el puerto 8080.");
        }
    }
}
