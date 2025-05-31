package get.get.service;

import get.get.model.Product;
import get.get.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private final ProductRepository repository = mock(ProductRepository.class);
    private final ProductService service = new ProductService(repository);

    @Test
    public void testGetAllProducts() {
        Product product = new Product(1L, "Producto 1", 100.0);
        when(repository.findAll()).thenReturn(Arrays.asList(product));

        List<Product> result = service.getAllProducts();
        assertEquals(1, result.size());
        assertEquals("Producto 1", result.get(0).getName());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Producto 1", 100.0);
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = service.getProductById(1L);
        assertTrue(result.isPresent());
        assertEquals(100.0, result.get().getPrice());
    }
}

