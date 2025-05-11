package com.Comercio;

import com.Comercio.DAO.IProductoDAO;
import com.Comercio.DAO.IUsuarioDAO;
import com.Comercio.DAO.UsuarioDAOImp;
import com.Comercio.Modelo.Usuario;

import java.sql.SQLException;
import java.util.List;

public class PruebaUsuarios {
    public static void main(String[] args) {
        IUsuarioDAO usuarioDAO = new UsuarioDAOImp();
        //Usuario usuario = new Usuario("Elena", "Vargas", "e.vargas77@servidor.org", "clave456", "cliente");
        try {
           //usuarioDAO.insertarUsuario(usuario);
            //List<Usuario> usuarios = usuarioDAO.lisatarUsuarios();
            //usuarios.forEach(System.out::println);
            //Usuario usuario = usuarioDAO.buscarUsuarioPorId(2);
            //Usuario usuario = usuarioDAO.buscarUsuarioPorEmail("lucia.mendoza@correo.com");
            //Usuario usuario = new Usuario(3,"Elena", "Vargas", "eleVar@mail.com", "elena1234", "admin");
            usuarioDAO.eliminarUsuario(2);
            //System.out.println(usuario);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
