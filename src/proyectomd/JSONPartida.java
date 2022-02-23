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
import static proyectomd.JSONPalabraFecha.diasSegMes;

/**
 *
 * @author fiore
 */
public class JSONPartida {
    public static ArrayList<Partida> parts = new ArrayList<Partida>();

    public static void agrandarJSON(Partida part){
        Gson gson = new Gson();


        String jsonPast= leerJSON();
        String json = gson.toJson(part);

        jsonPast = jsonPast.substring(0, (jsonPast.length()-1));
        

        if(jsonPast.length()!=1){
            jsonPast = jsonPast + ",\n";
        }
        json = jsonPast + json + "]";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("partidas.json"))) {
            bw.write(json);

        } catch (IOException ex) {
            System.err.print(ex);
        }
    }


    public static String leerJSON(){
        Gson gson = new Gson();
        String fichero = "";

        try (BufferedReader br = new BufferedReader(new FileReader("partidas.json"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            parts = gson.fromJson(fichero, new TypeToken<List<Partida>>(){}.getType());     

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return fichero;

    }


    public static void borraHistorial(){
        Gson gson = new Gson();

        String json = "[]";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("partidas.json"))) {
            bw.write(json);
            Juego.escribirConColor(Juego.ANSI_RED ,"¡¡ HISTORIAL DE PARTIDAS ELIMINADO !!\n");
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }
}
