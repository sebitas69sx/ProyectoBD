/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class Autor {
    private int IdAutor;
    private String Nombre;
    private String Apellido;
    private String Nacionalidad;

    public Autor() {
    }

    public Autor(int IdAutor, String Nombre, String Apellido, String Nacionalidad) {
        this.IdAutor = IdAutor;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Nacionalidad = Nacionalidad;
    }

    public int getIdAutor() {
        return IdAutor;
    }

    public void setIdAutor(int IdAutor) {
        this.IdAutor = IdAutor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }
    
    
}
