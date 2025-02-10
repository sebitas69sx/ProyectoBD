/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Prestamo{
    private int IdPrestamo;
    private int IdLibro;
    private int IdUsuario;
    private Date FechaPrestamo;
    private Date FechaDevolucion;
    private String Estado;

    public Prestamo() {
        
    }

    public Prestamo(int IdPrestamo, int IdLibro,int IdUsuario, Date FechaPrestamo, Date FechaDevolucion, String Estado) {
        this.IdPrestamo = IdPrestamo;
        this.IdLibro = IdLibro;
        this.IdUsuario = IdUsuario;
        this.FechaPrestamo = FechaPrestamo;
        this.FechaDevolucion = FechaDevolucion;
        this.Estado = Estado;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public int getIdPrestamo() {
        return IdPrestamo;
    }

    public void setIdPrestamo(int IdPrestamo) {
        this.IdPrestamo = IdPrestamo;
    }

    public int getIdLibro() {
        return IdLibro;
    }

    public void setIdLibro(int IdLibro) {
        this.IdLibro = IdLibro;
    }

    public Date getFechaPrestamo() {
        return FechaPrestamo;
    }

    public void setFechaPrestamo(Date FechaPrestamo) {
        this.FechaPrestamo = FechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return FechaDevolucion;
    }

    public void setFechaDevolucion(Date FechaDevolucion) {
        this.FechaDevolucion = FechaDevolucion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
    
    
}
