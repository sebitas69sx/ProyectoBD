/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Datos.Conexion;
import Entidades.Local;
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
public class LocalDAO {
    private static final String CONSULTAR = "SELECT * FROM local";
    private static final String INSERTAR = "INSERT INTO local VALUES (?, ?, ?, ?)";
    private static final String MODIFICAR = "UPDATE libro SET Nombre=?, Direccion=?,Telefono=? WHERE IdLocal=?";
    private static final String ELIMINAR = "DELETE FROM local WHERE IdLocal=?";
    
    public boolean InsertarLibro(Local loc) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(INSERTAR);
            pst.setInt(1, loc.getIdLocal());
            pst.setString(2, loc.getNombre());
            pst.setString(3, loc.getDireccion());
            pst.setString(4, loc.getTelefono());
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean ModificarLibro(Local loc) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(MODIFICAR);
            pst.setString(1, loc.getNombre());
            pst.setString(2, loc.getNombre());
            pst.setString(3, loc.getDireccion());
            pst.setString(4, loc.getTelefono());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean EliminarLibro(Local loc) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(ELIMINAR);
            pst.setInt(1, loc.getIdLocal());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public List<Local> ListarLibro() {
        List<Local> Lista = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            Connection connection = con.ObtenerConexion();
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(CONSULTAR);
            
            while (resultado.next()) {
                Local loc = new Local();
                loc.setIdLocal(resultado.getInt(1));
                loc.setNombre(resultado.getString(2));
                loc.setDireccion(resultado.getString(3));
                loc.setTelefono(resultado.getString(4));
                Lista.add(loc);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Lista;
    }
    
    public DefaultTableModel MostrarLibro(List<Local> Lista) {
        String[] titulos = {"ID", "Nombre", "Direccion", "Telefono"};
        String[] registro = new String[4];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        for (Local loc : Lista) {
            registro[0] = String.valueOf(loc.getIdLocal());
            registro[1] = loc.getNombre();
            registro[2] = loc.getDireccion();
            registro[3] = loc.getTelefono();
            modelo.addRow(registro);
        }
        return modelo;
    }
}
