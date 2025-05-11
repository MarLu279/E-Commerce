package com.Comercio.DAO;

import com.Comercio.Modelo.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDAO {
    void insertarUsuario(Usuario usuario) throws SQLException;

    Usuario buscarUsuarioPorId(int idUsuario) throws SQLException;

    Usuario buscarUsuarioPorEmail(String email) throws SQLException;

    List<Usuario> lisatarUsuarios() throws SQLException;

    void actualizarUsuario(Usuario usuario) throws SQLException;

    void eliminarUsuario(int idUsuario) throws SQLException;
}
