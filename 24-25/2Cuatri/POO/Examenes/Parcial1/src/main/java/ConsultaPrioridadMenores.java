//Dorado Maldonado, Francisco
//Ing. Informatica Grupo A

public class ConsultaPrioridadMenores extends Consulta{
  private static int EDAD_LIMITE=14;

  public ConsultaPrioridadMenores (Especialidad a){
    super(a);

  }
  public void nuevoPaciente(Paciente paciente){
    boolean prioridad=false;
    int i=pacientes.size()-1;
    while(i>=0 && !prioridad){
      if(pacientes.get(i).getEdad()<EDAD_LIMITE){//busca si hay algun menor del limite,del final hacia delante
        pacientes.add(i+1,paciente);//si lo encuentra se añade detras de el
        prioridad=true;
      }
      i--;
    }
    if(prioridad==false){//si no hay nadie menor que el limite se añade primero
      pacientes.addFirst(paciente);
    }
  }

}
