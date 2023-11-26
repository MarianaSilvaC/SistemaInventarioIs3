
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FabricaComandosTest {

    Scanner scanner;
    Inventario inventario;
    FabricaComandos fabrica;

    @BeforeEach
    void setUp() {
        scanner = new Scanner(System.in);
        inventario = Inventario.obtenerInstancia();
        fabrica = new FabricaComandos(scanner, inventario);

    }


    @Test
    void crearComandoEditar() {
        Comando comando = fabrica.crearComando("EDITAR");
        assertNotNull(comando, "El comando no fue creado correctamente");
    }

    @Test
    void crearComandoAgregar() {
        Comando comando = fabrica.crearComando("AGREGAR");
        assertNotNull(comando, "El comando no fue creado correctamente");
    }

    @Test
    void crearComandoBuscar() {
        Comando comando = fabrica.crearComando("BUSCAR");
        assertNotNull(comando, "El comando no fue creado correctamente");
    }






}