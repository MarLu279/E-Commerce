package com.Comercio.DAO;

import com.Comercio.Modelo.Usuario;
import com.Comercio.Util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImp implements IUsuarioDAO{
    @Override
    public void insertarUsuario(Usuario usuario) throws SQLException {
        if(usuario ==  null){
            throw new IllegalArgumentException("[ERROR] El usuario no puede ser nulo");
        }
        String sql = "INSERT INTO usuario(nombre, apellido, email, password, rol) "
                + "VALUES(?, ?, ?, ?, ?)";
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, usuario.getRol());
            int filasAlteradas = ps.executeUpdate();
            if(filasAlteradas == 0){
                throw new SQLException("[ERROR] No se inserto el usuario");
            }
        } catch (SQLException | IOException e) {
            throw new SQLException("[ERROR] Al insertar usuario " + e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> lisatarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY idUsuario";
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                usuarios.add(usuario);
            }

        } catch (SQLException | IOException e){
            throw new SQLException("[ERROR] Al listar los usuarios " + e.getMessage(), e);
        }
        return usuarios;
    }

    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE idUsuario = ?";
        Usuario usuario = null;
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idUsuario);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException | IOException e){
            throw new SQLException("[ERROR] Al recuperar el usuario buscado " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        Usuario usuario = null;
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException | IOException e){
            throw new SQLException("[ERROR] Al recuperar usuario " + e.getMessage(), e);
        }
        return usuario;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        if (usuario == null){
            throw new IllegalArgumentException("[ERROR] El usuario no puede ser nulo");
        }
        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, email = ?, password = ?, rol = ? "
                + "WHERE idUsuario = ?";
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, usuario.getRol());
            ps.setInt(6, usuario.getIdUsuario());
            int modificado = ps.executeUpdate();
            if (modificado == 0) {
                throw new SQLException("[ERROR] Usuario no modificado");
            }
        } catch (SQLException | IOException e){
            throw new SQLException("[ERROR] Al intentar modificar el usuario " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        try(Connection conn = ConexionBaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idUsuario);
            int eliminado = ps.executeUpdate();
            if(eliminado == 0){
                throw new SQLException("[ERROR] Al eliminar usuario");
            }
        } catch (SQLException | IOException e){
            throw new SQLException("[ERROR] No se puede eliminar usuario " + e.getMessage(), e);
        }
    }
}
