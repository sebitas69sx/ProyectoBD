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

    public void reporteEstadisticasLibros() {
        List<Libro> Lista = ListarLibro();

        // Mapas para contar libros por local, autor y categoría
        java.util.Map<Integer, Integer> librosPorLocal = new java.util.HashMap<>();
        java.util.Map<Integer, Integer> librosPorAutor = new java.util.HashMap<>();
        java.util.Map<Integer, Integer> librosPorCategoria = new java.util.HashMap<>();

        // Inicializar valores de máximos y mínimos
        int maxLibrosLocal = 0, minLibrosLocal = Integer.MAX_VALUE;
        int maxLibrosAutor = 0, minLibrosAutor = Integer.MAX_VALUE;
        int maxLibrosCategoria = 0, minLibrosCategoria = Integer.MAX_VALUE;

        int localConMas = 0, localConMenos = 0;
        int autorConMas = 0, autorConMenos = 0;
        int categoriaConMas = 0, categoriaConMenos = 0;

        // Contar libros por local, autor y categoría
        for (Libro lib : Lista) {
            librosPorLocal.merge(lib.getIdLocal(), 1, Integer::sum);
            librosPorAutor.merge(lib.getIdAutor(), 1, Integer::sum);
            librosPorCategoria.merge(lib.getIdCategoria(), 1, Integer::sum);
        }

        // Encontrar máximos y mínimos por local
        for (java.util.Map.Entry<Integer, Integer> entry : librosPorLocal.entrySet()) {
            if (entry.getValue() > maxLibrosLocal) {
                maxLibrosLocal = entry.getValue();
                localConMas = entry.getKey();
            }
            if (entry.getValue() < minLibrosLocal) {
                minLibrosLocal = entry.getValue();
                localConMenos = entry.getKey();
            }
        }

        // Encontrar máximos y mínimos por autor
        for (java.util.Map.Entry<Integer, Integer> entry : librosPorAutor.entrySet()) {
            if (entry.getValue() > maxLibrosAutor) {
                maxLibrosAutor = entry.getValue();
                autorConMas = entry.getKey();
            }
            if (entry.getValue() < minLibrosAutor) {
                minLibrosAutor = entry.getValue();
                autorConMenos = entry.getKey();
            }
        }

        // Encontrar máximos y mínimos por categoría
        for (java.util.Map.Entry<Integer, Integer> entry : librosPorCategoria.entrySet()) {
            if (entry.getValue() > maxLibrosCategoria) {
                maxLibrosCategoria = entry.getValue();
                categoriaConMas = entry.getKey();
            }
            if (entry.getValue() < minLibrosCategoria) {
                minLibrosCategoria = entry.getValue();
                categoriaConMenos = entry.getKey();
            }
        }

        // Crear reporte
        StringBuilder reporte = new StringBuilder();
        reporte.append("ESTADÍSTICAS DE LIBROS\n\n");
        reporte.append("Total de libros: ").append(Lista.size()).append("\n\n");

        reporte.append("LOCALES:\n");
        reporte.append("Local con más libros: ").append(localConMas)
                .append(" (").append(maxLibrosLocal).append(" libros)\n");
        reporte.append("Local con menos libros: ").append(localConMenos)
                .append(" (").append(minLibrosLocal).append(" libros)\n\n");

        reporte.append("AUTORES:\n");
        reporte.append("Autor con más libros: ").append(autorConMas)
                .append(" (").append(maxLibrosAutor).append(" libros)\n");
        reporte.append("Autor con menos libros: ").append(autorConMenos)
                .append(" (").append(minLibrosAutor).append(" libros)\n\n");

        reporte.append("CATEGORÍAS:\n");
        reporte.append("Categoría con más libros: ").append(categoriaConMas)
                .append(" (").append(maxLibrosCategoria).append(" libros)\n");
        reporte.append("Categoría con menos libros: ").append(categoriaConMenos)
                .append(" (").append(minLibrosCategoria).append(" libros)\n");

        JOptionPane.showMessageDialog(null, reporte.toString(),
                "Estadísticas de Libros",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void reporteLibrosPorCategoria(int idCategoria) {
        List<Libro> Lista = ListarLibro();
        StringBuilder reporte = new StringBuilder();
        reporte.append("LIBROS DE LA CATEGORÍA ").append(idCategoria).append("\n\n");

        boolean hayLibros = false;

        for (Libro lib : Lista) {
            if (lib.getIdCategoria() == idCategoria) {
                hayLibros = true;
                reporte.append("ID: ").append(lib.getIdLibro()).append("\n");
                reporte.append("Título: ").append(lib.getNombre()).append("\n");
                reporte.append("Editorial: ").append(lib.getEditorial()).append("\n");
                reporte.append("Local: ").append(lib.getIdLocal()).append("\n");
                reporte.append("Autor: ").append(lib.getIdAutor()).append("\n");
                reporte.append("------------------------------------------\n");
            }
        }

        if (!hayLibros) {
            reporte.append("No hay libros en esta categoría");
        }

        JOptionPane.showMessageDialog(null, reporte.toString(),
                "Libros por Categoría",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void reporteLibrosPorAutor(int idAutor) {
        List<Libro> Lista = ListarLibro();
        StringBuilder reporte = new StringBuilder();
        reporte.append("LIBROS DEL AUTOR ID: ").append(idAutor).append("\n\n");

        boolean hayLibros = false;

        for (Libro lib : Lista) {
            if (lib.getIdAutor() == idAutor) {
                hayLibros = true;
                reporte.append("Título: ").append(lib.getNombre()).append("\n");
                reporte.append("Editorial: ").append(lib.getEditorial()).append("\n");
                reporte.append("Categoría: ").append(lib.getIdCategoria()).append("\n");
                reporte.append("Local: ").append(lib.getIdLocal()).append("\n");
                reporte.append("------------------------------------------\n");
            }
        }

        if (!hayLibros) {
            reporte.append("No se encontraron libros de este autor");
        }

        JOptionPane.showMessageDialog(null, reporte.toString(),
                "Libros por Autor",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void buscarLibros(String busqueda) {
        List<Libro> Lista = ListarLibro();
        StringBuilder reporte = new StringBuilder();
        reporte.append("RESULTADOS DE BÚSQUEDA\n\n");

        boolean encontrado = false;
        busqueda = busqueda.toLowerCase();

        for (Libro lib : Lista) {
            if (lib.getNombre().toLowerCase().contains(busqueda)
                    || lib.getEditorial().toLowerCase().contains(busqueda)) {
                encontrado = true;
                reporte.append("ID: ").append(lib.getIdLibro()).append("\n");
                reporte.append("Título: ").append(lib.getNombre()).append("\n");
                reporte.append("Editorial: ").append(lib.getEditorial()).append("\n");
                reporte.append("Categoría: ").append(lib.getIdCategoria()).append("\n");
                reporte.append("Autor: ").append(lib.getIdAutor()).append("\n");
                reporte.append("Local: ").append(lib.getIdLocal()).append("\n");
                reporte.append("------------------------------------------\n");
            }
        }

        if (!encontrado) {
            reporte.append("No se encontraron libros que coincidan con '")
                    .append(busqueda).append("'");
        }

        JOptionPane.showMessageDialog(null, reporte.toString(),
                "Resultados de Búsqueda",
                JOptionPane.INFORMATION_MESSAGE);
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
