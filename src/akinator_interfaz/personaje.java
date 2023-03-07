/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package akinator_interfaz;

/**
 *
 * @author Rosita
 */
public class personaje {
    private String nombre;
    private String caracteristica;

    public personaje(String nombre, String caracteristica) {
        this.nombre = nombre;
        this.caracteristica = caracteristica;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCaracteristica() {
        return caracteristica;
    }
}