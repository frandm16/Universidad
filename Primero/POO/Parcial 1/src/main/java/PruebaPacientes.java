//Dorado Maldonado, Francisco
//Ing. Informatica Grupo A

public class PruebaPacientes {

	public static void main(String[] args) {
		// Crear una consulta de especialidad PRIMARIA, que se denomine
		// consultaPrimaria.
 		Consulta consultaPrimaria=new Consulta(Especialidad.PRIMARIA);

		// Añadir los siguientes pacientes a la consulta:
		// 		Pablo Ruiz Picasso, 18 años, DNI 12345678A
		// 		Josefa Flores Manzano, 13 años, DNI 12348765C
		// 		Salvador Rueda Santos, 25 años, DNI 87654321B
		// 		María Zambrano Alarcón, 10 años, DNI 12348765C
			Paciente paciente1=new Paciente("Pablo","Ruiz","Picasso",18,"12345678A");
			consultaPrimaria.nuevoPaciente(paciente1);
		Paciente paciente2=new Paciente("Josefa","Flores","Manzano",13,"12348765C");
		consultaPrimaria.nuevoPaciente(paciente2);
		Paciente paciente3=new Paciente("Salvador","Rueda","Santos",25,"87654321B");
		consultaPrimaria.nuevoPaciente(paciente3);
		Paciente paciente4=new Paciente("María","Zambrano","Alarcón",10,"12348765C");
		consultaPrimaria.nuevoPaciente(paciente4);
	
		// Mostrar por consola la consulta
		System.out.println(consultaPrimaria);
		
		// Llamar al primer paciente a la consulta
		consultaPrimaria.llamarPaciente();
		
		// Comprobar si el paciente con DNI 87654321B se encuentra en la sala de espera
		consultaPrimaria.estaPacienteConDNI("87654321B");
	
		// Atender al paciente que corresponda
		consultaPrimaria.atenderPaciente();
		
		// Llamar y atender alternativamente a dos pacientes
		consultaPrimaria.llamarPaciente();
		consultaPrimaria.atenderPaciente();
		consultaPrimaria.llamarPaciente();
		consultaPrimaria.atenderPaciente();
	
		// Mostrar la consulta tras las acciones anteriores
		System.out.println(consultaPrimaria);
	
		// Comprobar de nuevo si el paciente con DNI 87654321B se encuentra en la sala de espera
		consultaPrimaria.estaPacienteConDNI("87654321B");
	
		// Mostrar por consola las llamadas realizadas en la consulta
		System.out.println(consultaPrimaria.getLlamadas());
	}

	/*
	 * --- Salida esperada si la consulta creada es de tipo Consulta:
	 * 
	 * PRIMARIA [4 en espera - 0 atendidos)] 
	 * El paciente con DNI 87654321B está en la sala de espera 
	 * PRIMARIA [1 en espera - 4 atendidos)] 
	 * El paciente con DNI 87654321B no está en la sala de espera 
	 * [SRS321, JFM765, JFM765, PRP678]
	 * 
	 * --- Salida esperada si la consulta creada es de tipo ConsultaPrioridadMenores:
	 * 
	 * PRIMARIA [4 en espera - 0 atendidos)] 
	 * El paciente con DNI 87654321B está en la sala de espera 
	 * PRIMARIA [1 en espera - 4 atendidos)] 
	 * El paciente con DNI 87654321B está en la sala de espera 
	 * [PRP678, MZA765, MZA765, JFM765]
	 * 
	 */

}
