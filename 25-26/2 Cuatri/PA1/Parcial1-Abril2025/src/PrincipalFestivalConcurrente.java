import festivales.Actuacion;
import festivales.FestivalConcurrente;

/**
        * Clase principal para probar la funcionalidad del sistema de gestión de festivales.
        * Esta clase contiene el método main que ejecuta pruebas sobre la clase FestivalConcurrente.
 */
public class PrincipalFestivalConcurrente {
    /**
     * Programa principal que ejecuta pruebas sobre la clase FestivalConcurrente.
     * Las pruebas incluyen la creación de objetos, manipulación de sus atributos
     * y verificación del comportamiento esperado de los métodos.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Prueba de la clase FestivalConcurrente
        System.out.println("\n=== PRUEBA DE LA CLASE FESTIVALCONCURRENTE ===");
        try {
            // Creación de un festival con programación secuencial
            FestivalConcurrente festivalSec = new FestivalConcurrente("Rock in Rio", "Madrid");
            System.out.println("Festival concurrente creado: " + festivalSec.getNombre() + " en " + festivalSec.getCiudad());

            // Agregamos las actuaciones al festival para probar la gestión de colisiones
            festivalSec.agregarActuacion("AC/DC", "Escenario Principal", 18,2);  // AC/DC, 18h
            festivalSec.agregarActuacion("Iron maiden", "Escenario Principal", 21,2);  // Iron Maiden, 21h
            festivalSec.agregarActuacion("Rammstein", "Escenario 2", 18,2);  // Rammstein, 19h
            festivalSec.agregarActuacion("Slipknot", "Escenario 2", 17,2);  // Slipknot, 15h
            festivalSec.agregarActuacion("Iron Maiden", "Escenario Principal", 17,2);  // Iron Maiden, 17h (mismo grupo que act2.)
            festivalSec.agregarActuacion("Queen", "Escenario 2", 21,2);  // Queen, 21h

            // Mostramos el estado del festival secuencial, que incluye las actuaciones programadas y no programadas
            System.out.println("Festival concurrente con actuaciones: " + festivalSec);

            // Verificamos las actuaciones por escenario (solo muestra las programadas)
            //System.out.println("Actuaciones en Escenario Principal: " + festivalSec.actuacionesEn("Escenario Principal"));
            //System.out.println("Actuaciones en Escenario 2: " + festivalSec.actuacionesEn("Escenario 2"));

        } catch (Exception e) {
            // Captura y muestra detallada de errores no esperados
            System.out.println("Error no esperado en la prueba de FestivalConcurrente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}