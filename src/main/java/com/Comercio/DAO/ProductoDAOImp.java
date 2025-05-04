package com.Comercio.DAO;

import com.Comercio.Modelo.Producto;
import com.Comercio.Util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImp implements IProductoDAO{
    @Override
    public void insertar(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto(nombre, descripcion, precio, stock) "
                    + "VALUES(?, ?, ?, ?)";
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas == 0){
                throw new SQLException("[ERROR] No se inserto el producto");
            }
        } catch (SQLException | IOException e){
            throw new SQLException("[ERROR] Al insertar producto" + e.getMessage(), e);
        }
    }

    @Override
    public Producto buscarPorId(int idProducto) throws SQLException {
        String sql = "SELECT * FROM producto WHERE idProducto = ?";
        Producto producto = null;
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("idProducto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setStock(rs.getInt("stock"));
                }
            }
        } catch (SQLException | IOException e){
            throw new SQLException("[ERROR] Al recuperar el producto buscado " + e.getMessage(), e);
        }
        return producto;
    }

    @Override
    public List<Producto> listarProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto ORDER BY idProducto";
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        }catch (SQLException | IOException e){
            throw new SQLException("[ERROR] Al listar productos " + e.getMessage(), e);
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, stock = ? "
                + "WHERE idProducto = ?";
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdProducto());
            int modificado = ps.executeUpdate();
            if (modificado == 0){
                throw new SQLException("[ERROR] Producto no modificado");
            }
        } catch (SQLException | IOException e){
            throw new SQLException("[ERROR] Al intentar modificar producto " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(int idProducto) throws SQLException {
        String sql = "DELETE FROM producto WHERE idProducto = ?";
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idProducto);
            int eliminado = ps.executeUpdate();
            if(eliminado == 0){
                throw new SQLException("[ERROR] Al eliminar producto");
            }
        } catch (SQLException | IOException e){
            throw new SQLException("[ERROR] No se puede eliminar producto " + e.getMessage(), e);
        }
    }
}
