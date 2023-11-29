import java.util.Date;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * La clase Venta representa una transacción de venta de un producto.
 * Esta clase gestiona la información relacionada con la venta de un producto específico,
 * incluyendo el producto vendido, la cantidad vendida, la fecha de la venta, y el ID único
 * de la venta. Permite calcular el monto total de la venta en base al precio del producto y
 * la cantidad vendida. La clase también ofrece funcionalidades para aumentar la cantidad
 * de productos vendidos y combinar ventas de productos idénticos en una sola transacción.
 */
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
        this.ganancia = calcularMonto();
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