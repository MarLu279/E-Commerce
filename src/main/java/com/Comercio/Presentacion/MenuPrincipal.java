package com.Comercio.Presentacion;

import com.Comercio.DAO.IProductoDAO;
import com.Comercio.DAO.IUsuarioDAO;
import com.Comercio.DAO.ProductoDAOImp;
import com.Comercio.DAO.UsuarioDAOImp;
import com.Comercio.Modelo.Producto;
import com.Comercio.Modelo.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        IUsuarioDAO servicioUsuario = new UsuarioDAOImp();
        IProductoDAO servicioProducto = new ProductoDAOImp();
        login(entrada, servicioUsuario, servicioProducto);

    }
    private static void ingresarUsuario(Scanner entrada, IUsuarioDAO servicioUsuario){
        System.out.println("*** Ingresar usuario ***");
        System.out.println("Ingresa el nombre del usuario: ");
        String nombre = entrada.nextLine();
        System.out.println("Ingresa el apellido del usuario: ");
        String apellido = entrada.nextLine();
        System.out.println("Ingresa el email del usuario");
        String email = entrada.nextLine();
        System.out.println("Ingresa el password del usuario");
        String password = entrada.nextLine();
        System.out.println("Ingresa el tipo de usuario (admin, cliente)");
        String rol = entrada.nextLine();
        Usuario usuario = new Usuario(nombre, apellido, email, password, rol);
        try {
            servicioUsuario.insertarUsuario(usuario);
            System.out.println("Usuario agregado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buscarUsuarioId(Scanner entrada, IUsuarioDAO servicioUsuario){
        System.out.println("*** Buscar Ususario por Id ***");
        System.out.println("Ingresa el id del usuario");
        int id = Integer.parseInt(entrada.nextLine());
        try {
            Usuario usuarioEncontrado = servicioUsuario.buscarUsuarioPorId(id);
            if (usuarioEncontrado != null){
                System.out.println(usuarioEncontrado);
            } else {
                System.out.println("Usuario no encontrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buscarUsuarioEmail(Scanner entrada, IUsuarioDAO servicioUsuario){
        System.out.println("*** Buscar usuario por email ***");
        System.out.println("Ingresa el email a buscar: ");
        String email = entrada.nextLine();
        try {
            Usuario usuarioEncontrado = servicioUsuario.buscarUsuarioPorEmail(email);
            if (usuarioEncontrado != null){
                System.out.println(usuarioEncontrado);
            } else {
                System.out.println("Usuario no encontrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void actualizarUsuario(Scanner entrada, IUsuarioDAO servicioUsuario){
        System.out.println("*** Actualizar Usuario ***");
        System.out.println("Ingresa el id del usuario a actualizar");
        int id = Integer.parseInt(entrada.nextLine());
        System.out.println("Ingresa el nombre del usuario");
        String nombre = entrada.nextLine();
        System.out.println("Ingresa el apellido del ususario");
        String apellido = entrada.nextLine();
        System.out.println("Ingresa el email del usuario");
        String email = entrada.nextLine();
        System.out.println("Ingresa el password del usuario");
        String password = entrada.nextLine();
        System.out.println("Ingresa el tipo de usuario (admin o cliente)");
        String rol = entrada.nextLine();
        Usuario usuarioActualizado = new Usuario(id, nombre, apellido, email, password, rol);
        try {
            servicioUsuario.actualizarUsuario(usuarioActualizado);
            System.out.println("Usuario actualizado con exito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void eliminarUsuario(Scanner entrada, IUsuarioDAO servicioUsuario){
        System.out.println("*** Eliminar Usuario ***");
        System.out.println("Ingresa el id del usuario a eliminar");
        int id = Integer.parseInt(entrada.nextLine());
        try {
            servicioUsuario.eliminarUsuario(id);
            System.out.println("Usuario eliminado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listarUsarios(IUsuarioDAO servicoUsusario){
        System.out.println("*** Listado de Usuarios ***");
        try {
            List<Usuario> usuarios = servicoUsusario.lisatarUsuarios();
            if (!usuarios.isEmpty()){
                for(Usuario usuario : usuarios){
                    System.out.println(usuario);
                }
            } else {
                System.out.println("No hay usuarios disponibles");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void login(Scanner entrada, IUsuarioDAO servicioUsuario, IProductoDAO servicioProducto){
        System.out.println("Escribe tu email: ");
        String email = entrada.nextLine();
        System.out.println("Escribe tu contraseña: ");
        String password = entrada.nextLine();
        try {
            Usuario usuario = servicioUsuario.buscarUsuarioPorEmail(email);
            if(Usuario.validarPassword(password)){
                System.out.println("Bienvenido: " + usuario.getNombre());
                if ((usuario.getRol().equals("admin"))) {
                    menuAdmin(entrada, servicioUsuario);
                    login(entrada, servicioUsuario,servicioProducto);
                } else {
                    menuCliente(entrada, servicioProducto);
                    login(entrada, servicioUsuario,servicioProducto);
                }
            } else {
                System.out.println("Credenciales incorrectas");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void menuAdmin(Scanner entrada, IUsuarioDAO servicioUsuario){
        boolean bandera = false;
        while (!bandera){
            System.out.println("""
                *** Selececciona la opcion a realizar ***
                1. Lista de usuarios
                2. Ingresar usuario
                3. Buscar usuario por id
                4. Buscar usuario por email
                5. Actualizar usuario
                6. Eliminar usuario
                7. Salir
                Opcion seleccionada:
                """);
            int opcion = Integer.parseInt(entrada.nextLine());
            switch (opcion){
                case 1 -> listarUsarios(servicioUsuario);
                case 2 -> ingresarUsuario(entrada, servicioUsuario);
                case 3 -> buscarUsuarioId(entrada, servicioUsuario);
                case 4 -> buscarUsuarioEmail(entrada, servicioUsuario);
                case 5 -> actualizarUsuario(entrada, servicioUsuario);
                case 6 -> eliminarUsuario(entrada, servicioUsuario);
                case 7 -> {
                    System.out.println("Saliendo adios :D");
                    bandera = true;
                }
            }
        }
    }

    private static void menuCliente(Scanner entrada, IProductoDAO servicioProducto){
        boolean bandera = false;
        while(!bandera){
            System.out.println("""
                    *** Selecciona la opcion a realizar ***
                    1. Lista de productos
                    2. Buscar productos por Id
                    3. Comprar
                    4. Pagar
                    5. Salir
                    """);

            int opcion = Integer.parseInt(entrada.nextLine());
            switch (opcion) {
                case 1 -> listaProductos(servicioProducto);
                case 2 -> buscarProductoId(entrada, servicioProducto);
                case 3 -> System.out.println("comprar");
                case 4 -> System.out.println("pagar");
                case 5 -> {
                    System.out.println("Saliendo :D");
                    bandera = true;
                }
            }
        }
    }

    private static void listaProductos(IProductoDAO servicioProducto){
        System.out.println("*** Lista de prodcuctos ***");
        try {
            List<Producto> productos = servicioProducto.listarProductos();
            if(!productos.isEmpty()){
                for(Producto producto : productos)
                    System.out.println(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buscarProductoId(Scanner entrada, IProductoDAO servicioProducto) {
        Producto producto;
        System.out.println("Escribe el id del producto a buscar: ");
        int id = Integer.parseInt(entrada.nextLine());
        try {
            producto = servicioProducto.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El producto con id: " + id );
        System.out.println(producto);
    }
}
