/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectomd;

import java.util.Scanner;


/**
 *
 * @author fiore
 */
public class ProyectoMDP {
    public static Juego j = new Juego();
    public static Scanner teclado = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //JSONPalabraFecha.crearJSON();
        GestorURL.descargarPalas5("Archivos/palabras5URL.txt");
	instrucciones();
    }

    public static void instrucciones(){
        int opcion = 0;
        String rpta = "S", rpta2 = " ";
            
         while(opcion != 5 && (rpta.compareToIgnoreCase("S")==0)){
                
                opcion = menu();

            if((opcion >= 5) && (opcion<1)){
                Juego.escribirConColor(Juego.ANSI_RED_BACKGROUND + Juego.ANSI_WHITE, "¡¡ INGRESE UNA OPCIÓN CORRECTA !!");
                System.out.print("\n");
                Juego.pausa();
            }else{
                if(opcion == 1){
                    Juego.salto();
                    mostrarHistorial();

                }else{
                    if(opcion == 2){
                    j = new Juego();
                        j.darPalabra();
                        j.juego();

                    }else{
                        if(opcion == 3){
                            System.out.println("Ingresa año hasta el que se relacionaran las palabras: ");
                            JSONPalabraFecha.last_Year = teclado.nextInt(); 
                            JSONPalabraFecha.crearJSON();
                        }else{
                            if(opcion == 4){
                                System.out.print("Esta seguro de Borrar el Historial de Partidas [S/N]: ");
                                rpta2 = teclado.next();
                                if (rpta2.compareToIgnoreCase("s")==0) {
                                    JSONPartida.borraHistorial();
                                }
                            }
                        }
                    }
                }
                if (opcion!=5) {
                    System.out.print("Desea volver al Menu Principal [S/N]: ");
                    rpta = teclado.next();
                }
            }
            Juego.salto();
        }
        Juego.salto();
        System.out.print("\t");
        Juego.escribirConColor(Juego.ANSI_CYAN_BACKGROUND + Juego.ANSI_BLACK , "\t ¡¡ GRACIAS POR JUGAR !!\t");
        Juego.escribirConColor(Juego.ANSI_BLUE , "\n\t\t    Vuelve Pronto :)\n");

    }

    public static void mostrarHistorial(){
        JSONPartida.leerJSON();
        Juego.escribirConColor(Juego.ANSI_RED, "\t\t\tHISTORIAL DE PARTIDAS\t\t\t\n\n");;
        for (int i = 0; i < JSONPartida.parts.size(); i++) {
            Juego.escribirConColor(Juego.ANSI_BLUE ," Fecha y Hora: ");
            System.out.println(JSONPartida.parts.get(i).getFechaHora());
            Juego.escribirConColor(Juego.ANSI_BLUE ," Palabra Buscada: ");
            System.out.println(JSONPartida.parts.get(i).getPalabra());
            Juego.escribirConColor(Juego.ANSI_BLUE ," Resultado: ");
            if (JSONPartida.parts.get(i).isWin()) {
                System.out.println("Logro");
            }else{
                System.out.println("Derrota");
            }
            Juego.escribirConColor(Juego.ANSI_BLUE ," Intentos:\n");
            for (int j = 0; j < JSONPartida.parts.get(i).getIntentos().size(); j++) {
                System.out.println("\t"+JSONPartida.parts.get(i).getIntentos().get(j));
            }
            Juego.lineaIntermedia();
        }
    }
public static int menu(){
    int opcion = 0;
    Juego.escribirConColor(Juego.ANSI_RED, "\t\t\tWORDLE (ES)\t\t\t\n\n");;
             System.out.println("1. Ver resultados anteriores");
             System.out.println("2. Jugar");
             System.out.println("3. Actualizar lista de Palabras y Fechas");
             System.out.println("4. Borrar Historial de Partidas");
             System.out.println("5. Salir\n");
            Juego.escribirConColor(Juego.ANSI_GREEN , "Ingrese Opcion:");
            System.out.print("    ");
            opcion = teclado.nextInt();
        return opcion;
}

}
