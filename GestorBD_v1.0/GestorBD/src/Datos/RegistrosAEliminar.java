/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Logica.Metodos;
import com.csvreader.CsvReader;
import static Presentacion.GestorBD.tablas;
import static Presentacion.GestorBD.ventanaPrincipal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Logica.fabricaRegistros.Registro;

/**
 *
 * @author iJona
 */
public class RegistrosAEliminar extends Registro{
    
    ArrayList<String> palabras;
    ArrayList<String> campos;
    int nCampoClave;
    
    int numRegistros=0, longitudTabla=0;
    String clave="";
    @Override
    public void operarRegistros(String comando) {
       
        palabras=Metodos.particionarComando(comando);
        
        String ruta=palabras.get(2)+".csv";
        String rutaAux="tabAux.csv";
        String claveReg=palabras.get(4);
        if(new File(ruta).exists()){
            if(tablaTieneRegistros(ruta)){
                if(claveExisteEnTabla(ruta,claveReg )){
                    Metodos.duplicarArchivo("metaAux.csv", "METADATA.csv");
                    Metodos.duplicarArchivo(rutaAux, ruta);
                    this.metodoTemplate(ruta);            
                }
                else
                    ventanaPrincipal.salidaPrintln("la clave a eliminar no existe");
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
                
                //
                lectura = new CsvReader("tabAux.csv");
                lectura.readRecord();
                escritura = new PrintWriter(ruta);
                escritura.println(lectura.getRawRecord());
                while (lectura.readRecord()){
                    if(!palabras.get(4)/*valor de la clave a eliminar*/.equals(lectura.get(nCampoClave))){
                        escritura.println(lectura.getRawRecord());
                    }
                }
                lectura.close();
                escritura.close();
                new File("tabAux.csv").delete();
                
            }catch (FileNotFoundException ex) {
                System.out.println("archivo no encontrado, delete reg()");
            } catch (IOException ex) {
                System.out.println("no se pudo agregar validar clave , delete reg...!");
            }
    }
    protected void actualizarMetaData() {
        CsvReader lectura ;
        PrintWriter escritura; 
        try{
                lectura = new CsvReader("metaAux.csv");
                escritura = new PrintWriter("METADATA.csv");
                lectura.readRecord();
                escritura.println(lectura.getRawRecord());
                String linea="";
                int posNRegistros=2;
                while(lectura.readRecord()){
                    if(lectura.get(1).equals(palabras.get(2))){//2 es el valor de // si el nombre coincide
                        linea="";
                        int cont =0;
                        ArrayList<String> partes = Metodos.particionarComando(lectura.getRawRecord());
                        numRegistros=Integer.parseInt(lectura.get(posNRegistros))-1;
                        partes.set(4, String.valueOf(numRegistros));// incremento en 1 el numero de registros de la tabla
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
                new File("metaAux.csv").delete();
                
                
            }catch (FileNotFoundException ex) {
                System.out.println("archivo no encontrado, ag reg");
            } catch (IOException ex) {
                System.out.println("no se pudo agregar registro...!");
            }
    }
    protected void finalizarOperacion(String ruta) {
        //  Modifica en memoria los registros de la tabla
        try{
                Logica.Tabla tabla= new Logica.Tabla(new File(ruta));
                int i =0;
                while(i<tablas.size()){
                    if(tablas.get(i).getUrl().equals(tabla.getUrl())){
                        tablas.get(i).setNumeroRegistros(numRegistros);
                        break;
                    }
                    i++;
                }
                ventanaPrincipal.actualizarInterfazPrincipal(ruta);
        }catch(IOException e){
            System.err.println("error : "+e);
        }
    }
    //**
    
    
    
        
    
}
/*
ELIMINAR REGISTRO nombre_tabla CLAVE valorCampoClave
*/
