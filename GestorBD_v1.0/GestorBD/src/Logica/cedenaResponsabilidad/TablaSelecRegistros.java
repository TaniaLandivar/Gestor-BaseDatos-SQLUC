/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.cedenaResponsabilidad;

import Logica.Metodos;
import com.csvreader.CsvReader;
import Presentacion.GestorBD;
import static Presentacion.GestorBD.tablas;
import static Presentacion.GestorBD.ventanaPrincipal;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Logica.fabricaRegistros.Registro;
import Datos.RegistrosAAgregar;

/**
 *
 * @author iJona
 */
public class TablaSelecRegistros extends AprobadorInstruccion{
    int nCampoClave=0;
    ArrayList<String> palabras;
    private AprobadorInstruccion next;
    @Override
    public void analizarInstruccion(String comando) {
        if (Metodos.evaluarExpresionDeLaCadena(comando).equals("SELECCIONAR")){
            
            palabras = Metodos.particionarComando(comando);
            if(Metodos.validarSeleccionar(palabras)){
                String ruta =palabras.get(2)+".csv";
                String nomCampo= palabras.get(4);
                String campo= palabras.get(7);
                File file = new File("tabSelec.csv");
                if(new File(ruta).exists()){
                     if(tablaTieneRegistros(ruta)){
                        if(campoExiste(nomCampo,ruta)){
                            if(campoASeleccionarExiste(ruta, campo)){
                                Logica.Tabla tabla;
                                try{
                                    tabla = new Logica.Tabla(file);
                                    ventanaPrincipal.actualizarTabla(tabla);
                                    ventanaPrincipal.repaint();
                                    file.delete();
                                }catch(Exception e){
                                            System.out.println("error : "+e);
                                }
                            }
                            else
                                ventanaPrincipal.salidaPrintln("el campo a seleccionar '"+ campo + "' no existe en la tabla ");
                        }
                        else
                            ventanaPrincipal.salidaPrintln("el nombre del campo '"+nomCampo+"' no existe en la tabla");
                     }
                    else
                         ventanaPrincipal.salidaPrintln("la tabla no tiene registros");
                }
                else 
                    ventanaPrincipal.salidaPrintln("La tabla no existe!");
            }
            else 
                ventanaPrincipal.salidaPrintln("Error de sintaxis para seleccionar tablas");
        }
        else{
            
            next.analizarInstruccion(comando);
        }
    }

    @Override
    public void setNext(AprobadorInstruccion Ap) {
        next =Ap;
    }

    @Override
    public AprobadorInstruccion getNext() {
        return next;
    }

    private String evaluarExpresion(String comando) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean campoExiste(String nomCampo, String ruta) {
        CsvReader lectura;
        try{
                lectura = new CsvReader(ruta);
                lectura.readRecord();
                int i=0;
                //sacar el  campo clave!
                /*  obtiene la posicion de la clave en la Tabla*/
                while (i<lectura.getColumnCount()){
                    if(nomCampo.equals(lectura.get(i)) ){
                        nCampoClave=i;
                        System.out.println("campo clav agr reg: "+nCampoClave);
                        return true;
                    }
                    i++;
                
                }
                lectura.close();                
            }catch (FileNotFoundException ex) {
                        System.out.println("archivo no encontrado, delete reg()");
            } catch (IOException ex) {
                System.out.println("no se pudo agregar validar clave , delete reg...!");
            }
        return false;
    }
    private boolean tablaTieneRegistros(String ruta) {
        Logica.Tabla tabla;        
        try{
                tabla = new Logica.Tabla(new File(ruta));
                int i =0;
                while (i<tablas.size()){
                    if(tablas.get(i).getUrl().equals(tabla.getUrl()))
                        if(tabla.getNumeroRegistros()>0){
                            System.out.println("la tabla tiene registros...");
                            return true;
                        }
                    i++;
                }
                
            }catch (IOException ex) {
                System.out.println("no se pudo comprobar si tiene registros la tabla!");
            }
        return false;
        
    }
     private boolean campoASeleccionarExiste(String ruta, String campo) {
        CsvReader lectura;
        PrintWriter escritura;
        boolean retorno = false;
        try{
                lectura = new CsvReader(ruta);
                escritura= new PrintWriter("tabSelec.csv");
                lectura.readRecord();
                escritura.println(lectura.getRawRecord());
                while (lectura.readRecord()){
                    if(campo.equals(lectura.get(nCampoClave))){
                        escritura.println(lectura.getRawRecord());      //  escribo el registro seleccionado en el archivo
                        retorno= true;
                    }
                }
                lectura.close();
                escritura.close();
                
            }catch (FileNotFoundException ex) {
                        System.out.println("archivo no encontrado, delete reg()");
            } catch (IOException ex) {
                System.out.println("no se pudo agregar validar clave , delete reg...!");
            }
        return retorno;
    }

/*
    private void actualizarPantallaConTabla(String ruta, String campo) {
        System.out.println("entro a acutalizar pantalla");
        GestorBD.ventanaPrincipal.table.removeAll();
        GestorBD.ventanaPrincipal.table.repaint();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        CsvReader archivo;
        File file;
        try{
            
            modeloTabla.setColumnIdentifiers(tabla.getCampos());
            System.out.println("getCampos del table: "+tabla.getCampos().toString()+" numCampo: "+tabla.getNumeroCampos());
            archivo = new CsvReader(tabla.getUrl());
            archivo.readRecord();
            //
            while(archivo.readRecord()){
                Object[] registro = new String [ tabla.getNumeroCampos()];
                for (int i = 0 ; i< tabla.getNumeroCampos();i++){
                    registro[i]= archivo.get(i);
                }
                System.out.println("");
                modeloTabla.addRow(registro);
            }
            archivo.close();
            System.out.println("paso la actTab");
            GestorBD.ventanaPrincipal.table.setSelectionBackground(Color.MAGENTA);
            GestorBD.ventanaPrincipal.table.setSelectionForeground(Color.DARK_GRAY);
            GestorBD.ventanaPrincipal.table.setModel(modeloTabla);
            GestorBD.ventanaPrincipal.table.setSelectionMode(1);
            GestorBD.ventanaPrincipal.nombreTabla.setText("Seleccion de "+campo+" en la tabla "+ruta);
            GestorBD.ventanaPrincipal.repaint();
            //GestorBD.ventanaPrincipal.setVisible(false);
           new File("tabSelec.csv").delete();
        }
        catch(IOException e){
            System.out.println("error al actualizar la tabla... "+e);
        }
        
    }
    
    */

   
    
}
