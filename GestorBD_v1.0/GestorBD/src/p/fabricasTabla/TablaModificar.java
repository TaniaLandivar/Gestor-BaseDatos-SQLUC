/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.fabricasTabla;

import Logica.Metodos;
import Presentacion.VentanaCrear;
import com.csvreader.CsvReader;
import static gestorbd.GestorBD.tablas;
import static gestorbd.GestorBD.ventanaPrincipal;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.*;
import p.templateMethod.Template;

/**
 *
 * @author iJona
 */
class TablaModificar extends  Tabla {
    String campoAMod,campoAReemplazar;
    public TablaModificar() {
    }
    
    @Override
    public void operarTabla(String comando) {
        
        //MODIFICAR TABLA nombre_tabla CAMPO nombre_campo POR nombre_campo
        palabras= Metodos.particionarComando(comando);
        String ruta =palabras.get(2)+".csv";
        File file=new File(ruta);
        String rutaAux="metaAux.csv";
        
        if(file.exists()){  
            campoAMod=palabras.get(4);
            campoAReemplazar=palabras.get(6);
            try {
                Logica.Tabla tabla = new Logica.Tabla(file);
                if(camposValidos(tabla)){
                    Metodos.duplicarArchivo(rutaAux,"METADATA.csv");
                    this.metodoTemplate(ruta);
                    new File(rutaAux).delete();
                }
                /////////////
            } catch (IOException ex) {
                System.out.println("error al modificar tabla: "+ex);
            }
            

        }
        else System.out.println("la tabla no existe!");
    }

    protected void actualizarTabla(String ruta) {
        CsvReader fileRead;
        PrintWriter fileWriter;
        try{
            fileRead=new CsvReader(ruta);
            fileRead.readRecord();
            campos = Metodos.particionarComando(fileRead.getRawRecord());
            int i=0;
            String linea="";
            while(i<campos.size()){
                if(campos.get(i).equals(campoAMod))
                    linea=linea.concat(campoAReemplazar);
                else   
                    linea= linea.concat(campos.get(i));
                i++;                        
            }
            fileRead.close();
            fileWriter = new PrintWriter(ruta);
            fileWriter.println(linea);
            fileWriter.close();
        }catch (FileNotFoundException ex) {
            System.out.println("archivo no encontrado , actualizar Modificacion de registros.\n "+ex);
        } catch (IOException ex) {
            System.out.println("no se pudo abrir los archivos...!"+ex);
        }
            
    }
    protected void actualizarMetaData() {
        CsvReader fileRead;
        PrintWriter fileWriter;
        try{
            
            fileRead=new CsvReader("metaAux.csv");
            fileWriter = new PrintWriter("METADATA.csv");
            String linea="";
            while(fileRead.readRecord()){
                if(fileRead.get(1).equals(palabras.get(2)) && fileRead.get(0).equals("1")){// si nombres de las tablas coinciden y bandera 1
                   ArrayList<String> partes=Metodos.particionarComando(fileRead.getRawRecord());        //  particiono la linea a modificar en el metaDB
                   //recorro las partes
                   int j=0;
                  linea="";
                   while(j<partes.size()){
                       if(partes.get(j).equals(campoAMod) && j!=2 ){
                           linea=linea.concat(campoAReemplazar);
                       }
                       else
                          linea=linea.concat(partes.get(j));
                       j++;
                   }
                    fileWriter.println(linea);  // escribo la linea modificada
                }
                else
                    fileWriter.println(fileRead.getRawRecord());    // linea tal cual
            }
            
            fileRead.close();
            fileWriter.close();
        
         }catch (FileNotFoundException ex) {
            System.out.println("archivo no encontrado operacion "+ex);
        } catch (IOException ex) {
            System.out.println("no se pudo abrir los archivos...!"+ex);
        }
        
    }
    protected void finalizarOperacion(String ruta) {
        int k=0;
        while(k<tablas.size()){
            if(tablas.get(k).getUrl().equals(ruta)){   // si encuentra la tabla
                int l=0;
                while(true){
                    if(tablas.get(k).getCampos()[l].equals(campoAMod)){
                        tablas.get(k).getCampos()[l]=campoAReemplazar;break;
                    }
                    l++;
                }
            }
            k++; 
        }
        ventanaPrincipal.actualizarInterfazPrincipal(ruta);
    }
    
    //
    private boolean camposValidos(Logica.Tabla tabla) {
        int i=0;
         if(tabla.getNumeroRegistros()==0){
            while (i<tabla.getCampos().length){
                if(tabla.getCampos()[i].equals(campoAMod)) {  // si el campo a mod  existe...
                    int j = 0;
                    //  verifico que el campo a reemplazar no exista ! ! !
                    while(j<tabla.getCampos().length){
                        if (tabla.getCampos()[j].equals(campoAReemplazar)){
                           ventanaPrincipal.salidaPrintln("El campo a reemplazar ya existe");
                           return false;
                       }
                        j++;
                    }
                    
                    return true;
                }
                
                i++;
            }
             ventanaPrincipal.salidaPrintln("el campo "+campoAMod+" no existe en la tabla");
            return false;
         }
         else
             ventanaPrincipal.salidaPrintln("La tabla tiene registros y no se puede modificar");
         return false;
    }

   
    
}
