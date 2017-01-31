/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;


import Logica.Metodos;
import Logica.templateMethod.Template;
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
public class RegistrosAAgregar extends Registro {
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
        
        //
        if(new File(ruta).exists()){
            if(extraerCampos(ruta)){
                if(!claveExisteEnTabla(ruta,"")){
                        if(camposCumplenLongitud()){
                            
                            Metodos.duplicarArchivo(rutaAux, ruta);
                            Metodos.duplicarArchivo("metaAux.csv", "METADATA.csv");
                            this.metodoTemplate(ruta);
                        }
                        else
                            ventanaPrincipal.salidaPrintln("los campos no cumplen la longitud");
                }
                else
                    ventanaPrincipal.salidaPrintln("el campo clave correspondiente a '"+clave+"' ya existe en otra tabla.");
            }
            else
                ventanaPrincipal.salidaPrintln("no se pudo extraer los campos");
        }
        else
            ventanaPrincipal.salidaPrintln("la tabla no existe");
        
    }

    protected void actualizarTabla( String ruta) {
        CsvReader lectura ;
        PrintWriter escritura; 
        try{
                lectura = new CsvReader("tabAux.csv");
                escritura = new PrintWriter(ruta);
                while(lectura.readRecord()){
                    escritura.println(lectura.getRawRecord());
                }
                //
                int i=0;
                String registro="";
                while (i<campos.size()){
                    registro=registro.concat(campos.get(i));
                    if(i!=campos.size()-1)
                        registro = registro+",";
                        
                    i++;
                }
                escritura.println(registro);
                lectura.close();
                escritura.close();
                new File("tabAux.csv").delete();
            }catch (FileNotFoundException ex) {
                System.out.println("archivo no encontrado, duplicado");
            } catch (IOException ex) {
                System.out.println("no se pudo duplicar...!");
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
                        numRegistros=Integer.parseInt(lectura.get(posNRegistros))+1;
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
            Logica.Tabla tabla= new Logica.Tabla(new File(palabras.get(2)+".csv"));
            int i =0;
            while(i<tablas.size()){
                if(tablas.get(i).getUrl().equals(tabla.getUrl())){
                    tablas.get(i).setNumeroRegistros(numRegistros);
                    break;
                }
                i++;
            }
            ventanaPrincipal.actualizarInterfazPrincipal(ruta);
        } catch (IOException ex) {
                System.out.println("error !! , "+ex);
            }
    }
    
    private boolean extraerCampos(String ruta) {
        int i =0;
        campos=new ArrayList<>();
        while(i<palabras.size()){
            if(palabras.get(i).equals("VALOR")){
                i++;
                while(i<palabras.size()){
                    if(!palabras.get(i).equals(","))
                        campos.add(palabras.get(i));
                    i++;
                }
                break;
            }
            i++;
        }
        int j=0;
        while(j<tablas.size()){
            if(tablas.get(j).getNombre().equals(ruta/*nombre.csv del archivo*/)){
                if(campos.size()==tablas.get(j).getNumeroCampos())
                    return true;
                else
                    ventanaPrincipal.salidaPrintln("la tabla permite "+String.valueOf(tablas.get(j).getNumeroCampos())+" campos");
            }
            j++;
        }
    
        return false;
    }

    private boolean camposCumplenLongitud() {
        int i = 0;
        while(i< campos.size()){
            if(campos.get(i).length() > longitudTabla){
                System.out.println("el campo "+campos.get(i)+"no cumple con la longitud de "+longitudTabla);
                return false;
                
            }
            i++;
        }
        return true;
    }

  
    
    
}
/*
CREAR REGISTRO nombre_tabla VALOR vCampo1 , vCampo2 ,... , vCampoN
*/ 