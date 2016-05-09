package com.example.luis.dibujosaleatorios;


public class ObjetosSpinner {

    // OBJETOS
    private String figura;
    private String formula;
    private int imagen;

    public ObjetosSpinner(String figura, String formula, int imagen) {
        this.figura = figura;
        this.formula = formula;
        this.imagen = imagen;
    }

    public String getFigura() {
        return figura;
    }

    public String getFormula() {
        return formula;
    }

    public int getImagen() {
        return imagen;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
