import java.util.Scanner;

class ComandoVender implements Comando {

    private Scanner scanner;

    public ComandoVender(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void ejecutar() {
        System.out.println("Productos disponibles:");
        Inventario inventario = Inventario.obtenerInstancia();
        for (Producto producto : inventario.getListaProductos()) {
            System.out.println(producto.getNombre());
        }
        System.out.println("Ingrese el nombre del producto a vender:");
        String nombreProducto = scanner.next();

        Producto productoAVender = inventario.buscarProducto(nombreProducto);

        if (productoAVender != null) {
            System.out.println("Stock: " + productoAVender.getStock()+ " unidades.\tPrecio: $" + productoAVender.getPrecio());
            System.out.println("Ingrese la cantidad a vender:");
            int cantidadVendida = scanner.nextInt();

            if (cantidadVendida <= productoAVender.getStock()) {
                productoAVender.setStock(productoAVender.getStock() - cantidadVendida);
                inventario.registrarVenta(productoAVender, cantidadVendida);

                System.out.println("Venta realizada exitosamente.");
            } else {

                System.out.println("No hay suficiente stock para la venta.");
            }
        } else {
            System.out.println("Producto no encontrado en el inventario.");
        }
    }
}