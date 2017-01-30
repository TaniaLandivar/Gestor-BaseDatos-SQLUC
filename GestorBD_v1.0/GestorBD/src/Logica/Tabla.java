/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author iJona
 */
public class Tabla {
    
    private String nombre;
    private String url;
    private int numeroCampos;
    private int numeroRegistros;
    private String [] campos ;
    private String clave;
    private int longitud;

    public Tabla(File file) throws IOException {
        this.nombre = file.getName();
        this.url = file.getPath();
        this.longitud=0;
        this.clave="";
        calcularNumRegistros();
        cargarCampos();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getClave() {
        return clave;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
    
    
    public void setCampo(int pos, String dato){
        this.campos[pos]= dato;
    }
//      * *                 /
    public String getNombre() {
        return nombre;
    }

    public int getNumeroCampos() {
        return numeroCampos;
    }

    public int getNumeroRegistros() {
        return numeroRegistros;
    }


    public String getUrl() {
        return url;
    }

    public String[] getCampos() {
        return campos;
    }

    public void setNumeroRegistros(int numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

    public void setNumeroCampos(int numeroCampos) {
        this.numeroCampos = numeroCampos;
    }
    
    
    private void calcularNumRegistros(){
        CsvReader archivo;
        
        try{
            archivo = new CsvReader(url);
            archivo.readRecord();
             this.numeroCampos=archivo.getColumnCount();
            int cont = 0;
            while (archivo.readRecord()){
                 cont++;
            }
            this.numeroRegistros = cont;
            archivo.close();
        }
        catch(IOException e){
            System.out.println("error calcular num campos: "+e);
        }  
    }


    private void cargarCampos() {
        CsvReader archivo;
        try{
            archivo = new CsvReader(url);
            archivo.readRecord();
            this.campos = new String[numeroCampos];
            int cont = 0;
            while (cont < numeroCampos){
                 this.campos[cont]= archivo.get(cont);
                 cont++;
            }
            archivo.close();
        }
        catch(IOException e){
            System.out.println("error cargar campos: "+e);
        }
        
    }    
}
