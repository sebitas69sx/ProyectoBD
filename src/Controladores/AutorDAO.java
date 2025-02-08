/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Datos.Conexion;
import Entidades.Autor;
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
public class AutorDAO {

    private static final String Consultar = "Select * from autor";
    private static final String Insertar = "insert into autor values(?,?,?,?)";
    private static final String Modificar = "update autor set idautor=?, nombre=?, apellido=?, nacionalidad=? where idautor=?";
    private static final String Eliminar = "delete from autor where idautor=?";

    public boolean InsertarAutor(Autor oa) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(Insertar);
            pst.setInt(1, oa.getIdAutor());
            pst.setString(2, oa.getNombre());
            pst.setString(3, oa.getApellido());
            pst.setString(4, oa.getNacionalidad());

            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }

    public boolean ModificarAutor(Autor oa) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(Modificar);
            pst.setInt(1, oa.getIdAutor());
            pst.setString(2, oa.getNombre());
            pst.setString(3, oa.getApellido());
            pst.setString(4, oa.getNacionalidad());
            pst.setInt(5, oa.getIdAutor());

            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }

    public boolean EliminarAutor(Autor oa) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(Eliminar);
            pst.setInt(1, oa.getIdAutor());

            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }

    public List<Autor> ListarAutor() {
        List<Autor> Lista = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            Connection connection = con.ObtenerConexion();
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(Consultar);

            while (resultado.next()) {
                Autor oa = new Autor();
                oa.setIdAutor(resultado.getInt(1));
                oa.setNombre(resultado.getString(2));
                oa.setApellido(resultado.getString(3));
                oa.setNacionalidad(resultado.getString(4));
                Lista.add(oa);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Lista;
    }

    public DefaultTableModel MostrarAutor(List<Autor> Lista) {
        String[] titulos = {"ID", "Nombre", "Apellido","Nacionalidad"};
        String[] registro = new String[4];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        for (Autor oa : Lista) {
            registro[0] = String.valueOf(oa.getIdAutor());
            registro[1] = oa.getNombre();
            registro[2] = oa.getApellido();
            registro[3] = oa.getNacionalidad();
            modelo.addRow(registro);
        }
        return modelo;
    }
}
