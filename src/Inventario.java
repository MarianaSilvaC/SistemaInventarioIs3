import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Observador> getObservadores(){
        return observadores;
    }
}