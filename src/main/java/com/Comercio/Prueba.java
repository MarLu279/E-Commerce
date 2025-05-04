package com.Comercio;

import com.Comercio.DAO.IProductoDAO;
import com.Comercio.DAO.ProductoDAOImp;
import com.Comercio.Modelo.Producto;

import java.sql.SQLException;
import java.util.List;

public class Prueba {
    public static void main(String[] args) {
        IProductoDAO productoDAO = new ProductoDAOImp();

        //Prueba insertar producto
//        Producto producto = new Producto("Nivel de burbuja de 60 cm",
//                "Nivel de aluminio con tres burbujas para mediciones horizontales, verticales y a 45 grados.",
//                18.30, 45);
//        try {
//            productoDAO.insertar(producto);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        //Prueba buscar por idProducto

//        Producto producto = null;
//        try {
//            producto = productoDAO.buscarPorId(2);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(producto);

        //Prueba listar productos
//        try {
//            List<Producto> productos = productoDAO.listarProductos();
//            productos.forEach(System.out::println);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        //Prueba Actualizar producto
//        Producto productoModificado = new Producto(3,"Taladro inalámbrico 18V",
//                "Taladro/atornillador inalámbrico con batería de litio y velocidad variable.",
//                79.99, 25);
//        try {
//            productoDAO.actualizar(productoModificado);
//            List<Producto> productos = productoDAO.listarProductos();
//            productos.forEach(System.out::println);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        //Prueba Eliminar producto
//        try {
//            productoDAO.eliminar(5);
//            List<Producto> productos = productoDAO.listarProductos();
//            productos.forEach(System.out::println);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }
}
