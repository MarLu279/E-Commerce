package com.Comercio.Modelo;

import java.util.Objects;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;

    public Usuario(){}

    public Usuario(int idUsuario){
        this.idUsuario = idUsuario;
    }

    public Usuario(String nombre, String apellido, String email, String password, String rol){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(int idUsuario, String nombre, String apellido, String email, String password, String rol){
        this(nombre, apellido, email, password, rol);
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        if(idUsuario < 0){
            throw new IllegalArgumentException("[ERROR] El id de usuario no puede ser negativo");
        }
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("[ERROR] El nombre no puede estar vacio");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if(apellido == null || apellido.trim().isEmpty()){
            throw new IllegalArgumentException("[ERROR] El apellido no puede estar vacio");
        }
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.trim().isEmpty() || !email.contains("@")){
            throw new IllegalArgumentException("[ERROR] El email no puede estar vacio");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(!validarPassword(password)){
            throw new IllegalArgumentException("[ERROR] El password no puede estar vacio");
        }
        this.password = password;
    }

    public String getRol(){
        return rol;
    }

    public void setRol(String rol){
        if(!rol.equals("admin") && !rol.equals("cliente")){
            throw new IllegalArgumentException("[ERROR] El rol solo debe de ser admin o cliente");
        }
        this.rol = rol;
    }

    public static boolean validarPassword(String password){
        if(password == null || password.trim().isEmpty()){
            return false;
        }
        if(password.length() < 6){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellido, usuario.apellido) && Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {
        int result = idUsuario;
        result = 31 * result + Objects.hashCode(nombre);
        result = 31 * result + Objects.hashCode(apellido);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(password);
        return result;
    }
}
