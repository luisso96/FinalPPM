package com.example.luis.panaderia.Objetos;

public class Pedido {

    private int id;
    private String pan;
    private String tamano;
    private String complemento;
    private String unidades;
    private String precio;
    private int imagen;

    public Pedido(){}

    public Pedido(int id, String pan, String tamano, String complemento, String unidades, String precio, int imagen) {
        this.id = id;
        this.pan = pan;
        this.tamano = tamano;
        this.complemento = complemento;
        this.unidades = unidades;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getPan() {
        return pan;
    }

    public int getId() {
        return id;
    }

    public String getTamano() {
        return tamano;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getUnidades() {
        return unidades;
    }

    public String getPrecio() {
        return precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
