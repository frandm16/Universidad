import festivales.*;

/**
 * Clase principal para probar la funcionalidad del sistema de gestión de festivales.
 * Esta clase permite probar la clase Actuacion.
 */
public class PrincipalActuacion {
    /**
     * Programa principal que ejecuta pruebas sobre la clase Actuacion.
     * Las pruebas incluyen la creación de objetos, manipulación de sus atributos
     * y verificación del comportamiento esperado de los métodos.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Prueba de la clase Actuacion
        System.out.println("=== PRUEBA DE LA CLASE ACTUACION ===");
        try {
            // Creación de una actuación válida y muestra de sus datos
            Actuacion act1 = new Actuacion("Metallica", "Escenario Principal", 20, 2);
            System.out.println("Actuación creada: " + act1);

            // Prueba de métodos getter para acceder a los atributos
            System.out.println("Grupo: " + act1.getGrupo());
            System.out.println("Escenario: " + act1.getEscenario());
            System.out.println("HoraInicio: " + act1.getHoraInicio());
            System.out.println("HoraFin: " + act1.getHoraFin());

            // Prueba de métodos setter para modificar los atributos modificables
            act1.setHoraInicio(21);      // Cambia la hora de 20 a 21
            System.out.println("Actuación modificada: " + act1);

            // Prueba de validaciones: Intento de crear actuación con hora fuera de rango
            try {
                Actuacion actInvalida = new Actuacion("Inválido", "Escenario", 25, 2);
            } catch (RuntimeException e) {
                System.out.println("Error capturado correctamente: " + e.getMessage());
                //e.printStackTrace();
            }
            // Prueba de validaciones: Intento de crear actuación con duracion incorrecta
            try {
                Actuacion actInvalida = new Actuacion("Inválido", "Escenario", 22, 4);
            } catch (RuntimeException e) {
                System.out.println("Error capturado correctamente: " + e.getMessage());
                //e.printStackTrace();
            }
            // Prueba de validaciones: Intento de crear actuación que termine al dia siguiente de empezar
            try {
                Actuacion actInvalida = new Actuacion("Inválido", "Escenario", 22, 2);
            } catch (RuntimeException e) {
                System.out.println("Error capturado correctamente: " + e.getMessage());
                //e.printStackTrace();
            }
        } catch (Exception e) {
            // Captura de errores no esperados durante las pruebas
            System.out.println("Error no esperado en la prueba de Actuacion: " + e.getMessage());
        }
    }
}