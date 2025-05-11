package com.Comercio.DAO;

import com.Comercio.Modelo.Producto;

import java.sql.SQLException;
import java.util.List;

public interface IProductoDAO {
    void insertar (Producto producto) throws SQLException;

    Producto buscarPorId(int idProducto) throws SQLException;

    List<Producto> listarProductos() throws SQLException;

    void actualizar(Producto producto) throws SQLException;

    void eliminar(int idProducto) throws SQLException;
}
