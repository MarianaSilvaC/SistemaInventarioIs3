import java.util.*;

/**
 *
 * @author msilvac
 * @author Luis Hernandez
 * @author Karolina Badilla
 */


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Inventario inventario = Inventario.obtenerInstancia();
        FabricaComandos fabrica = new FabricaComandos(scanner, inventario);

        // Instanciar observadores
        Observador notificador = new NotificadorInventario();
        inventario.agregarObservador(notificador);
        // Menú principal
        while (true) {
            System.out.println("1. Agregar producto");
            System.out.println("2. Editar producto");
            System.out.println("3. Buscar producto");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Simular venta");
            System.out.println("6. Mostrar ganancias");
            System.out.println("7. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Comando comandoAgregar = fabrica.crearComando("AGREGAR");
                    comandoAgregar.ejecutar();
                    break;
                case 2:
                    Comando comandoEditar = fabrica.crearComando("EDITAR");
                    comandoEditar.ejecutar();
                    break;
                case 3:
                    Comando comandoBuscar = fabrica.crearComando("BUSCAR");
                    comandoBuscar.ejecutar();
                    break;
                case 4:
                    Comando comandoMostrar = fabrica.crearComando("MOSTRAR");
                    comandoMostrar.ejecutar();
                    break;
                case 5:
                    Comando comandoVender = fabrica.crearComando("VENDER");
                    comandoVender.ejecutar();
                    break;
                case 6:
                    Comando comandoMostrarGanancias = fabrica.crearComando("GANANCIAS");
                    comandoMostrarGanancias.ejecutar();
                    break;
                case 7:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}
