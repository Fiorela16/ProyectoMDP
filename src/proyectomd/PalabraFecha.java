/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomd;

/**
 *
 * @author fiore
 */
public class PalabraFecha {
    private String fecha;
    private String palabra;

    public PalabraFecha(String fecha, String palabra) {
        this.fecha = fecha;
        this.palabra = palabra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }


}
