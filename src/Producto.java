/**
 * La clase Producto representa un artículo en un sistema de inventario.
 * Contiene información esencial del producto, como su nombre, precio y cantidad en stock.
 * Proporciona métodos getter y setter para modificar y acceder a estas propiedades,
 * permitiendo una gestión flexible del producto en el inventario. Esta clase es fundamental
 * para operaciones relacionadas con productos en un sistema de ventas o gestión de inventario,
 * facilitando el seguimiento y actualización de la información del producto.
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