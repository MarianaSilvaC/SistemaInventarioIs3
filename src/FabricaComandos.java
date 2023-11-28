import java.util.Scanner;

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
