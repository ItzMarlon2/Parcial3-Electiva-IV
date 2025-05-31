package post.post.service;

import post.post.model.Product;
import post.post.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private final ProductRepository repository = mock(ProductRepository.class);
    private final ProductService service = new ProductService(repository);

    @Test
    public void testSaveProduct() {
        Product product = new Product(1L, "Producto Nuevo", "desc prueba", 200.0);
        when(repository.save(product)).thenReturn(product);

        Product result = service.saveProduct(product);
        assertEquals("Producto Nuevo", result.getName());
        assertEquals(200.0, result.getPrice());
    }
}