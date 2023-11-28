import java.util.ArrayList;
import java.util.List;

class ComandoMostrarGanancias implements Comando {
    private Inventario inventario;

    public ComandoMostrarGanancias(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public void ejecutar() {
        List<Venta> ventas = inventario.getVentas();

        if (!ventas.isEmpty()) {
            System.out.println("Mostrando ganancias:");

            // Combina las ventas del mismo producto
            /*
            List<Venta> ventasComb = new ArrayList<>();
            for (Venta venta : ventas) {
                boolean combinada = false;
                for (Venta combVenta : ventasComb) {
                    if (venta.getProducto().equals(combVenta.getProducto())) {
                        combVenta.combinarVenta(venta);
                        combinada = true;
                        break;
                    }
                }
                if (!combinada) {
                    ventasComb.add(venta);
                }
            }
            */

            // Muestra las ventas combinadas
            for (Venta venta : ventas) {
                System.out.println("---------------");
                System.out.println("ID: " + venta.getId());
                System.out.println("Fecha: " + venta.getFecha());
                System.out.println("Producto: " + venta.getProducto().getNombre());
                System.out.println("Cantidad: " + venta.getCantidad());
                System.out.println("Monto: $" + venta.getGanancia());
            }
            System.out.println("---------------");
            // Muestra ganancias totales
            double gananciasTotales = 0.0;
            for (Venta venta : ventas) {
                gananciasTotales += venta.getGanancia();
            }
            System.out.println("Ganancias totales: $" + gananciasTotales);
        } else {
            System.out.println("No hay ventas registradas.");
        }
    }
}