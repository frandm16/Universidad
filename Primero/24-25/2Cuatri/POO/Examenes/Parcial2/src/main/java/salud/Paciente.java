/**
 * Francisco Dorado Maldonado
 * Ingenieria Informatica Grupo A
 */
package salud;

/** 
 * Clase para representar pacientes de un centro de salud.
 */
public class Paciente {
	private static int EDAD_LIMITE=14;
	/**
	 * Nombre del paciente
	 */
	private String nombre;

	/**
	 * Edad del paciente
	 */
	private int edad;
	
	/**
	 * DNI del paciente
	 */
	private String dni;
	
	/**
	 * Constructor para crear objetos de la clase Paciente.
	 * 
	 * @param nombre    Nombre del paciente
	 * @param edad      Edad del paciente
	 * @param dni       DNI del paciente
	 */
	public Paciente(String nombre, int edad, String dni) {

		if (edad < 0)
			throw new SaludException("La edad no puede ser negativa");

		if(dni.length()!=9)
		this.nombre = nombre;
		this.edad = edad;
		this.dni = dni;
	}
	
	/**
	 * Método para obtener el nombre del paciente.
	 * @return Nombre del paciente
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Método para obtener la edad del paciente.
	 * 
	 * @return Edad del paciente
	 */
	public int getEdad() {
        return edad;
    }

	/**
	 * Método para obtener el DNI del paciente.
	 * @return DNI del paciente	
	 */
	public String getDni() {
		return dni;
	}

	public boolean equals(Object o){
		boolean res=false;
		if(o instanceof Paciente){
			Paciente p= Object o;
			res= nombre.equalsIgnoreCase(p.nombre) && dni.equalsIgnoreCase(p.dni);
		}
		return res;
	}

	public boolean esMenor(){
			return edad<=EDAD_LIMITE
	}
	public boolean equals
	/**
	 * Método para obtener la representación textual del paciente, dada por
	 * la primera letra del nombre, y los tres últimos digitos del DNI.
	 * 
	 * @return Representación textual del paciente
	 */
	public String toString() {
		return nombre + " " + dni.substring(dni.length()-4,dni.length()-1);
	}
	
}
