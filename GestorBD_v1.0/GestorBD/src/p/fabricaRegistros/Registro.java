/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.fabricaRegistros;

import Logica.Metodos;
import com.csvreader.CsvReader;
import static gestorbd.GestorBD.tablas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import p.templateMethod.Template;

/**
 *
 * @author iJona
 */
public abstract class Registro extends Template{
    
    ArrayList<String> palabras;
    ArrayList<String> campos;
    int nCampoClave;
    
    int numRegistros=0, longitudTabla=0;
    String clave="";
    public abstract void operarRegistros(String comando);
    //
    // para el caso de agregar registros paso el parametro claveRegistro como ""
    public boolean claveExisteEnTabla(String ruta,String claveRegistro) {
        CsvReader lectura;
        extraerClaveDelMeta();
        try{
                lectura = new CsvReader(ruta);
                lectura.readRecord();
                int i=0;
                //sacar el  campo clave!
                /*  obtiene la posicion de la clave en la Tabla*/
                while (i<lectura.getColumnCount()){
                    if(clave.equals(lectura.get(i)) ){
                        nCampoClave=i;
                        break;
                    }
                    i++;
                }
                lectura.close();
                //
               
                lectura = new CsvReader(ruta);
                lectura.readRecord();
                if(claveRegistro.equals("")) { //  para el caso de agregar registros..
                    claveRegistro=campos.get(nCampoClave);
                }
                while (lectura.readRecord()){
                    if(claveRegistro /*valor de la clave */.equals(lectura.get(nCampoClave))){
                        return true;
                    }
                }
                lectura.close();
                
                
            }catch (FileNotFoundException ex) {
                        System.out.println("archivo no encontrado, delete reg()");
            } catch (IOException ex) {
                System.out.println("no se pudo agregar validar clave , delete reg...!");
            }
        return false;
    }
    public boolean tablaTieneRegistros(String ruta) {
        Logica.Tabla tabla;        
        try{
                tabla = new Logica.Tabla(new File(ruta));
                int i =0;
                while (i<tablas.size()){
                    if(tablas.get(i).getUrl().equals(tabla.getUrl()))
                        if(tabla.getNumeroRegistros()>0){
                            return true;
                        }
                    i++;
                }
                
            }catch (IOException ex) {
                System.out.println("no se pudo comprobar si tiene registros la tabla!");
            }
        return false;
        
    }
    public void extraerClaveDelMeta() {
        CsvReader lectura;
            try{
                lectura = new CsvReader("METADATA.csv");
                while (lectura.readRecord()){
                     if(palabras.get(2).equals(lectura.get(1))){
                         clave=lectura.get(4);
                         longitudTabla =Integer.parseInt(lectura.get(5));
                         break;
                     }
                }
                lectura.close();
            }catch (FileNotFoundException ex) {
                System.out.println("archivo no encontrado, extraerClvMeta");
            } catch (IOException ex) {
                System.out.println("no se pudo xtraer clave del meta!!");
            }
    }


}
