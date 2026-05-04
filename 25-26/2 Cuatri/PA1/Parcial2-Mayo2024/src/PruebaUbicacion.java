import procesiones.Ubicacion;

public class PruebaUbicacion {
    public static void main(String[] args) {
        Ubicacion ub1 = new Ubicacion("Larios", 210, 1000);
        Ubicacion ub2 = new Ubicacion("Molina Larios", 270, 1900);

        System.out.println(ub1);
        System.out.println(ub2);

        if(ub1.equals(ub2)){
            System.out.println("Las ubicaciones son iguales");
        } else{
            System.out.println("Las ubicaciones no son iguales");
        }

        try{
            Ubicacion ub3 = new Ubicacion(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
