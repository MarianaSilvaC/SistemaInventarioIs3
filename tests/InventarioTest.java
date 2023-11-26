
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {

    Inventario inventario;
    Producto producto;

    @BeforeEach
    void setUp() {
        inventario = Inventario.obtenerInstancia();
        producto = new Producto("ProductoTest", 20.0, 10);
    }

    @Test
    void obtenerInstancia() {
        assertNotNull(inventario, "La instancia de Inventario es null");
        assertSame(inventario, Inventario.obtenerInstancia(), "Las instancias no son las mismas");
    }

    @Test
    void agregarObservador() {
        Observador observador = new ObservadorTest(); // Asumiendo que ObservadorTest es una implementación de Observador
        inventario.agregarObservador(observador);
        assertTrue(inventario.getObservadores().contains(observador), "El observador no fue agregado correctamente");
        // Verificar que el observador se agregó correctamente (esto podría requerir un método getObservadores o similar)
    }



    @Test
    void agregarProducto() {
        inventario.agregarProducto(producto);
        assertTrue(inventario.getListaProductos().contains(producto), "El producto no fue agregado correctamente");
    }

    @Test
    void editarProducto() {
        inventario.agregarProducto(producto);
        Producto productoEditado = new Producto("ProductoTest", 30.0, 15);
        inventario.editarProducto(productoEditado);
        Producto productoEncontrado = inventario.buscarProducto("ProductoTest");
        assertNotNull(productoEncontrado, "El producto editado no se encuentra");
        assertEquals(30.0, productoEncontrado.getPrecio(), "El precio no se actualizó correctamente");
        assertEquals(15, productoEncontrado.getStock(), "El stock no se actualizó correctamente");
    }

    @Test
    void getListaProductos() {
        assertNotNull(inventario.getListaProductos(), "La lista de productos es null");
    }

    @Test
    void buscarProducto() {
        inventario.agregarProducto(producto);
        assertNotNull(inventario.buscarProducto("ProductoTest"), "El producto no fue encontrado");
        assertNull(inventario.buscarProducto("ProductoInexistente"), "Se encontró un producto que no debería existir");
    }

    // Clase de prueba para Observador
    static class ObservadorTest implements Observador {
        @Override
        public void actualizar() {
            System.out.println("El método actualizar fue llamado");
            // Implementación de prueba
        }
    }
}
