class NotificadorInventario implements Observador {

    @Override
    public void actualizar() {
        System.out.println("Notificación: Se han realizado cambios en el inventario.");
    }
}