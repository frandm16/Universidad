//Dorado Maldonado, Francisco
//Ing. Informatica Grupo A

public class Paciente {
  private String nombre;
  private String apellido1;
  private String apellido2;
  private int edad;
  private String dni;


  public Paciente(String a,String b,String c,int d,String e){
    nombre=a;
    apellido1=b;
    apellido2=c;
    edad=d;
    if(edad<0){
      throw new RuntimeException("La edad no puede ser negativa");
    }
    dni=e;
  }

  public String getNombreCompleto(){
    return nombre+" "+apellido1+" "+apellido2;
  }

  public int getEdad(){
    return edad;
  }

  public String getDNI(){
    return dni;
  }

  @Override
  public String toString() {
    String formato= nombre.charAt(0)+apellido1.charAt(0)+apellido2.charAt(0)
            +dni.substring(5,7);
    return formato;
  }
}
