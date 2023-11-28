
class Venta {
    private Producto producto;
    private int cantidad;
    private double ganancia;

    public Venta(Producto producto, int cantidad) {
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
    public void combinarVenta(Venta otraVenta) {
        if (otraVenta.getProducto().equals(producto)) {
            cantidad += otraVenta.getCantidad();
        }
    }
}