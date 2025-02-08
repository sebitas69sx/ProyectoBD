/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Datos.Conexion;
import Entidades.Categoria;
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
public class CategoriaDAO {
     private static final String Consultar = "SELECT * FROM categoria";
    private static final String Insertar = "INSERT INTO categoria VALUES (?, ?, ?)";
    private static final String Modificar = "UPDATE categoria SET nombre=?, descripcion=? WHERE idcategoria=?";
    private static final String Eliminar = "DELETE FROM categoria WHERE idcategoria=?";
    
    public boolean InsertarCategoria(Categoria oc) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(Insertar);
            pst.setInt(1, oc.getIdCategoria());
            pst.setString(2, oc.getNombre());
            pst.setString(3, oc.getDescripcion());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean ModificarCategoria(Categoria oc) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(Modificar);
            pst.setString(1, oc.getNombre());
            pst.setString(2, oc.getDescripcion());
            pst.setInt(3, oc.getIdCategoria());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean EliminarCategoria(Categoria oc) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(Eliminar);
            pst.setInt(1, oc.getIdCategoria());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public List<Categoria> ListarCategoria() {
        List<Categoria> Lista = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            Connection connection = con.ObtenerConexion();
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(Consultar);
            
            while (resultado.next()) {
                Categoria oc = new Categoria();
                oc.setIdCategoria(resultado.getInt(1));
                oc.setNombre(resultado.getString(2));
                oc.setDescripcion(resultado.getString(3));
                Lista.add(oc);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Lista;
    }
    
    public DefaultTableModel MostrarCategoria(List<Categoria> Lista) {
        String[] titulos = {"ID", "Nombre", "Descripción"};
        String[] registro = new String[3];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        for (Categoria oc : Lista) {
            registro[0] = String.valueOf(oc.getIdCategoria());
            registro[1] = oc.getNombre();
            registro[2] = oc.getDescripcion();
            modelo.addRow(registro);
        }
        return modelo;
    }
}
