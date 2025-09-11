/**
 * Francisco Dorado Maldonado
 * Ingenieria Informatica Grupo A
 */
import salud.*;

public class PruebaConsulta {

	public static void main(String[] args) {
		String[] arrayPacientes1 =
			{"Pablo Ruiz Picasso(18)12345678A",
             "Josefa Flores Manzano(25)12348765C",
             "Salvador Rueda Santos(10)87654321Z"};
		
		String[] arrayPacientes2 =
            {"María Zambrano Alarcón(45)12341234F",
             "Miguel de Cervantes(68)22222222B"};
		
		// Se crea una consulta con los pacientes de arrayPacientes1.
		Consulta primaria = new Consulta(arrayPacientes1);
		
		// Se añaden los pacientes del arrayPacientes2 a la consulta creada antes.
		primaria.agregarPacientes(arrayPacientes2);
		
		// Se muestra por consola la consulta
		System.out.println(primaria);
		
		// Se muestran por consola los pacientes menores
		System.out.println("Pacientes menores: " + primaria.menores());
		
		// Se muestran por consola los pacientes entre 15 y 50 años
		SeleccionPacientes sp = new ConEdadEntre(15,50);
		System.out.println("Pacientes entre 15 y 50 años: " + primaria.seleccionar(sp));
		
		// Se muestran por consola los pacietes menores 
		sp = new Menores();
		System.out.println("Pacientes menores: " + primaria.seleccionar(sp));
		
	}
	
	/* SALIDA ESPERADA:
	
	Pablo Ruiz Picasso 678 : Josefa Flores Manzano 765 : Salvador Rueda Santos 321 : Maria Zambrano Alarcón 234 : Miguel de Cervantes 222 }
	Pacientes menores: [Salvador Rueda Santos 321]
	Pacientes entre 15 y 50 años: [Pablo Ruiz Picasso 678, Josefa Flores Manzano 765, Maria Zambrano Alarcón 234]
	Pacientes menores: [Salvador Rueda Santos 321]
	
	*/
}
