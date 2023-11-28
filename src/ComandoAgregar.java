import java.util.Scanner;

class ComandoAgregar implements Comando {

    private Scanner scanner;

    public ComandoAgregar(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void ejecutar() {
        // Obtener detalles del nuevo producto desde el usuario
        System.out.println("Ingrese el nombre del producto:");
        String nombre = scanner.next();

        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();

        System.out.println("Ingrese el stock del producto:");
        int stock = scanner.nextInt();

        // Crear un nuevo producto
        Producto nuevoProducto = new Producto(nombre, precio, stock);

        // Agregar el nuevo producto al inventario
        Inventario inventario = Inventario.obtenerInstancia();
        inventario.agregarProducto(nuevoProducto);

        System.out.println("Producto agregado al inventario.");
    }
}