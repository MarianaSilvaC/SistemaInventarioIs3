import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventarioVentasTest {
    @Test
    void deberiaRegistrarVenta() {
        Inventario inventario = new Inventario();
        Producto leche = new Producto("Leche", 25.0, 50);

        // Realizar venta
        inventario.registrarVenta(leche, 5);

        // Verificar si la venta se registró correctamente
        List<Venta> listaVentas = inventario.getVentas();
        assertEquals(1, listaVentas.size());
        assertEquals(leche, listaVentas.get(0).getProducto());
        assertEquals(5, listaVentas.get(0).getCantidad());
    }

    @Test
    void deberiaCalcularMontoVenta() {
        Inventario inventario = new Inventario();
        Producto cereal = new Producto("Cereal", 50.0, 30);

        // Realizar venta
        inventario.registrarVenta(cereal, 3);

        // Verificar si el monto de la venta es correcto
        assertEquals(3 * 50.0, inventario.calcularMontoVenta(cereal,3), 0.01);
    }

    @Test
    void deberiaAcumularVentas() {
        Inventario inventario = new Inventario();
        Producto yogurt = new Producto("Yogurt", 47.0, 40);

        // Realizar dos ventas
        inventario.registrarVenta(yogurt, 10);
        inventario.registrarVenta(yogurt, 5);

        // Verificar si las ventas se acumulan correctamente
        assertEquals(15, inventario.calcularVentasTotales());
    }

    @Test
    void deberiaCalcularGananciasTotales() {
        Inventario inventario = new Inventario();
        Producto leche = new Producto("Leche", 25.0, 50);
        Producto cereal = new Producto("Cereal", 50.0, 30);

        // Realizar dos ventas
        inventario.registrarVenta(leche, 5);
        inventario.registrarVenta(cereal, 3);

        // Verificar si las ganancias totales son correctas
        assertEquals((5 * 25.0) + (3 * 50.0), inventario.calcularGananciasTotales(), 0.01);
    }


}