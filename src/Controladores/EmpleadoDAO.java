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
    
    public String Cant(){
        List<Empleado> Lista = ListarEmpleado();
        return Lista.size() + "";
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

    public void reporteEmpleadosPorLocal(int idLocal) {
        List<Empleado> Lista = ListarEmpleado();
        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DE EMPLEADOS - LOCAL ").append(idLocal).append("\n\n");

        boolean hayEmpleados = false;

        for (Empleado emp : Lista) {
            if (emp.getIdLocal() == idLocal) {
                hayEmpleados = true;
                reporte.append("ID: ").append(emp.getIdEmpleado()).append("\n");
                reporte.append("Nombre: ").append(emp.getNombre()).append(" ");
                reporte.append(emp.getApellido()).append("\n");
                reporte.append("Cédula: ").append(emp.getCedula()).append("\n");
                reporte.append("Teléfono: ").append(emp.getTelefono()).append("\n");
                reporte.append("Dirección: ").append(emp.getDireccion()).append("\n");
                reporte.append("------------------------------------------\n");
            }
        }

        if (!hayEmpleados) {
            reporte.append("No se encontraron empleados en el local ").append(idLocal);
        }

        JOptionPane.showMessageDialog(null, reporte.toString(),
                "Empleados del Local " + idLocal,
                JOptionPane.INFORMATION_MESSAGE);
    }
     public void reporteEmpleadosPorPuesto(int idPuesto) {
        List<Empleado> Lista = ListarEmpleado();
        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DE EMPLEADOS - PUESTO ").append(idPuesto).append("\n\n");

        boolean hayEmpleados = false;

        for (Empleado emp : Lista) {
            if (emp.getIdPuesto() == idPuesto) {
                hayEmpleados = true;
                reporte.append("ID: ").append(emp.getIdEmpleado()).append("\n");
                reporte.append("Nombre: ").append(emp.getNombre()).append(" ");
                reporte.append(emp.getApellido()).append("\n");
                reporte.append("Cédula: ").append(emp.getCedula()).append("\n");
                reporte.append("Teléfono: ").append(emp.getTelefono()).append("\n");
                reporte.append("Dirección: ").append(emp.getDireccion()).append("\n");
                reporte.append("------------------------------------------\n");
            }
        }

        if (!hayEmpleados) {
            reporte.append("No se encontraron empleados con el puesto ").append(idPuesto);
        }

        JOptionPane.showMessageDialog(null, reporte.toString(),
                "Empleados con el Puesto" + idPuesto,
                JOptionPane.INFORMATION_MESSAGE);
    }
    

    public void reporteConteoEmpleados() {
        List<Empleado> Lista = ListarEmpleado();
        java.util.Map<Integer, Integer> conteoLocal = new java.util.HashMap<>();

       
        for (Empleado emp : Lista) {
            conteoLocal.merge(emp.getIdLocal(), 1, Integer::sum);
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("CONTEO DE EMPLEADOS POR LOCAL\n\n");

       
        java.util.List<Integer> localesOrdenados = new java.util.ArrayList<>(conteoLocal.keySet());
        java.util.Collections.sort(localesOrdenados);

        for (Integer local : localesOrdenados) {
            reporte.append("Local ").append(local)
                    .append(": ").append(conteoLocal.get(local))
                    .append(" empleados\n");
        }

        JOptionPane.showMessageDialog(null, reporte.toString(),
                "Conteo de Empleados por Local",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void buscarEmpleadosJOption(String busqueda) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("RESULTADOS DE BÚSQUEDA\n\n");

        String sql = "SELECT * FROM empleado WHERE nombre LIKE ? OR apellido LIKE ?";

        try (Connection connection = new Conexion().ObtenerConexion(); PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, "%" + busqueda + "%");
            pst.setString(2, "%" + busqueda + "%");
            ResultSet rs = pst.executeQuery();

            boolean encontrado = false;
            while (rs.next()) {
                encontrado = true;
                reporte.append("ID: ").append(rs.getInt("idEmpleado")).append("\n");
                reporte.append("Nombre Completo: ").append(rs.getString("nombre"))
                        .append(" ").append(rs.getString("apellido")).append("\n");
                reporte.append("Cédula: ").append(rs.getString("cedula")).append("\n");
                reporte.append("Teléfono: ").append(rs.getString("telefono")).append("\n");
                reporte.append("Local: ").append(rs.getInt("idLocal")).append("\n");
                reporte.append("------------------------------------------\n");
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "No se encontraron empleados con ese criterio de búsqueda",
                        "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, reporte.toString(),
                        "Resultados de búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void reporteEstadisticasPersonal() {
        List<Empleado> Lista = ListarEmpleado();
        int maxEmpleadosLocal = 0;
        int minEmpleadosLocal = Integer.MAX_VALUE;
        int localMayor = 0;
        int localMenor = 0;

        int maxEmpleadosPuesto = 0;
        int minEmpleadosPuesto = Integer.MAX_VALUE;
        int puestoMayor = 0;
        int puestoMenor = 0;

       
        java.util.Map<Integer, Integer> conteoLocal = new java.util.HashMap<>();
        java.util.Map<Integer, Integer> conteoPuesto = new java.util.HashMap<>();

       
        for (Empleado emp : Lista) {
  
            int localCount = conteoLocal.getOrDefault(emp.getIdLocal(), 0) + 1;
            conteoLocal.put(emp.getIdLocal(), localCount);

          
            int puestoCount = conteoPuesto.getOrDefault(emp.getIdPuesto(), 0) + 1;
            conteoPuesto.put(emp.getIdPuesto(), puestoCount);
        }

    
        for (java.util.Map.Entry<Integer, Integer> entry : conteoLocal.entrySet()) {
            if (entry.getValue() > maxEmpleadosLocal) {
                maxEmpleadosLocal = entry.getValue();
                localMayor = entry.getKey();
            }
            if (entry.getValue() < minEmpleadosLocal) {
                minEmpleadosLocal = entry.getValue();
                localMenor = entry.getKey();
            }
        }

       
        for (java.util.Map.Entry<Integer, Integer> entry : conteoPuesto.entrySet()) {
            if (entry.getValue() > maxEmpleadosPuesto) {
                maxEmpleadosPuesto = entry.getValue();
                puestoMayor = entry.getKey();
            }
            if (entry.getValue() < minEmpleadosPuesto) {
                minEmpleadosPuesto = entry.getValue();
                puestoMenor = entry.getKey();
            }
        }

       
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("ESTADÍSTICAS DE PERSONAL\n\n");
        mensaje.append("LOCALES:\n");
        mensaje.append("Local con más empleados: Local ").append(localMayor)
                .append("\nTotal empleados: ").append(maxEmpleadosLocal).append("\n\n");
        mensaje.append("Local con menos empleados: Local ").append(localMenor)
                .append("\nTotal empleados: ").append(minEmpleadosLocal).append("\n\n");

        mensaje.append("PUESTOS:\n");
        mensaje.append("Puesto con más empleados: Puesto ").append(puestoMayor)
                .append("\nTotal empleados: ").append(maxEmpleadosPuesto).append("\n\n");
        mensaje.append("Puesto con menos empleados: Puesto ").append(puestoMenor)
                .append("\nTotal empleados: ").append(minEmpleadosPuesto);

        JOptionPane.showMessageDialog(null, mensaje.toString(),
                "Estadísticas de Personal", JOptionPane.INFORMATION_MESSAGE);
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
