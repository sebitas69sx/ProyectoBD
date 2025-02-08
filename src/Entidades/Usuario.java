/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class Usuario extends Persona{
    private int IdUsuario;

    public Usuario() {
        //HOLA a
        //AJSDA
    }


    public Usuario(String Cedula, String Nombre, String Apellido, String Telefono, String Direccion,int IdUsuario) {
        super(Cedula, Nombre, Apellido, Telefono, Direccion);
        this.IdUsuario = IdUsuario;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }
    
    
    
    
    

   
    
    
    
    
}
