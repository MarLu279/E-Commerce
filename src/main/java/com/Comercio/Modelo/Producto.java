package com.Comercio.Modelo;

import java.util.Objects;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    public Producto(){}

    public Producto(int idProducto){
        this.idProducto = idProducto;
    }

    public Producto(String nombre, String descripcion, double precio, int stock){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(int idProducto, String nombre, String descripcion, double precio, int stock){
        this(nombre, descripcion, precio, stock);
        this.idProducto = idProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;
        return idProducto == producto.idProducto && Double.compare(precio, producto.precio) == 0 && stock == producto.stock && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion);
    }

    @Override
    public int hashCode() {
        int result = idProducto;
        result = 31 * result + Objects.hashCode(nombre);
        result = 31 * result + Objects.hashCode(descripcion);
        result = 31 * result + Double.hashCode(precio);
        result = 31 * result + stock;
        return result;
    }
}
