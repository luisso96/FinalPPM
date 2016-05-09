package com.example.luis.panaderia.Objetos;

public class Pan {

    private String pan;
    private String precio;
    private int imagen;

    public Pan(String zona, String precio, int imagen) {
        this.pan = zona;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getPan() {
        return pan;
    }
    public String getPrecio() {
        return precio;
    }
    public int getImagen() {
        return imagen;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}
