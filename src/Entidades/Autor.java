/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class Autor extends Persona {
    private int IdAutor;

    public Autor() {
        //CAJAMARCA X MARITZA
    }

    public Autor(int IdAutor) {
        this.IdAutor = IdAutor;
    }

    public Autor(String Cedula, String Nombre, String Apellido, String Telefono, String Direccion, int IdAutor) {
        super(Cedula, Nombre, Apellido, Telefono, Direccion);
        this.IdAutor = IdAutor;
    }

    public int getIdAutor() {
        return IdAutor;
    }

    public void setIdAutor(int IdAutor) {
        this.IdAutor = IdAutor;
    }
    

   
    
    
}
