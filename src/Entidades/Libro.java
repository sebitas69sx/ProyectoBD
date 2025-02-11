/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class Libro{
    private int IdLibro;
    private String Nombre;
    private String Editorial;
    private int IdLocal;
    private int IdAutor;
    private int IdCategoria;

    public Libro() {
    }

    public Libro(int IdLibro, String Nombre, String Editorial, int IdLocal, int IdAutor, int IdCategoria) {
        this.IdLibro = IdLibro;
        this.Nombre = Nombre;
        this.Editorial = Editorial;
        this.IdLocal = IdLocal;
        this.IdAutor = IdAutor;
        this.IdCategoria = IdCategoria;
    }

    public int getIdLibro() {
        return IdLibro;
    }

    public void setIdLibro(int IdLibro) {
        this.IdLibro = IdLibro;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    public int getIdLocal() {
        return IdLocal;
    }

    public void setIdLocal(int IdLocal) {
        this.IdLocal = IdLocal;
    }

    public int getIdAutor() {
        return IdAutor;
    }

    public void setIdAutor(int IdAutor) {
        this.IdAutor = IdAutor;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    
}
