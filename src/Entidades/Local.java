/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class Local {
    private int IdLocal;
    private String Nombre;
    private String Direccion;
    private String Telefono;

    public Local() {
    }

    public Local(int IdLocal, String Nombre, String Direccion, String Telefono) {
        this.IdLocal = IdLocal;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }

    public int getIdLocal() {
        return IdLocal;
    }

    public void setIdLocal(int IdLocal) {
        this.IdLocal = IdLocal;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    
}
