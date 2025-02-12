/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Datos.Conexion;
import Entidades.Libro;
import Entidades.Prestamo;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public void librom() {
        LibroDAO l = new LibroDAO();
        List<Prestamo> ListaP = ListarPrestamo();
        List<Libro> ListaL = l.ListarLibro();
        java.util.Map<Integer, Integer> librosU = new java.util.HashMap<>();
        for (Prestamo pre : ListaP) {
            for (Libro lib : ListaL) {
                if (pre.getIdLibro() == lib.getIdLibro()) {
                    int id = pre.getIdLibro();
                    librosU.put(id, librosU.getOrDefault(id, 0) + 1);
                }
            }
        }
        int max = 0;
        int id = 0;
        int min = Integer.MAX_VALUE;
        int idmin = 0;

        List<Map.Entry<Integer, Integer>> lis = new ArrayList<>(librosU.entrySet());
        for (Map.Entry<Integer, Integer> entry : lis) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                id = entry.getKey();
            }
            if (entry.getValue() < min) {
                min = entry.getValue();
                idmin = entry.getKey();
            }
        }
        String a = "";
        String b = "";
        for (Libro lib : ListaL) {
            if (id == lib.getIdLibro()) {
                a = lib.getNombre();
            }
            if (idmin == lib.getIdLibro()) {
                b = lib.getNombre();
            }
        }
        StringBuilder reporte = new StringBuilder("REPORTE");
        reporte.append("Libro más pedido:\n");
        reporte.append("El libro más pedido fue ").append(a)
                .append(" con un total de ").append(max).append(" pedidos.\n");
        reporte.append("Libro menos pedido:\n");
        reporte.append("El libro menos pedido fue ").append(b)
                .append(" con un total de ").append(min).append(" pedidos.\n");
        JOptionPane.showMessageDialog(null, reporte.toString());
    }

    public void usuariom() {
        UsuarioDAO u = new UsuarioDAO();
        List<Prestamo> listaPrestamos = ListarPrestamo();
        List<Usuario> listaUsuarios = u.ListarUsuario();
        java.util.Map<Integer, Integer> prestamosPorUsuario = new java.util.HashMap<>();

        for (Prestamo prestamo : listaPrestamos) {
            int idUsuario = prestamo.getIdUsuario();
            prestamosPorUsuario.put(idUsuario, prestamosPorUsuario.getOrDefault(idUsuario, 0) + 1);
        }

        int max = 0, min = Integer.MAX_VALUE;
        int idMax = 0, idMin = 0;

        for (Map.Entry<Integer, Integer> entry : prestamosPorUsuario.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                idMax = entry.getKey();
            }
            if (entry.getValue() < min) {
                min = entry.getValue();
                idMin = entry.getKey();
            }
        }

        String UsMax = "", nombreUsuarioMin = "";
        for (Usuario usuario : listaUsuarios) {
            if (idMax == usuario.getIdUsuario()) {
                UsMax = usuario.getNombre() + " " + usuario.getApellido();
            }
            if (idMin == usuario.getIdUsuario()) {
                nombreUsuarioMin = usuario.getNombre() + " " + usuario.getApellido();
            }
        }

        StringBuilder reporte = new StringBuilder("REPORTE\n");
        reporte.append("Usuario con más préstamos:\n");
        reporte.append("El usuario que realizó más préstamos fue ").append(UsMax)
                .append(" con un total de ").append(max).append(" préstamos.\n");
        reporte.append("Usuario con menos préstamos:\n");
        reporte.append("El usuario que realizó menos préstamos fue ").append(nombreUsuarioMin)
                .append(" con un total de ").append(min).append(" préstamos.\n");

        JOptionPane.showMessageDialog(null, reporte.toString());
    }
    
    public void buscarPrestamo(String busqueda) {
    List<Prestamo> listaPrestamos = ListarPrestamo();
    StringBuilder reporte = new StringBuilder();
    reporte.append("RESULTADOS DE BÚSQUEDA\n\n");

    boolean encontrado = false;
    busqueda = busqueda.toLowerCase();

    for (Prestamo prestamo : listaPrestamos) {
        String idPrestamo = String.valueOf(prestamo.getIdPrestamo());
        String idUsuario = String.valueOf(prestamo.getIdUsuario());
        String idLibro = String.valueOf(prestamo.getIdLibro());
        String estado = prestamo.getEstado().toLowerCase();

        if (idPrestamo.contains(busqueda) || idUsuario.contains(busqueda) || idLibro.contains(busqueda) || estado.contains(busqueda)) {
            encontrado = true;
            reporte.append("ID Préstamo: ").append(prestamo.getIdPrestamo()).append("\n");
            reporte.append("ID Usuario: ").append(prestamo.getIdUsuario()).append("\n");
            reporte.append("ID Libro: ").append(prestamo.getIdLibro()).append("\n");
            reporte.append("Fecha de Préstamo: ").append(prestamo.getFechaPrestamo()).append("\n");
            reporte.append("Fecha de Devolución: ").append(prestamo.getFechaDevolucion()).append("\n");
            reporte.append("Estado: ").append(prestamo.getEstado()).append("\n");
            reporte.append("------------------------------------------\n");
        }
    }

    if (!encontrado) {
        reporte.append("No se encontraron préstamos que coincidan con '").append(busqueda).append("'");
    }

    JOptionPane.showMessageDialog(null, reporte.toString(),
            "Resultados de Búsqueda",
            JOptionPane.INFORMATION_MESSAGE);
}

}
