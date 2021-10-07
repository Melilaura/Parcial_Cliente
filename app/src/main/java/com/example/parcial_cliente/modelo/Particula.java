package com.example.parcial_cliente.modelo;

public class Particula {

    private int x, y;
    private String nombre;
    private int cantidad;
    private int r, g, b;
    private Boolean borrar;
    private Boolean crear;


    public Particula(int x, int y, String nombre, int cantidad, int r, int g, int b, boolean crear, boolean borrar) {
        this.x = x;
        this.y = y;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.r = r;
        this.g = g;
        this.b = b;
        this.crear = crear;
        this.borrar = borrar;

    }

    public Particula() {

    }

    public Boolean getBorrar() {
        return borrar;
    }

    public void setBorrar(Boolean borrar) {
        this.borrar = borrar;
    }

    public Boolean getCrear() {
        return crear;
    }

    public void setCrear(Boolean crear) {
        this.crear = crear;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
