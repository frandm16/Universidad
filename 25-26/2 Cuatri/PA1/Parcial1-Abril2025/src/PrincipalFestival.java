import festivales.Actuacion;
import festivales.Festival;

/**
 * Clase principal para probar la funcionalidad del sistema de gestión de festivales.
 * Esta clase contiene laa pruebas de la clase Festival.
 */
public class PrincipalFestival {
    /**
     * Programa principal que ejecuta pruebas sobre la clase Festival.
     * Las pruebas incluyen la creación de objetos, manipulación de sus atributos
     * y verificación del comportamiento esperado de los métodos.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Prueba de la clase Festival
        System.out.println("\n=== PRUEBA DE LA CLASE FESTIVAL ===");
        try {
            // Creación de un nuevo festival
            Festival festival = new Festival("Rock in Rio", "Madrid");
            System.out.println("Festival creado: " + festival.getNombre() + " en " + festival.getCiudad());

            // Agregamos las actuaciones al festival para probar la gestión de colisiones
            festival.agregarActuacion("AC/DC", "Escenario Principal", 18,2);  // AC/DC, 18h
            festival.agregarActuacion("Iron maiden", "Escenario Principal", 21,2);  // Iron Maiden, 21h
            festival.agregarActuacion("Rammstein", "Escenario 2", 15,2);  // Rammstein, 15h
            festival.agregarActuacion("Slipknot", "Escenario 2", 13,2);  // Slipknot, 13h - solapamiento
            festival.agregarActuacion("Iron Maiden", "Escenario 2", 12,2);  // Iron Maiden, 12h - elimina la otra actuación de Iron maiden
            festival.agregarActuacion("Queen", "Escenario Principal", 21,2);  // Queen, 22h

            // Mostramos el estado actual del festival con todas las actuaciones
            System.out.println("Festival con actuaciones: " + festival);

            // Probamos la búsqueda de actuaciones por escenario
            //System.out.println("Actuaciones en Escenario Principal: " + festival.actuacionesEn("Escenario Principal"));
            //System.out.println("Actuaciones en Escenario 2: " + festival.actuacionesEn("Escenario 2"));

            // Probamos la eliminación de una actuación y verificamos el resultado
            festival.eliminarActuacion("Rammstein");  // Eliminamos a Rammstein
            System.out.println("Festival después de eliminar Rammstein: " + festival);

        } catch (Exception e) {
            // Captura y muestra de errores no esperados
            System.out.println("Error no esperado en la prueba de Festival: " + e.getMessage());
            e.printStackTrace();
        }
    }
}