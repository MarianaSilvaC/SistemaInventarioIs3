import java.util.Scanner;

class ComandoEditar implements Comando {

    private Scanner scanner;

    public ComandoEditar(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void ejecutar() {
        // Obtener el nombre del producto a editar desde el usuario
        System.out.println("Ingrese el nombre del producto a editar:");
        String nombreProducto = scanner.next();

        // Buscar el producto en el inventario
        Inventario inventario = Inventario.obtenerInstancia();
        Producto productoExistente = inventario.buscarProducto(nombreProducto);

        // Verificar si el producto existe
        if (productoExistente != null) {
            // Obtener los nuevos detalles del producto desde el usuario
            System.out.println("Ingrese el nuevo precio del producto:");
            double nuevoPrecio = scanner.nextDouble();

            System.out.println("Ingrese el nuevo stock del producto:");
            int nuevoStock = scanner.nextInt();

            // Actualizar los detalles del producto en el inventario
            productoExistente.setPrecio(nuevoPrecio);
            productoExistente.setStock(nuevoStock);

            System.out.println("Producto editado exitosamente.");
        } else {
            System.out.println("Producto no encontrado en el inventario.");
        }
    }
}