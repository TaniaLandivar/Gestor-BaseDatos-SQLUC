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
import java.util.logging.Level;
import java.util.logging.Logger;
import p.templateMethod.Template;

/**
 *
 * @author iJona
 */
class TablaEliminar extends Tabla {
 
    public TablaEliminar() {
    }

    @Override
    public void operarTabla(String comando) {
        palabras= Metodos.particionarComando(comando);
        String ruta =palabras.get(2)+".csv";
        if(new File(ruta).exists()){
                Metodos.duplicarArchivo("metaAux.csv","METADATA.csv");
                this.metodoTemplate(ruta);     
        }
        else
            GestorBD.ventanaPrincipal.salidaPrintln("la tabla no existe!!");
    }
    protected void actualizarTabla(String ruta) {
        File f = new File(ruta);
        f.delete();
    }
    protected void actualizarMetaData() {
        PrintWriter fileWriter;
        CsvReader fileRead;
        try {
            fileWriter = new PrintWriter("METADATA.csv");
            fileRead=new CsvReader("metaAux.csv");
            while(fileRead.readRecord()){
                if(fileRead.get(1).equals(palabras.get(2))){
                    String aux=fileRead.getRawRecord() ;
                    char[]caracteres=aux.toCharArray();
                    caracteres[0]='0';
                    fileWriter.println(String.valueOf(caracteres));
                }
                else
                    fileWriter.println(fileRead.getRawRecord());
            }
            fileRead.close();
            fileWriter.close();
            /////////////

           new File("metaAux.csv").delete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaCrear.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaCrear.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void finalizarOperacion(String ruta) {
        
        //  remover la tabla en memoria
        File file =  new File(ruta);
        try{
            Logica.Tabla tabla = new Logica.Tabla(file);
            int i =0;
            while(true){
                if(tablas.get(i).getUrl().equals(tabla.getUrl()) ){
                    tablas.remove(i);
                    break;
                }
                i++;
            }
        }
        catch(Exception e) {System.out.println("error : "+e);}
        ventanaPrincipal.actualizarInterfazPrincipal(ruta);
    }
    

    
    
}
