/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomd;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.Normalizer;

/**
 *
 * @author fiore
 */
public class GestorURL {
    public static String url = "https://raw.githubusercontent.com/javierarce/palabras/master/listado-general.txt";
    public static final int detAleatorio = 5;
    public static final int cantNumeros = 365;

    public static void mostrarContenido(String url){
        try{
            URL ficheroUrl = new URL(url);
           BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));

           String linea;
           while ((linea = in.readLine()) != null){
              System.out.println(linea);
            
           }
            in.close(); // Cerramos la conexión  
        }catch(Exception e){
            System.err.print(e);
        }
    }

    public static void descargar(String ficheroDestino){
        try{
            URL ficheroUrl = new URL(url);
            InputStream inputStream = ficheroUrl.openStream();
            OutputStream outputStream = new FileOutputStream(ficheroDestino); // path y nombre del nuevo fichero creado

            byte[] b = new byte[2048];
            int longitud;

            while ((longitud = inputStream.read(b)) != -1) {
               outputStream.write(b, 0, longitud);
            }

            inputStream.close();  // Cerramos la conexión entrada
            outputStream.close(); // Cerramos la conexión salida
        }catch(Exception e){
            System.err.print(e);
        }
       
    }
    public static void descargarPalas5(String ficheroDestino){
        int consonantes=0, vocales=0, espacios=0, especial=0;
        int numAleatorio = 0, contador = 0;
        try{
            
            URL ficheroUrl = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
            FileWriter fichero = null;
            PrintWriter pw = null;

            fichero = new FileWriter(ficheroDestino);
            pw = new PrintWriter(fichero);

           String linea;
           while ((linea = in.readLine()) != null){
                if(linea.length()==5){     
                    linea = Normalizer.normalize(linea, Normalizer.Form.NFD)
                                    .replaceAll("[\\u0300-\\u036f]", "")
                                    .toUpperCase();
                      // remplaza todo lo que sea diferente a espacios y calcula su longitud
                    espacios = linea.replaceAll("[\\S]", "").length();
                    // remplaza todo lo que sea diferente a una vocal (a, e, i, o, u)
                    vocales = linea.replaceAll("[^AEIOU]", "").length();
                    // remplaza todo lo que no sea una letra y le resta las vocales
                    consonantes = linea.replaceAll("[^A-ZÑ]", "").length() - vocales;

                    especial = linea.length() - espacios - vocales - consonantes;
                    numAleatorio = (int)(Math.random()*12);
                    if (((especial==0)&& (numAleatorio==detAleatorio))&&(contador<cantNumeros))  {
                        //System.out.println((contador+1) + "."+numAleatorio + " ->" + linea);
                            pw.println(linea);
                        contador++;
                    }

                }
           }
            fichero.close();
            in.close(); // Cerramos la conexión  
        }catch(Exception e){
            System.err.print(e);
        }
    }
}
