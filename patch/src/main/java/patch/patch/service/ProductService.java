package patch.patch.service;

import patch.patch.repository.ProductRepository;
import patch.patch.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<Product> updateProduct(Long id, Product newData) {
        return repository.findById(id).map(existing -> {
            if (newData.getName() != null) existing.setName(newData.getName());
            if (newData.getPrice() != null) existing.setPrice(newData.getPrice());
            return repository.save(existing);
        });
    }
}
