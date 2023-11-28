import java.util.List;

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
