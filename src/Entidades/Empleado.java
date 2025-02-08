/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class Empleado extends Persona {
    private int idEmpleado;
    private int idLocal;    // Clave foránea de Local
    private int idPuesto; 

    public Empleado() {
    }


    public Empleado( String Cedula, String Nombre, String Apellido, String Telefono, String Direccion,int idEmpleado, int idLocal, int idPuesto) {
        super(Cedula, Nombre, Apellido, Telefono, Direccion);
        this.idEmpleado = idEmpleado;
        this.idLocal = idLocal;
        this.idPuesto = idPuesto;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }
    


   
    
    
    


    
    
    
}
