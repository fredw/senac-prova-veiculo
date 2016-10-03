package com.fred.domain;

public class VeiculoDomain extends AbstractDomain {

    private MarcaDomain marca;
    private String modelo;
    private String categoria;
    private int ano;
    private String cor;

    public MarcaDomain getMarca() {
        return marca;
    }

    public void setMarca(MarcaDomain marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
