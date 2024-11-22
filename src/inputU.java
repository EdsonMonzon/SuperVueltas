import java.util.Scanner;

public class inputU {
    final static Scanner input=new Scanner(System.in);

    public static int entero(String mensaje){
        int numero=0;

        if(input.hasNextInt()){
            numero=input.nextInt();
        }else{
            System.out.println(mensaje);
            input.next();
            numero=entero(mensaje);
        }

        return numero;
    }
    public static int entero(){
        int numero=0;

        if(input.hasNextInt()){
            numero=input.nextInt();
        }else{
            System.out.println("El numero introducido no es valido, vuelve a intentarlo");
            input.next();
            numero=entero();
        }

        return numero;
    }
    public static int between(int a,int b, String mensaje){
        int numero=0;

        if(input.hasNextInt()){
            numero=input.nextInt();
            if(!(numero>=a && numero<=b)){
                System.out.println(mensaje);
                numero=between(a,b,mensaje);
            }
        }else{
            System.out.println("Trata de introducir un numero valido");
            input.next();
            numero=between(a,b,mensaje);
        }

        return numero;
    }

    public static int between(int a,int b){
        int numero=0;

        if(input.hasNextInt()){
            numero=input.nextInt();
        }else{
            System.out.println("El numero introducido no es valido , vuelva a intentarlo");
            input.next();
            numero=between(a,b);
        }

        return numero;
    }
}
