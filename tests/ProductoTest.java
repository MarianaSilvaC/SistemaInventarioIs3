import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto("TestProducto", 10.0, 5);
    }

    @Test
    void getNombre() {
        assertEquals("TestProducto", producto.getNombre(), "El nombre no coincide");
    }

    @Test
    void getPrecio() {
        assertEquals(10.0, producto.getPrecio(),  "El precio no coincide");
    }

    @Test
    void setPrecio() {
        producto.setPrecio(15.0);
        assertEquals(15.0, producto.getPrecio(),  "El precio establecido no coincide");
    }

    @Test
    void getStock() {
        assertEquals(5, producto.getStock(), "El stock no coincide");
    }

    @Test
    void setStock() {
        producto.setStock(10);
        assertEquals(10, producto.getStock(), "El stock establecido no coincide");
    }
}