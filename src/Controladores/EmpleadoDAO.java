/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Datos.Conexion;
import Entidades.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Usuario
 */
public class EmpleadoDAO {
   private static final String Consultar = "SELECT * FROM empleado";
    private static final String Insertar = "INSERT INTO empleado VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String Modificar = "UPDATE empleado SET cedula=?, nombre=?, apellido=?, telefono=?, direccion=?, idLocal=?, idPuesto=? WHERE idEmpleado=?";
    private static final String Eliminar = "DELETE FROM empleado WHERE idEmpleado=?";
    
    public boolean InsertarEmpleado(Empleado oe) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(Insertar);
            pst.setInt(1, oe.getIdEmpleado());
            pst.setString(2, oe.getCedula());
            pst.setString(3, oe.getNombre());
            pst.setString(4, oe.getApellido());
            pst.setString(5, oe.getTelefono());
            pst.setString(6, oe.getDireccion());
            pst.setInt(7, oe.getIdLocal());
            pst.setInt(8, oe.getIdPuesto());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean ModificarEmpleado(Empleado oe) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(Modificar);
            pst.setString(1, oe.getCedula());
            pst.setString(2, oe.getNombre());
            pst.setString(3, oe.getApellido());
            pst.setString(4, oe.getTelefono());
            pst.setString(5, oe.getDireccion());
            pst.setInt(6, oe.getIdLocal());
            pst.setInt(7, oe.getIdPuesto());
            pst.setInt(8, oe.getIdEmpleado());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean EliminarEmpleado(Empleado oe) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(Eliminar);
            pst.setInt(1, oe.getIdEmpleado());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public List<Empleado> ListarEmpleado() {
        List<Empleado> Lista = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            Connection connection = con.ObtenerConexion();
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(Consultar);
            
            while (resultado.next()) {
                Empleado oe = new Empleado();
                oe.setIdEmpleado(resultado.getInt(1));
                oe.setCedula(resultado.getString(2));
                oe.setNombre(resultado.getString(3));
                oe.setApellido(resultado.getString(4));
                oe.setTelefono(resultado.getString(5));
                oe.setDireccion(resultado.getString(6));
                oe.setIdLocal(resultado.getInt(7));
                oe.setIdPuesto(resultado.getInt(8));
                Lista.add(oe);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Lista;
    }
    
    public DefaultTableModel MostrarEmpleado(List<Empleado> Lista) {
        String[] titulos = {"ID", "Cédula", "Nombre", "Apellido", "Teléfono", "Dirección", "ID Local", "ID Puesto"};
        String[] registro = new String[8];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        for (Empleado oe : Lista) {
            registro[0] = String.valueOf(oe.getIdEmpleado());
            registro[1] = oe.getCedula();
            registro[2] = oe.getNombre();
            registro[3] = oe.getApellido();
            registro[4] = oe.getTelefono();
            registro[5] = oe.getDireccion();
            registro[6] = String.valueOf(oe.getIdLocal());
            registro[7] = String.valueOf(oe.getIdPuesto());
            modelo.addRow(registro);
        }
        return modelo;
    }
}
