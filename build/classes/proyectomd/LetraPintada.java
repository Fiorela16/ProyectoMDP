/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomd;

/**
 *
 * @author fiore
 */
public class LetraPintada {
    private char letra;
    private String color ;
    private boolean verde ;

    public LetraPintada(char letra) {
        this.letra = letra;
        color = "\u001B[30m";
        verde = false;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public boolean isVerde() {
        return verde;
    }

    public void setVerde(boolean verde){
        this.verde = verde;
    }
}
