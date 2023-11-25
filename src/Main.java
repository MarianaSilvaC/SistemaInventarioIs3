import java.util.*;

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
    private List<Venta> ventas;

    // Método para registrar ganancias


    Inventario() {
        listaProductos = new ArrayList<>();
        observadores = new ArrayList<>();
        ventas = new ArrayList<>();

        Producto leche = new Producto("Leche", 25.0, 50);
        Producto cereal = new Producto("Cereal", 50.0, 30);
        Producto yogurt = new Producto("Yogurt", 47.0, 40);

        listaProductos.add(leche);
        listaProductos.add(cereal);
        listaProductos.add(yogurt);
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
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(productoEditado.getNombre())) {
                producto.setPrecio(productoEditado.getPrecio());
                producto.setStock(productoEditado.getStock());
                break;
            }
        }
        notificarObservadores();
    }

    public void registrarVenta(Producto producto, int cantidad) {
        boolean ventaExistente = false;
        for (Venta venta : ventas) {
            if (venta.getProducto().equals(producto)) {
                venta.aumentarCantidad(cantidad);
                ventaExistente = true;
                break;
            }
        }

        if (!ventaExistente) {
            ventas.add(new Venta(producto, cantidad));
        }

        notificarObservadores();
    }
    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public Producto buscarProducto(String nombre) {
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    public List<Venta> getVentas() {
        return ventas;
    }
    public double calcularMontoVenta(Producto producto, int cantidad) {
        return producto.getPrecio() * cantidad;
    }

    public double calcularVentasTotales() {
        int ventasTotales = 0;
        for (Venta venta : ventas) {
            ventasTotales += venta.getCantidad();
        }
        return ventasTotales;
    }
    public double calcularGananciasTotales() {
        double gananciasTotales = 0.0;
        for (Venta venta : ventas) {
            gananciasTotales += venta.calcularMonto();
        }
        return gananciasTotales;
    }
}
class Venta {
    private Producto producto;
    private int cantidad;

    public Venta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double calcularMonto() {
        return producto.getPrecio() * cantidad;
    }

    public void aumentarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }
    public void combinarVenta(Venta otraVenta) {
        if (otraVenta.getProducto().equals(producto)) {
            cantidad += otraVenta.getCantidad();
        }
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

class ComandoVender implements Comando {

    private Scanner scanner;

    public ComandoVender(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void ejecutar() {
        System.out.println("Ingrese el nombre del producto a vender:");
        String nombreProducto = scanner.next();

        Inventario inventario = Inventario.obtenerInstancia();
        Producto productoAVender = inventario.buscarProducto(nombreProducto);

        if (productoAVender != null) {
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

            // Muestra las ventas combinadas
            for (Venta venta : ventasComb) {
                System.out.println("Producto: " + venta.getProducto().getNombre());
                System.out.println("Cantidad: " + venta.getCantidad());
                System.out.println("Monto: $" + venta.calcularMonto());
                System.out.println("---------------");
            }

            // Muestra ganancias totales
            double gananciasTotales = 0.0;
            for (Venta venta : ventasComb) {
                gananciasTotales += venta.calcularMonto();
            }
            System.out.println("Ganancias totales: $" + gananciasTotales);
        } else {
            System.out.println("No hay ventas registradas.");
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
            case "VENDER":
                return new ComandoVender(scanner);
            case "GANANCIAS":
                return new ComandoMostrarGanancias(inventario);
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
