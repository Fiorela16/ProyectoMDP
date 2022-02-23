/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomd;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fiore
 */
public class JSONPalabraFecha {
    public static ArrayList<PalabraFecha> palasFecha = new ArrayList<PalabraFecha>();
        public static final int FIRST_YEAR =2022;
        public static int last_Year =2026;
    /*public static void main(String[] args) {
            //JSONPalabraFecha.crearJSON();
            leerJSON();
            int tam = palasFecha.size();
        System.out.println("Tamaño de Lista:" + tam);
            for (int i = 0; i < tam; i++) {
                System.out.println(palasFecha.get(i).getFecha()+"->"+palasFecha.get(i).getPalabra());
        }
    }*/
    public static void crearJSON(){
        Gson gson = new Gson();
        PalabraFecha pf= new PalabraFecha("","");
          String aleatorio = ""; 
        int lis =0 , tam = 0;
        boolean uniq=true;

        GestorArchivo.obtenerPal5("Archivos/palabras5.txt");
        pf = new PalabraFecha("2021-12-31" , GestorArchivo.palAleatoria());
        String json ="[" + gson.toJson(pf);
        palasFecha.add(pf);

        for(int y = FIRST_YEAR;y<=last_Year;y++){
            for (int m = 1; m < 12; m++) {
                for (int d = 1; d <= diasSegMes(y,m); d++) {
                    lis =0;
                    tam=palasFecha.size();
                    aleatorio = GestorArchivo.palAleatoria();
                    do{
                        if(aleatorio==palasFecha.get(lis).getPalabra()){
                            lis=0;
                            aleatorio = GestorArchivo.palAleatoria();
                        }else{  
                            lis++;
                        }  
                    }while(lis<tam);
                    if (m<10) {
                        pf = new PalabraFecha(y+"-0"+m+"-"+d , aleatorio);
                    }else{
                        pf = new PalabraFecha(y+"-"+m+"-"+d , aleatorio);
                    }
                    
                    palasFecha.add(pf);

                    json = json + " ,\n " +  gson.toJson(pf);

                }  
            }
        }
        json = json + "]";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("palabras_por_fecha.json"))) {
            bw.write(json);

        } catch (IOException ex) {
            System.err.print(ex);
        }
    }
    public static void leerJSON(){
        Gson gson = new Gson();
        String fichero = "";

        try (BufferedReader br = new BufferedReader(new FileReader("palabras_por_fecha.json"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            palasFecha = gson.fromJson(fichero, new TypeToken<List<PalabraFecha>>(){}.getType());     

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public static int diasSegMes(int anio, int mes){
        int nD = 0;
        switch(mes){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: nD=31; break;
            case 2: if((anio%4==0 &&anio%100!=0)||anio%400==0)
                        nD = 29;
                    else
                        nD =28;
            default: nD=30;

        }
        return nD;
    }

    public static int buscarPos(String fechaActual){
        leerJSON();
        int pos = -1;
        //System.out.println(palasFecha.size());
        for (int i = 0; i < palasFecha.size(); i++) {
            //System.out.println(palasFecha.get(i).getFecha() + " ==" + fechaActual);
            if(palasFecha.get(i).getFecha().compareTo(fechaActual)==0){
                pos = i;
            }
        }
        //System.out.println(pos);
        return pos;
    }  
}

