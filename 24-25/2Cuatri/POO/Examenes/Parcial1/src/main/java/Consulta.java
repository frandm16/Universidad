//Dorado Maldonado, Francisco
//Ing. Informatica Grupo A

import java.util.ArrayList;
import java.util.List;

public class Consulta {
  private Especialidad especialidad;
  protected List<Paciente> pacientes;
  private List<String> llamadas;

  public Consulta(Especialidad a){
    especialidad=a;
    pacientes= new ArrayList<>();
    llamadas= new ArrayList<>();
  }

  public Especialidad getEspecialidad(){
    return especialidad;
  }

  public List<Paciente> getPacientes() {
    return pacientes;
  }

  public List<String> getLlamadas() {
    return llamadas;
  }

  public void nuevoPaciente(Paciente paciente){
    pacientes.addLast(paciente);
  }

  public void llamarPaciente(){
    if(pacientes.size()>0){
      llamadas.addFirst(String.valueOf(pacientes.getFirst()));
    }
  }
  public void atenderPaciente(){
  if(!(pacientes.getFirst().equals(llamadas.getFirst())) && llamadas.size()>0){
    throw new RuntimeException("El paciente no ha sido llamado");
  }
  if(llamadas.size()>0){
    pacientes.removeFirst();
  }
  }

  public boolean estaPacienteConDNI(String dni){
    boolean encontrado = false;
    int i = 0;
    while(i<pacientes.size() && !encontrado){
      if(pacientes.get(i).getDNI().equalsIgnoreCase(dni)){
        encontrado=true;
      }
      i++;
    }
    return encontrado;
  }

  @Override
  public String toString() {
    return especialidad+" ["+pacientes.size()+" en espera - "+llamadas.size()+" atendidos]";
  }
}
