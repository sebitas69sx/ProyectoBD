/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Datos.Conexion;
import Entidades.Prestamo;
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
public class PrestamoDAO {

    private static final String CONSULTAR = "SELECT * FROM prestamo";
    private static final String INSERTAR = "INSERT INTO prestamo VALUES (?, ?, ?, ?, ?, ?)";
    private static final String MODIFICAR = "UPDATE prestamo SET IdLibro=?,IdUsuario=?, FechaPrestamo=?, FechaDevolucion=?, Estado=? WHERE IdPrestamo=?";
    private static final String ELIMINAR = "DELETE FROM local WHERE IdPrestamo=?";

    public boolean InsertarPrestamo(Prestamo oe) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(INSERTAR);
            pst.setInt(1, oe.getIdPrestamo());
            pst.setInt(2, oe.getIdLibro());
           pst.setDate(3, new java.sql.Date(oe.getFechaPrestamo().getTime()));
             pst.setDate(4, new java.sql.Date(oe.getFechaDevolucion().getTime()));
            pst.setString(5, oe.getEstado());
            pst.setInt(6, oe.getIdUsuario());

            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }

    public boolean ModificarPrestamo(Prestamo oe) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(MODIFICAR);
            pst.setInt(1, oe.getIdLibro());
             pst.setDate(2, new java.sql.Date(oe.getFechaPrestamo().getTime()));
             pst.setDate(3, new java.sql.Date(oe.getFechaDevolucion().getTime()));
            pst.setString(4, oe.getEstado());
            pst.setInt(5, oe.getIdUsuario());
            pst.setInt(6, oe.getIdPrestamo());

            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }

    public boolean EliminarPrestamo(Prestamo oe) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(ELIMINAR);
            pst.setInt(1, oe.getIdPrestamo());

            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }

    public List<Prestamo> ListarPrestamo() {
        List<Prestamo> Lista = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            Connection connection = con.ObtenerConexion();
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(CONSULTAR);

            while (resultado.next()) {
                Prestamo oe = new Prestamo();
                oe.setIdPrestamo(resultado.getInt(1));
                oe.setIdLibro(resultado.getInt(2));
                oe.setFechaPrestamo(resultado.getDate(3));
                oe.setFechaDevolucion(resultado.getDate(4));
                oe.setEstado(resultado.getString(5));
                oe.setIdUsuario(resultado.getInt(6));
                Lista.add(oe);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Lista;
    }

    public DefaultTableModel MostrarPrestamo(List<Prestamo> Lista) {
        String[] titulos = {"ID", "Libro", "Fecha prestamo", "Fecha devolucion", "Estado", "Usuario"};
        String[] registro = new String[6];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        for (Prestamo oe : Lista) {
            registro[0] = String.valueOf(oe.getIdPrestamo());
            registro[1] = String.valueOf(oe.getIdLibro());
            registro[2] = String.valueOf(oe.getFechaPrestamo());
            registro[3] = String.valueOf(oe.getFechaDevolucion());
            registro[4] = oe.getEstado();
            registro[5] = String.valueOf(oe.getIdUsuario());
            modelo.addRow(registro);
        }
        return modelo;
    }

}
