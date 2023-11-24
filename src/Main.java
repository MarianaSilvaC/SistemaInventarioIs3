import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author msilvac
 */
class Producto {

    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Métodos getter y setter para precio y stock
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Inventario {

    private static Inventario instancia = null;
    private ArrayList<Producto> listaProductos;
    private ArrayList<Observador> observadores;

    private Inventario() {
        listaProductos = new ArrayList<>();
        observadores = new ArrayList<>();
    }

    public static Inventario obtenerInstancia() {
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    }

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.actualizar();
        }
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
        notificarObservadores();
    }

    public void editarProducto(Producto productoEditado) {
        // Implementación para editar productos en el inventario
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(productoEditado.getNombre())) {
                // Supongamos que quieres actualizar el precio y el stock del producto existente
                producto.setPrecio(productoEditado.getPrecio());
                producto.setStock(productoEditado.getStock());
                // Puedes agregar más lógica según tus necesidades
                break; // Una vez encontrado y editado, salir del bucle
            }
        }
        notificarObservadores();
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public Producto buscarProducto(String nombre) {
        // Implementación para buscar productos en el inventario
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null; // Retorna null si no se encuentra el producto con el nombre especificado
    }
}

// Interfaz Command
interface Comando {

    void ejecutar();
}

// Clases concretas que implementan Comando
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

// Clase que implementa Comando para mostrar el inventario
class ComandoMostrar implements Comando {

    private Inventario inventario;

    public ComandoMostrar(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public void ejecutar() {
        // Obtener la lista de productos y mostrarla
        List<Producto> listaProductos = inventario.getListaProductos();

        if (listaProductos != null) {
            System.out.println("Mostrando inventario:");
            for (Producto producto : listaProductos) {
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Stock: " + producto.getStock());
                System.out.println("---------------");
            }
        } else {
            System.out.println("El inventario está vacío.");
        }
    }
}

// Factoría de comandos
class FabricaComandos {

    private Scanner scanner;
    private Inventario inventario;

    public FabricaComandos(Scanner scanner, Inventario inventario) {
        this.scanner = scanner;
        this.inventario = inventario;
    }

    public Comando crearComando(String tipoComando) {
        switch (tipoComando) {
            case "AGREGAR":
                return new ComandoAgregar(scanner);
            case "EDITAR":
                return new ComandoEditar(scanner);
            case "BUSCAR":
                return new ComandoBuscar(scanner);
            case "MOSTRAR":
                return new ComandoMostrar(inventario);
            default:
                throw new IllegalArgumentException("Comando no válido");
        }
    }
}

// Interfaz Observer
interface Observador {

    void actualizar();
}

// Clase concreta que implementa Observador
class NotificadorInventario implements Observador {

    @Override
    public void actualizar() {
        System.out.println("Notificación: Se han realizado cambios en el inventario.");
    }
}

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
            System.out.println("5. Salir");

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
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}
