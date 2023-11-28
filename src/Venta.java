import java.util.Date;
import java.util.concurrent.atomic.DoubleAccumulator;

class Venta {
    private Producto producto;
    private int cantidad;
    private double ganancia;

    private Date fecha;

    private int id;

    public Venta(Producto producto, int cantidad, int id) {
        this.id = id;
        this.fecha = new Date();
        this.producto = producto;
        this.cantidad = cantidad;
        this.ganancia = producto.getPrecio() * (double) cantidad;
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

    public double getGanancia() {
        return ganancia;
    }

    public void aumentarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }

    public void combinarVenta(Venta otraVenta) {
        if (otraVenta.getProducto().equals(producto)) {
            cantidad += otraVenta.getCantidad();
        }
    }
}