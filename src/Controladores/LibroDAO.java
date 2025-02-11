/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Datos.Conexion;
import Entidades.Libro;
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
public class LibroDAO {
     private static final String CONSULTAR = "SELECT * FROM libro";
    private static final String INSERTAR = "INSERT INTO libro VALUES (?, ?, ?, ?, ?, ?)";
    private static final String MODIFICAR = "UPDATE libro SET Nombre=?, Editorial=?, IdLocal=?, IdAutor=?, IdCategoria=? WHERE IdLibro=?";
    private static final String ELIMINAR = "DELETE FROM libro WHERE IdLibro=?";
    
    public boolean InsertarLibro(Libro lib) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(INSERTAR);
            pst.setInt(1, lib.getIdLibro());
            pst.setString(2, lib.getNombre());
            pst.setString(3, lib.getEditorial());
            pst.setInt(4, lib.getIdLocal());
            pst.setInt(5, lib.getIdAutor());
            pst.setInt(6, lib.getIdCategoria());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean ModificarLibro(Libro lib) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(MODIFICAR);
            pst.setString(1, lib.getNombre());
            pst.setString(2, lib.getEditorial());
            pst.setInt(3, lib.getIdLocal());
            pst.setInt(4, lib.getIdAutor());
            pst.setInt(5, lib.getIdCategoria());
            pst.setInt(6, lib.getIdLibro());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean EliminarLibro(Libro lib) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(ELIMINAR);
            pst.setInt(1, lib.getIdLibro());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public List<Libro> ListarLibro() {
        List<Libro> Lista = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            Connection connection = con.ObtenerConexion();
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(CONSULTAR);
            
            while (resultado.next()) {
                Libro lib = new Libro();
                lib.setIdLibro(resultado.getInt(1));
                lib.setNombre(resultado.getString(2));
                lib.setEditorial(resultado.getString(3));
                lib.setIdLocal(resultado.getInt(4));
                lib.setIdAutor(resultado.getInt(5));
                lib.setIdCategoria(resultado.getInt(6));
                Lista.add(lib);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Lista;
    }
    
    public DefaultTableModel MostrarLibro(List<Libro> Lista) {
        String[] titulos = {"ID", "Nombre", "Editorial", "ID Local", "ID Autor", "ID Categoria"};
        String[] registro = new String[6];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        for (Libro lib : Lista) {
            registro[0] = String.valueOf(lib.getIdLibro());
            registro[1] = lib.getNombre();
            registro[2] = lib.getEditorial();
            registro[3] = String.valueOf(lib.getIdLocal());
            registro[4] = String.valueOf(lib.getIdAutor());
            registro[5] = String.valueOf(lib.getIdCategoria());
            modelo.addRow(registro);
        }
        return modelo;
    }
}
