package com.example.mati.basedatos;

/**
 * Created by mati on 11/01/16.
 */
public class Cliente {
    private String nombre;
    private String telefono;

    public Cliente (String nombre, String telefono){
        this.setNombre(nombre);
        this.setTelefono(telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
