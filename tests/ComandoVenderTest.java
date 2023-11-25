import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ComandoVenderTest {
    private Inventario inventario;

    @BeforeEach
    public void setUp() {
        // Configuración inicial
        inventario = Inventario.obtenerInstancia();
    }

    @Test
    void deberiaVenderProductoYActualizarStock() {
        // Configuración específica del test
        Producto producto = new Producto("Producto1", 10.0, 50);
        inventario.agregarProducto(producto);

        String entradaUsuario = "Producto1\n10\n";
        InputStream inputStream = new ByteArrayInputStream(entradaUsuario.getBytes());
        Scanner scanner = new Scanner(inputStream);
        ComandoVender comandoVender = new ComandoVender(scanner);

        comandoVender.ejecutar();

        Producto productoVendido = inventario.buscarProducto("Producto1");
        assertEquals(40, productoVendido.getStock());
    }

}