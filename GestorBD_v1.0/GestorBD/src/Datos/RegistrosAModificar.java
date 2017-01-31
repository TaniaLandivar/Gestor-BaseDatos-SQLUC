/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Logica.Metodos;
import Presentacion.VentanaCrear;
import com.csvreader.CsvReader;
import static Presentacion.GestorBD.tablas;
import static Presentacion.GestorBD.ventanaPrincipal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Logica.fabricaRegistros.Registro;

/**
 *
 * @author iJona
 */
public class RegistrosAModificar extends Registro{
    ArrayList<String> palabras;
    ArrayList<String> campos;
    int nCampoClave;
    
    int numRegistros=0, longitudTabla=0;
    String clave="";
    String campo1,campo2;
    String claveReg;
    
/*
MODIFICAR REGISTRO nombre_tabla CLAVE valorCampoClave CAMPO campo POR valor_campo_nuevo
*/
    @Override
    public void operarRegistros(String comando) {
        palabras= Metodos.particionarComando(comando);
        campo1=palabras.get(6);
        campo2=palabras.get(8);
        String ruta =palabras.get(2)+".csv"; 
        File file=new File(ruta);
        claveReg=palabras.get(4);
        if(file.exists()){
             if(tablaTieneRegistros(ruta)){
                Metodos.duplicarArchivo("tabAux.csv",ruta);
                if(claveExisteEnTabla(ruta,claveReg)) {       //  si se va a modificar el campo clave hay que verificar que el campo a reemplazar no exista
                   
                    
                    if(campoAReemplazarNoEsClave(ruta)){
                        if(campo2.length()<=longitudTabla){
                            this.metodoTemplate(ruta);
                        }
                        else
                            ventanaPrincipal.salidaPrintln("el campo a reemplazar exede el tamaño del permitido que es "+String.valueOf(longitudTabla));
                    }
                    else 
                        ventanaPrincipal.salidaPrintln("El campo a reemplazar ya existe en la tabla como campo clave");
                        
                }
                else
                    ventanaPrincipal.salidaPrintln("la clave a modificar no existe!");
                     
             }
             else
                 ventanaPrincipal.salidaPrintln("la tabla no tiene registros");        
        }
        else    
            ventanaPrincipal.salidaPrintln("la tabla no existe!");
    }

    
    protected void actualizarTabla(String ruta) {
        CsvReader lectura;
        PrintWriter escritura;
        try{
            lectura = new CsvReader("tabAux.csv");
            escritura = new PrintWriter(ruta);
            lectura.readRecord();
            escritura.println(lectura.getRawRecord());
            String linea="";
            while(lectura.readRecord()){
                if(lectura.get(nCampoClave).equals(claveReg)){ // si los campos claves coinciden
                    linea="";
                    int cont =0,i=0;
                    ArrayList<String> partes = Metodos.particionarComando(lectura.getRawRecord());
                    while(i<partes.size()){
                        if(partes.get(i).equals(campo1)){
                            partes.set(i, campo2);   // reemplazo el campo
                            break;
                        }
                        else if(i==partes.size()-1){
                            System.out.println("el campo "+campo1+" no se encuentra en los registros");
                        }
                        i++;
                    }
                    while(cont<partes.size()){
                        linea=linea.concat(partes.get(cont));
                        cont++;
                    }
                    escritura.println(linea);// escribe la linea con el numReg actualizado
                }
                else
                    escritura.println(lectura.getRawRecord());

            }
            lectura.close();
            escritura.close();
            new File("tabAux.csv").delete();
        }catch (FileNotFoundException ex) {
            System.out.println("archivo no encontrado , actualizar Modificacion de registros.\n "+ex);
        } catch (IOException ex) {
            System.out.println("no se pudo abrir los archivos...!"+ex);
        }
    }

    protected void actualizarMetaData() {
        System.out.println("pasando la actualizacion del meta en modificar Registros");
    }

    protected void finalizarOperacion(String ruta) {
         //  Modifica en memoria los registros de la tabla
        
                ventanaPrincipal.actualizarInterfazPrincipal(ruta);
        
    }

    private boolean campoAReemplazarNoEsClave(String ruta) {
        if(claveReg.equals(campo1)){
            CsvReader lectura;
            try{
                lectura = new CsvReader(ruta);
                lectura.readRecord();
                while (lectura.readRecord()){
                    if(campo2 /*valor de la clave */.equals(lectura.get(nCampoClave))){
                        return false;
                    }
                }
                lectura.close();
            }catch (FileNotFoundException ex) {
                        System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return true;
    }
   
}