/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomd;

import java.util.ArrayList;

/**
 *
 * @author fiore
 */
public class Partida {
    private String fechaHora;
    private boolean win;
    private String palabra;
    private ArrayList<String> intentos; 

    public Partida(String fechaHora, boolean win, String palabra, ArrayList<String> intentos) {
        this.fechaHora = fechaHora;
        this.win = win;
        this.palabra = palabra;
        this.intentos = intentos;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fecha) {
        this.fechaHora = fecha;
    }


    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public ArrayList<String> getIntentos() {
        return intentos;
    }

    public void setIntentos(ArrayList<String> intentos) {
        this.intentos = intentos;
    }


}
