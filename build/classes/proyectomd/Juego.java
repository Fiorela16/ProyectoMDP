/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyectomd;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author fiore
 */
public class Juego {
    public static final String ANSI_RESET = "\u001B[0m";
    //Colores de letra
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    //Colores de fondo
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final int N_INTENTOS =  6;
    public static final int N_CARACTER = 5;
    public int intento = 0;
    public String palabraBuscada;  
    public List<String> palabras5;

    public static LetraPintada car_ing[][];
    public static char let_teclado[] = {'Q', 'W' , 'E' , 'R' , 'T', 'Y' , 'U','I','O','P', 'A', 'S' , 'D' , 'F' , 'G', 'H' , 'J','K','L','Ñ','Z', 'X' , 'C' , 'V' , 'B', 'N', 'M' };
    public String fechaActual = "";
    public boolean win = false;
    DateTimeFormatter dtWin = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    String fechaHoraWin;

    public static ArrayList<String> palabs5 = new ArrayList<String>();

    public Juego() {
    car_ing = new LetraPintada[N_INTENTOS][N_CARACTER];
            for( int i=0;i<N_INTENTOS; i++){
                    for( int j=0;j<N_CARACTER; j++){
                            car_ing[i][j] = new LetraPintada(' ');
                    }	
            }
    }


    public void darPalabra(){
        int lis =0;
        GestorArchivo.obtenerPal5("Archivos/palabras5URL.txt");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fechaActual= dtf.format(LocalDateTime.now()).toString();
        int pos = JSONPalabraFecha.buscarPos(fechaActual),tam =JSONPalabraFecha.palasFecha.size();

        palabraBuscada = GestorArchivo.palAleatoria();
        if(pos>=0){
            lis = pos;
            do{
               if(palabraBuscada.compareTo(JSONPalabraFecha.palasFecha.get(lis).getPalabra())==0){
                System.out.println(palabraBuscada + " -> " + JSONPalabraFecha.palasFecha.get(lis).getFecha()+ ":"+ JSONPalabraFecha.palasFecha.get(lis).getPalabra());
                   lis=pos;
                   palabraBuscada = GestorArchivo.palAleatoria();
                }else{  
                     lis++;
                }  
            
            }while(lis<tam);
        }
       System.out.println(palabraBuscada);
    }

    public static void escribirConColor(String color ,String text){
            System.out.print(color + text + ANSI_RESET);
            System.out.print("");
    }

    public void juego(){

        String palabra;
        Scanner input = new Scanner(System.in);
        
        int error =0;
        while(intento<N_INTENTOS && !win){
            salto();

            escribirConColor(ANSI_RED, "\t\tWORDLE (ES)");
            System.out.print("\t\t\t\n\n");
            escribirConColor(ANSI_PURPLE," Intento N° " + (intento+1) + " \n");
            frameJuego();
            System.out.println("\n");
            teclado();
            System.out.print("\n\nINGRESE PALABRA : " );
            palabra = input.nextLine();
            error=validarPalabra(palabra);
            if(error==1){
                    palabra = cambiaPalabra(palabra);
                    pintaPalabra(palabra);
                    intento ++;

                    if(palabra.compareToIgnoreCase(palabraBuscada)==0){
                        salto();
                        fechaHoraWin = dtWin.format(LocalDateTime.now()).toString();
                        escribirConColor(ANSI_RED, "\t\tWORDLE (ES)\n");
                        escribirConColor(ANSI_PURPLE," N° Intentos Usados:  " + intento + " \n");
                        frameJuego();            
                        System.out.print("\n\n\t");
                        escribirConColor(ANSI_RED, "\t¡¡ LO LOGRASTE !!\t\n\n");	
                            win=true;		
                    }
                    pausa();
            }else{
                if(error ==-1){
                    escribirConColor(ANSI_RED, "INGRESE UNA CADENA DE TEXTO COMPUESTA POR 5 CARACTERES\n\n");
                    pausa();
                }else{
                    escribirConColor(ANSI_RED, "SOLO SE PERMITE INGRESAR LETRAS , ESTAS DEBEN SER SI TILDE\n\n");
                    pausa();
                }
            }

        }

        if(intento >=6){
            fechaHoraWin = dtWin.format(LocalDateTime.now()).toString();
            salto();
            escribirConColor(ANSI_RED ,"\t\tWORDLE (ES) \n");
            escribirConColor(ANSI_PURPLE," PALABRA NO ENCONTRADA:  " + palabraBuscada + " \n");
            frameJuego();          
            System.out.print("\n\n\t");
            escribirConColor(ANSI_RED, "\tFALLASTE :C\t\n\n");	
        }

        guardarPartida();

    }
    public static void salto(){
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public static void pausa(){
            System.out.println("Press ENTER To Continue...");
              new java.util.Scanner(System.in).nextLine();
    }

    public int validarPalabra(String pal){
            int ok = 1;

            pal = quitarEspacios(pal);
            if(pal.length() != N_CARACTER){
                    ok = -1;	
            }else{
                for(int i=0; i<N_CARACTER;i++){
                if(!Character.isLetter(pal.charAt(i))){
                    ok =-2;
                }
                }
            }

            return ok;
    }


    public String cambiaPalabra(String pal){
            //CONVERTIMOS A MAYÚSCULA
               pal = pal.toUpperCase();
                Normalizer.normalize(pal, Normalizer.Form.NFD);
                pal = pal.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");


            return pal;
    }

    public String quitarEspacios(String pal){
        int i;
        while(pal.charAt(0) == ' '){
            pal = pal.substring(1, pal.length());
            System.out.println(pal);
        }

        while(pal.charAt(pal.length()-1) == ' '){
            System.out.println(pal);
            pal = pal.substring(0,pal.length()-2);
        }

        return pal;
    }


    public void pintaPalabra(String pal){
            String colorLetra = ANSI_WHITE_BACKGROUND + ANSI_BLACK;

            for(int i =0 ; i<N_CARACTER; i++){
                    if(pal.charAt(i) == palabraBuscada.charAt(i)){
                            colorLetra = ANSI_GREEN_BACKGROUND + ANSI_BLACK;
                            car_ing[intento][i].setVerde(true);
                    }else{
                            for(int j = 0; j<N_CARACTER;j++){
                                    if((i!=j)&&(pal.charAt(i) == palabraBuscada.charAt(j))){
                                                    colorLetra = ANSI_YELLOW_BACKGROUND+ANSI_BLACK;
                                    }
                            }
                    }
                    car_ing[intento][i].setLetra(pal.charAt(i));
                    car_ing[intento][i].setColor(colorLetra);

                    colorLetra = ANSI_WHITE_BACKGROUND + ANSI_BLACK;
            }	
    }

    public static void lineaIntermedia(){
            for(int i = 0; i <N_CARACTER;i++){
                       System.out.print(" + - - -");
            }
            System.out.println(" +");
    }

    public void frameJuego(){
            for(int i=0;i<N_INTENTOS; i++){
                lineaIntermedia();
                for( int j=0;j<N_CARACTER; j++){
                    System.out.print(" |  ");

                    escribirConColor(car_ing[i][j].getColor()," "+car_ing[i][j].getLetra() + " ");
                    System.out.print(" ");
                }
                System.out.print(" | \n");	
            }
            lineaIntermedia();

    }
    public String buscaColorLetra(char car){
            String colorBusc = ANSI_CYAN_BACKGROUND + ANSI_BLACK;
            boolean esVerde=false;
            for(int i=0;i<intento; i++){
                    for( int j=0;j<N_CARACTER; j++){
                            if((!esVerde) &&(car == car_ing[i][j].getLetra())){
                                    colorBusc = car_ing[i][j].getColor();
                                    if(car_ing[i][j].isVerde()){
                                            esVerde =true;
                                    }
                            }
                    }	
            }
            return colorBusc;
    }
    public void teclado(){
            for(int i = 0; i<27; i++){
                if(let_teclado[i] =='Z'){
                    System.out.print("\t   ");
                }
                escribirConColor(buscaColorLetra(let_teclado[i]), "[   " + let_teclado[i]  + "   ]");
                if((i+1)%10==0){
                   System.out.println("");
                }
            }
    }

    public void guardarPartida(){
        String palab="";
        ArrayList<String> palEscritas = new ArrayList<String>();

        for (int i = 0; i < intento; i++) {
        palab="";
            for (int j = 0; j < N_CARACTER; j++) {
                palab = palab+car_ing[i][j].getLetra();
            }
            palEscritas.add(palab);
        }
        Partida part = new Partida(fechaHoraWin, win, palabraBuscada,  palEscritas);
        JSONPartida.agrandarJSON(part);
    }
}

