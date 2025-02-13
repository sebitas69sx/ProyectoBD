/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Datos.Conexion;
import Entidades.Puesto;
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
public class PuestoDAO {
    private static final String CONSULTAR = "SELECT * FROM puesto";
    private static final String INSERTAR = "INSERT INTO puesto VALUES (?, ?, ?)";
    private static final String MODIFICAR = "UPDATE puesto SET Nombre=?, Descripcion=? WHERE IdPuesto=?";
    private static final String ELIMINAR = "DELETE FROM puesto WHERE IdPuesto=?";
    
    public boolean InsertarPuesto(Puesto puesto) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(INSERTAR);
            pst.setInt(1, puesto.getIdPuesto());
            pst.setString(2, puesto.getNombre());
            pst.setString(3, puesto.getDescripcion());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean ModificarPuesto(Puesto puesto) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(MODIFICAR);
            pst.setString(1, puesto.getNombre());
            pst.setString(2, puesto.getDescripcion());
            pst.setInt(3, puesto.getIdPuesto());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean EliminarPuesto(Puesto puesto) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(ELIMINAR);
            pst.setInt(1, puesto.getIdPuesto());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public List<Puesto> ListarPuesto() {
        List<Puesto> lista = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            Connection connection = con.ObtenerConexion();
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(CONSULTAR);
            
            while (resultado.next()) {
                Puesto puesto = new Puesto();
                puesto.setIdPuesto(resultado.getInt(1));
                puesto.setNombre(resultado.getString(2));
                puesto.setDescripcion(resultado.getString(3));
                lista.add(puesto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public DefaultTableModel MostrarPuesto(List<Puesto> lista) {
        String[] titulos = {"ID", "Nombre", "Descripción"};
        String[] registro = new String[3];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        for (Puesto puesto : lista) {
            registro[0] = String.valueOf(puesto.getIdPuesto());
            registro[1] = puesto.getNombre();
            registro[2] = puesto.getDescripcion();
            modelo.addRow(registro);
        }
        return modelo;
    }
    
    public void buscarPuesto(String busqueda) {
    List<Puesto> lista = ListarPuesto();
    StringBuilder reporte = new StringBuilder();
    reporte.append("RESULTADOS DE BÚSQUEDA\n\n");

    boolean encontrado = false;
    busqueda = busqueda.toLowerCase();

    for (Puesto puesto : lista) {
        if (puesto.getNombre().toLowerCase().contains(busqueda) ||
            puesto.getDescripcion().toLowerCase().contains(busqueda)) {
                
            encontrado = true;
            reporte.append("ID: ").append(puesto.getIdPuesto()).append("\n");
            reporte.append("Nombre: ").append(puesto.getNombre()).append("\n");
            reporte.append("Descripción: ").append(puesto.getDescripcion()).append("\n");
            reporte.append("------------------------------------------\n");
        }
    }

    if (!encontrado) {
        reporte.append("No se encontraron puestos que coincidan con '")
                .append(busqueda).append("'");
    }

    JOptionPane.showMessageDialog(null, reporte.toString(),
            "Resultados de Búsqueda",
            JOptionPane.INFORMATION_MESSAGE);
}
    public String Cant(){
        List<Puesto> Lista = ListarPuesto();
        return Lista.size() + "";
    }
}
