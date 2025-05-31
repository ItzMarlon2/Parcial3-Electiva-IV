package patch.patch.controller;

import patch.patch.model.Product;
import patch.patch.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // PATCH /products/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedFields) {
        return service.updateProduct(id, updatedFields)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}