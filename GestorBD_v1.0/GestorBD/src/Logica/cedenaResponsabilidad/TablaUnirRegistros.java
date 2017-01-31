/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.cedenaResponsabilidad;

import Logica.Metodos;
import com.csvreader.CsvReader;
import static Presentacion.GestorBD.tablas;
import static Presentacion.GestorBD.ventanaPrincipal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author iJona
 */
public class TablaUnirRegistros extends AprobadorInstruccion{
//  UNIR nombreTabla1, nombreTabla2 POR nombreCampo(Tiene que ser el mismo nombre del campo en ambos archivos) = "algo"

    private AprobadorInstruccion next;
    ArrayList<String> palabras ;
    int nCampoClaveTab1, nCampoClaveTab2;
    @Override
    public void analizarInstruccion(String comando) {
        if (Metodos.evaluarExpresionDeLaCadena(comando).equals("UNIR")){
            palabras = Metodos.particionarComando(comando);
            
            if(Metodos.validarUnir(palabras)){
                String ruta1 =palabras.get(1)+".csv";
                String ruta2 =palabras.get(3)+".csv";
                String nomCampo= palabras.get(5);
                if(new File(ruta1).exists()  && new File(ruta2).exists()){
                     if(tablaTieneRegistros(ruta1) && tablaTieneRegistros(ruta2)){
                         if(camposExisten(ruta1,ruta2,nomCampo) ){
                             Logica.Tabla tabla;
                            try{
                                unirRegistros(ruta1,ruta2);
                                File file = new File("tabUnion.csv");
                                tabla = new Logica.Tabla(file);
                                tabla.setNombre("Union por "+nomCampo);
                                ventanaPrincipal.actualizarTabla(tabla);
                                ventanaPrincipal.repaint();
                                file.delete();
                            }catch(Exception e){
                                        System.out.println("error : "+e);
                            }
                    
                        }
                        else
                             ventanaPrincipal.salidaPrintln("el nombre del campo: "+nomCampo+" no existe en alguna de las tablas");
                        
                    }
                    else
                         ventanaPrincipal.salidaPrintln("Una de las tablas no tiene registros");
                        
                }
                else                
                    ventanaPrincipal.salidaPrintln("Las tablas no existen!");
            }
            else
                ventanaPrincipal.salidaPrintln("Error de sintaxis para unir las tablas");
        }    
            
        
        else{
            next.analizarInstruccion(comando);
        }
    }

    @Override
    public void setNext(AprobadorInstruccion Ap) {
        next = Ap;
    }

    @Override
    public AprobadorInstruccion getNext() {
        return next;
    }

    private String evaluarExpresion(String comando) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean camposExisten(String ruta1,String ruta2, String nomCampo) {
        CsvReader lectura;
        int cont= 0;
        try{
                lectura = new CsvReader(ruta1);
                lectura.readRecord();
                int i=0;
                while (i<lectura.getColumnCount()){
                    if(nomCampo.equals(lectura.get(i)) ){
                        nCampoClaveTab1=i;
                        cont +=1;
                        break;
                    }
                    i++;
                }
                lectura.close();
                lectura = new CsvReader(ruta2);
                lectura.readRecord();
                i=0;
                while (i<lectura.getColumnCount()){
                    if(nomCampo.equals(lectura.get(i)) ){
                        nCampoClaveTab2=i;
                            cont++;
                        break;
                    }
                    i++;
                }
                lectura.close();
                    if(cont==2){
                        //System.out.println("ambas tablas contienen el campo");
                        return  true;
                    }
                
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
                            return true;
                        }
                    i++;
                }
                
            }catch (IOException ex) {
                System.out.println("no se pudo comprobar si tiene registros la tabla!");
            }
        return false;
        
    }

    private void unirRegistros(String ruta1, String ruta2) {
        CsvReader lectura1;
        CsvReader lectura2;
        PrintWriter escritura;
        try{
                lectura1 = new CsvReader(ruta1);
                lectura2 = new CsvReader(ruta2);
                escritura = new PrintWriter("tabUnion.csv");
                lectura1.readRecord();
                lectura2.readRecord();
                String cabezera = lectura1.getRawRecord()+",";
                int i=0;
                while(i<lectura2.getColumnCount()){
                    if(i !=nCampoClaveTab2){// no se tiene que concatenar el campo que comparten ambas tablas
                        cabezera = cabezera.concat(lectura2.get(i));
                        
                            cabezera = cabezera+",";
                    }
                    i++;
                }
                String a = cabezera.substring(0, cabezera.length()-1);
                escritura.println(a);
                lectura2.close();
                i=0;
                while (lectura1.readRecord()){
                        String campo=lectura1.get(nCampoClaveTab1);
                        String registroModificado ="";
                        lectura2 = new CsvReader(ruta2);
                        lectura2.readRecord();
                        while(lectura2.readRecord()){
                            if(campo.equals(lectura2.get(nCampoClaveTab2))){
                                registroModificado = lectura1.getRawRecord()+",";
                                ArrayList<String > registro = new ArrayList<>();
                                int j=0;
                                while(j<lectura2.getColumnCount()){
                                    if(j!=nCampoClaveTab2){
                                        registro.add(lectura2.get(j));
                                    }
                                    j++;
                                }
                                j=0;
                                String linea="";
                                while(j<registro.size()){
                                    linea = linea.concat(registro.get(j));
                                    if(j!=registro.size()-1)
                                        linea = linea+",";
                                    j++;
                                }
                                registroModificado =registroModificado.concat(linea);
                                escritura.println(registroModificado);
                                //System.out.println("insertando reg: "+registroModificado);
                            }
                        }
                        lectura2.close();
                    }
                    lectura1.close();
                    escritura.close();
                
            }catch (FileNotFoundException ex) {
                        System.out.println("archivo no encontrado, delete reg()");
            } catch (IOException ex) {
                System.out.println("no se pudo agregar validar clave , delete reg...!");
            }
      
    }

    
}
