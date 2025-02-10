/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Datos.Conexion;
import Entidades.Usuario;
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
 * @author HP
 */
public class UsuarioDAO {
     private static final String CONSULTAR = "SELECT * FROM usuario";
    private static final String INSERTAR = "INSERT INTO usuario VALUES (?, ?, ?, ?, ?, ?)";
    private static final String MODIFICAR = "UPDATE usuario SET Cedula=?, Nombre=?, Apellido=?, Telefono=?, Direccion=? WHERE IdUsuario=?";
    private static final String ELIMINAR = "DELETE FROM usuario WHERE IdUsuario=?";
    
    public boolean InsertarUsuario(Usuario u) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(INSERTAR);
            pst.setInt(1, u.getIdUsuario());
            pst.setString(2, u.getCedula());
            pst.setString(3, u.getNombre());
            pst.setString(4, u.getApellido());
            pst.setString(5, u.getTelefono());
            pst.setString(6, u.getDireccion());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean ModificarUsuario(Usuario u) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(MODIFICAR);
            pst.setString(1, u.getCedula());
            pst.setString(2, u.getNombre());
            pst.setString(3, u.getApellido());
            pst.setString(4, u.getTelefono());
            pst.setString(5, u.getDireccion());
            pst.setInt(6, u.getIdUsuario());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public boolean EliminarUsuario(Usuario u) {
        Conexion con = new Conexion();
        boolean op = false;
        try (Connection connection = con.ObtenerConexion()) {
            PreparedStatement pst = connection.prepareStatement(ELIMINAR);
            pst.setInt(1, u.getIdUsuario());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                op = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public List<Usuario> ListarUsuarios() {
        List<Usuario> Lista = new ArrayList<>();
        try {
            Conexion con = new Conexion();
            Connection connection = con.ObtenerConexion();
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(CONSULTAR);
            
            while (resultado.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(resultado.getInt(1));
                u.setCedula(resultado.getString(2));
                u.setNombre(resultado.getString(3));
                u.setApellido(resultado.getString(4));
                u.setTelefono(resultado.getString(5));
                u.setDireccion(resultado.getString(6));
                Lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Lista;
    }
    
    public DefaultTableModel MostrarUsuarios(List<Usuario> Lista) {
        String[] titulos = {"ID", "Cédula", "Nombre", "Apellido", "Teléfono", "Dirección"};
        String[] registro = new String[6];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        for (Usuario u : Lista) {
            registro[0] = String.valueOf(u.getIdUsuario());
            registro[1] = u.getCedula();
            registro[2] = u.getNombre();
            registro[3] = u.getApellido();
            registro[4] = u.getTelefono();
            registro[5] = u.getDireccion();
            modelo.addRow(registro);
        }
        return modelo;
    }
}


