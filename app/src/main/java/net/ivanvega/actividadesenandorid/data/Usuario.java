package net.ivanvega.actividadesenandorid.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable {
    private String Nombre;
    private String Telefono;
    private String Email;
    private String Contraseña;
    private long ID;

    public Usuario(String nombre, String telefono, String email, String contraseña) {
        Nombre = nombre;
        Telefono = telefono;
        Email = email;
        Contraseña = contraseña;
    }

    public Usuario(String email, String password) {
        this.Email = email;
        this.Contraseña = password;
    }

    public Usuario(int ID, String nombre, String emaill, String pass, String tel) {
        this.ID = ID;
        this.Nombre = nombre;
        this.Email = emaill;
        this.Contraseña = pass;
        this.Telefono = tel;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    @Override
    public String toString(){
        return Nombre;
    }

    public void setID(long id) {
        this.ID = id;
    }

    public long getID() {
        return ID;
    }
}
