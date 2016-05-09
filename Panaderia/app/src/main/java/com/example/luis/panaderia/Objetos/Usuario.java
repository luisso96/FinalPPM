package com.example.luis.panaderia.Objetos;

public class Usuario {

    private int id;
    String usuario;
    String contrasenya;

    public Usuario(int id, String usuario, String contrasenya) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenya = contrasenya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}
