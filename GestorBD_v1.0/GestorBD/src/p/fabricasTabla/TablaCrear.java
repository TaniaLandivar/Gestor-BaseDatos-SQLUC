/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.fabricasTabla;

import Logica.Metodos;
import Presentacion.VentanaCrear;
import com.csvreader.CsvReader;
import gestorbd.GestorBD;
import static gestorbd.GestorBD.tablas;
import static gestorbd.GestorBD.ventanaPrincipal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import p.templateMethod.Template;

/**
 *
 * @author iJona
 */
public class TablaCrear extends Tabla {
    @Override
    public void operarTabla(String comando) {
        palabras= Metodos.particionarComando(comando);
       // obtencion de los campos
        campos=obtenerCampos();
        
        String ruta=palabras.get(2)+".csv";
        if(!new File(ruta).exists()){
            
            if(campos.contains(palabras.get(palabras.size()-3))){
                    this.metodoTemplate(ruta);
                    GestorBD.ventanaPrincipal.salidaPrintln("Tabla creada con exito!!!");
            }
            else
                GestorBD.ventanaPrincipal.salidaPrintln("la clave no coincide con ningun campo");
                            
        }
        else
            GestorBD.ventanaPrincipal.salidaPrintln("La tabla ya existe!!");
                            
    }
    
    protected ArrayList<String> obtenerCampos() {
         ArrayList<String> campos= new  ArrayList<>();
         int i=0;
         
        while(true){
            if(palabras.get(i).equals("CAMPOS")){
                i++;
                while(!palabras.get(i).equals("CLAVE")){
                    if(!palabras.get(i).equals(","))
                        campos.add(palabras.get(i));
                     i++;
                }
                break;
            }
            i++;
        }
        return campos;
    }
    @Override
    protected void actualizarTabla(String ruta) {
        
        PrintWriter archivo;
        try {
            String linea = "";      // cabezera
            for (int i = 0; i < campos.size(); i++){
                linea=linea.concat(campos.get(i));
                if(i!=campos.size()-1)
                    linea = linea+",";
            }
            archivo = new PrintWriter(ruta);
            archivo.println(linea);
            archivo.close();
            /////////////
            
        } catch (FileNotFoundException ex) {
            System.out.println("error: "+ ex);
        } catch (IOException ex) {
            System.out.println("error: "+ ex);
        }

    }
    @Override
    protected void actualizarMetaData() {
        String registroTabla="";
                                                //          bandera,nombre,nReg,campos,clave,longitud
        registroTabla=registroTabla.concat("1,"+palabras.get(2)+","+"0"+",");
        //concatena los campos sequidos de un ;
        for (int i = 0; i < campos.size(); i++){
            registroTabla=registroTabla.concat(campos.get(i));
            if(i!=campos.size()-1)
                registroTabla = registroTabla+";";
        }
        registroTabla=registroTabla.concat(","+palabras.get(palabras.size()-3) );   //concatena la clave
        registroTabla= registroTabla.concat(","+palabras.get(palabras.size()-1) );  //concatena la longitud

        //
        PrintWriter metaWrite;
        PrintWriter metaAuxWrite;
        CsvReader metaRead;
        try {
            
            metaAuxWrite = new PrintWriter("METADATA_AUX.csv");
            metaRead = new CsvReader("METADATA.csv");
            while(metaRead.readRecord()){
                metaAuxWrite.println(metaRead.getRawRecord());
            }
            metaRead.close();
            metaAuxWrite.close();
            metaRead = new CsvReader("METADATA_AUX.csv");
            metaWrite = new PrintWriter("METADATA.csv");
            while(metaRead.readRecord()){
                metaWrite.println(metaRead.getRawRecord());
            }
            metaWrite.println(registroTabla);
            metaRead.close();
            metaWrite.close();
            new File("METADATA_AUX.csv").delete();
            
        } catch (FileNotFoundException ex) {
            System.out.println("error: "+ ex);
        } catch (IOException ex) {
            System.out.println("error: "+ ex);
        }
    }
@Override
    protected void finalizarOperacion(String ruta) {
        try{
            File file =  new File(ruta);
            Logica.Tabla tabla = new Logica.Tabla(file);
            tabla.setClave(palabras.get(palabras.size()-3));
            tabla.setLongitud(Integer.parseInt(palabras.get(palabras.size()-1)));
             if (GestorBD.tablaNoExiste(tabla))
                tablas.add(tabla);
            else
                 GestorBD.ventanaPrincipal.salidaPrintln("Muestro un mensaje de tabla ya existe");
        }
        catch(Exception e){
            System.out.println("error: "+ e);
        }
        ventanaPrincipal.actualizarInterfazPrincipal(ruta);
        
    }
    
}
