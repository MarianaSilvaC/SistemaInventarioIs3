import java.util.Scanner;

class ComandoBuscar implements Comando {

    private Scanner scanner;

    public ComandoBuscar(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void ejecutar() {
        // Obtener el nombre del producto a buscar desde el usuario
        System.out.println("Ingrese el nombre del producto a buscar:");
        String nombreProducto = scanner.next();

        // Buscar el producto en el inventario
        Inventario inventario = Inventario.obtenerInstancia();
        Producto productoEncontrado = inventario.buscarProducto(nombreProducto);

        // Mostrar los detalles del producto si se encuentra
        if (productoEncontrado != null) {
            System.out.println("Producto encontrado:");
            System.out.println("Nombre: " + productoEncontrado.getNombre());
            System.out.println("Precio: " + productoEncontrado.getPrecio());
            System.out.println("Stock: " + productoEncontrado.getStock());
        } else {
            System.out.println("Producto no encontrado en el inventario.");
        }
    }
}
