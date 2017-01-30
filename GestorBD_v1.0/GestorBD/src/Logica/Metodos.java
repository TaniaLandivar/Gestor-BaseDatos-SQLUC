/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import com.csvreader.CsvReader;
import gestorbd.GestorBD;
import static gestorbd.GestorBD.ventanaPrincipal;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author iJona
 */
public class Metodos {
    //  metodo para entrar en la cadena...
    public static String evaluarExpresionDeLaCadena(String comando) {
        int indice = 0;
        String palabra = "";
        while (indice<comando.length()){
            
            if (comando.charAt(indice) ==' '){
                if(palabra.equals("UNIR") || palabra.equals("SELECCIONAR"))
                    return palabra;
                indice++;
                palabra="";
                while(comando.charAt(indice)!=' '){
                    palabra += comando.charAt(indice);
                    indice++;
                }
                if(comando.charAt(indice)==' ')
                        return palabra;
            }
            palabra += comando.charAt(indice);
            indice++;
        }
        return null;
    }
    // particiona el comando
    public static ArrayList<String> particionarComando(String comando){
        int i = 0;
        String palabra = "";
        ArrayList<String> palabras = new ArrayList<>();
        while(i< comando.length()){
            
            if(comando.charAt(i)!=' ' ){
                if(comando.charAt(i)==','|| comando.charAt(i)=='"' || comando.charAt(i)==';'
                  || comando.charAt(i)=='“'|| comando.charAt(i)=='”'){
                    if(!palabra.equals("" )){
                        palabras.add(palabra);
                        palabra="";
                    }
                    palabra+= comando.charAt(i);
                    //  agrego el caracter especial si no a llegado a la ultima posicion
                    if( i!= comando.length()-1){
                        palabras.add(palabra);
                        palabra="";
                    }
                    
                }
                else
                    palabra+= comando.charAt(i);
            }
            else{
                if(!palabra.equals("")){
                    palabras.add(palabra);
                    palabra="";
                }
                    
            }
            i++;
        }
        palabras.add(palabra);  //  agrega la ultima palabra
        return palabras;
    }
    
    public static boolean evaluarComandosTabla(String comando){
          
        int caso;
        int i;
        int w = 0;
        
        //Identificacion del tipo de comando_ingresado
        StringBuffer atributo = new StringBuffer();
       
        for(i = 0; i< comando.length(); i++)
        {
            if(comando.charAt(i)!=' ' )
            {
                atributo.append(comando.charAt(i));
            }
            else
            {
                if(w<1){
                    atributo.append(comando.charAt(i));
                }
                w++;
                if(w==2)
                    break;
            }
        }
        
        //Asignacion tipo
        if(atributo.toString().equals("CREAR TABLA")){
            caso = 1;
        }
        else if(atributo.toString().equals("ELIMINAR TABLA")){
            caso = 2;
        }
        else if(atributo.toString().equals("MODIFICAR TABLA")){
            caso = 3;
        }
        else{
            ventanaPrincipal.salidaPrintln("No entendemos esa orden");
            return false;
        }
        //Elimino los espacios para identificar el nombre de la tabla
        for(i=i+1;i<comando.length();i++)
        {
            if(comando.charAt(i)!=' ')
                break;
        }
        if(i == comando.length()){
            ventanaPrincipal.salidaPrintln("Lo sentimos no entendemos esa orden");
            return false;
        }
        //Identificacion del nombre de la tabla
        atributo = new StringBuffer();
        for(i = i;i<comando.length();i++)
        {
            if(comando.charAt(i)!=' ')
                atributo.append(comando.charAt(i));
            else{
                break;
            }
        }
        if(caso == 1){
            //Elimino los espacios
             for(i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    break;
            }
            if(i == comando.length()){
                System.out.println("Lo sentimos no entendemos esa orden");
                return false;
            }
             //Identificacion la palabra reservada campo
            atributo = new StringBuffer();
            for(i = i;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    atributo.append(comando.charAt(i));
                else{
                    break;
                }
            }
            if(!atributo.toString().equals("CAMPOS")){
                ventanaPrincipal.salidaPrintln("No se encuentra la palabra reservada CAMPOS");
                return false;
            }
            //Elimino los espacios
             for(i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    break;
            }
            if(i == comando.length()){
                System.out.println("Lo sentimos no entendemos esa orden");
                return false;
            }
            //Obtengo el nombre de los campos
            atributo = new StringBuffer();
            StringBuffer palabra_aux = new StringBuffer();
            for(i = i;i<comando.length();i++){
                if(comando.charAt(i)!=' '){
                    palabra_aux.append(comando.charAt(i));
                }
                else{
                    if((!palabra_aux.toString().equals("CLAVE")) && (i <= comando.length())){
                        atributo.append(palabra_aux);
                    }
                    else{
                        break;
                    }
                    palabra_aux = new StringBuffer();
                }
              
            }
            if(i == comando.length()){
                ventanaPrincipal.salidaPrintln("Error de sintaxis");
                return false;
            }
            StringBuffer nombre_campo = new StringBuffer();
            for(int j = 0;j<atributo.length();j++ ){
                if(atributo.charAt(j)!=','){
                    nombre_campo.append(atributo.charAt(j));
                }
                else{
                    nombre_campo = new StringBuffer();
                }
            }
            nombre_campo = new StringBuffer();
             //Elimino los espacios
            for(i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    break;
            }
            if(i == comando.length()){
                System.out.println("Lo sentimos no entendemos esa orden");
                return false;
            }
             //Identificacion de la clave de la tabla
            atributo = new StringBuffer();
            for(i = i;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    atributo.append(comando.charAt(i));
                else{
                    break;
                }
            }
            //Elimino los espacios
             for(i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    break;
            }
            if(i == comando.length()){
                System.out.println("Lo sentimos no entendemos esa orden");
                return false;
            }
             //Identificacion la palabra reservada LONGITUD
            atributo = new StringBuffer();
            for(i = i;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    atributo.append(comando.charAt(i));
                else{
                    break;
                }
            }
            if(!atributo.toString().equals("LONGITUD")){
                ventanaPrincipal.salidaPrintln("Falta la palabra reservada LONGITUD");
                return false;
            }
            //Elimino los espacios
             for(i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    break;
            }
            if(i == comando.length()){
                System.out.println("Lo sentimos no entendemos esa orden");
                return false;
            }
            //Identificacion la LONGITUD
            atributo = new StringBuffer();
            for(i = i;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    atributo.append(comando.charAt(i));
                else{
                    break;
                }
            }
            if(isNumeric(atributo.toString()) == false){
                ventanaPrincipal.salidaPrintln("La longitud debe ser un valor numérico");
                return false;
            }
            for (i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' '){
                    System.out.println("Error en sintaxis.");
                    return false;
                }
                else
                    break;
            }
            //System.out.println("Crear la tabla");           
            return true;
        }
        else if(caso == 2){
            for (i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' '){
                    ventanaPrincipal.salidaPrintln("Error en sintaxis.");
                    return false;
                }
                else
                    break;
            }
            System.out.println("Opcion eliminar tabla");
            return true;
        }
        else if(caso == 3){
            //Elimino los espacios
             for(i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    break;
            }
            if(i == comando.length()){
                System.out.println("Lo sentimos no entendemos esa orden");
                return false;
            }
             //Identificacion la palabra reservada campo
            atributo = new StringBuffer();
            for(i = i;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    atributo.append(comando.charAt(i));
                else{
                    break;
                }
            }
            if(!atributo.toString().equals("CAMPO")){
                ventanaPrincipal.salidaPrintln("No se encuentra la palabra reservada CAMPO");
                return false;
            }
            //Elimino los espacios
             for(i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    break;
            }
            if(i == comando.length()){
                System.out.println("Lo sentimos no entendemos esa orden");
                return false;
            }
            //Identificacion el nombre del campo1
            atributo = new StringBuffer();
            for(i = i;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    atributo.append(comando.charAt(i));
                else{
                    break;
                }
            }
            //Elimino los espacios
             for(i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    break;
            }
            if(i == comando.length()){
                System.out.println("Lo sentimos no entendemos esa orden");
                return false;
            }
             //Identificacion la palabra reservada POR
            atributo = new StringBuffer();
            for(i = i;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    atributo.append(comando.charAt(i));
                else{
                    break;
                }
            }
            if(!atributo.toString().equals("POR")){
                ventanaPrincipal.salidaPrintln("No se encuentra la palabra reservada POR");
                return false;
            }
            //Elimino los espacios
             for(i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    break;
            }
            if(i == comando.length()){
                System.out.println("Lo sentimos no entendemos esa orden");
                return false;
            }
            //Identificacion el nombre del campo2
            atributo = new StringBuffer();
            for(i = i;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' ')
                    atributo.append(comando.charAt(i));
                else{
                    break;
                }
            }
            for (i=i+1;i<comando.length();i++)
            {
                if(comando.charAt(i)!=' '){
                    ventanaPrincipal.salidaPrintln("Error en sintaxis.");
                    return false;
                }
                else
                    break;
            }
            System.out.println("Opcion modificar tabla");
            return true;
           
        }
        else{
            return false;
        }
    }
    
    public static boolean validarUnir(ArrayList<String> palabras){
        
        Iterator<String> it = palabras.iterator();
        while(it.hasNext()){
            System.out.println("... "+it.next());
        }// UNIR nombre_tabla1, nombre_tabla2 POR nombre_campo
        if (palabras.get(0).equals("UNIR")){
            if (palabras.get(2).equals(",")){
                if (palabras.get(4).equals("POR")){
                    return true;
                }
                else
                    ventanaPrincipal.salidaPrintln("Falta la palabra reservada POR");
            }
            else
                ventanaPrincipal.salidaPrintln("Falta ',' para separar el nombre de las tablas");
        }
        else 
            ventanaPrincipal.salidaPrintln("Error con la parabra reservada UNIR");
        
        return false;
    }
    public static boolean validarSeleccionar(ArrayList<String> palabras){
        
        if (palabras.get(0).equals("SELECCIONAR")){
            if (palabras.get(1).equals("DE")){
                if (palabras.get(3).equals("DONDE")){
                    if (palabras.get(5).equals("=")){
                        if (palabras.get(6).equals("\"") || palabras.get(6).equals("“")){
                            if(palabras.get(8).equals("\"") || palabras.get(8).equals("”"))
                                return true;
                            else 
                                ventanaPrincipal.salidaPrintln("Falta cerrar la comilla");
                        }
                        else
                            ventanaPrincipal.salidaPrintln("Falta abrir la comilla");
                   }
                    else
                        ventanaPrincipal.salidaPrintln("Falta el signo '='");
                }
                else
                    ventanaPrincipal.salidaPrintln("Falta la palabra reservada DONDE");
            }
            else
                ventanaPrincipal.salidaPrintln("Falta la parabra reservada DE");
        }
        return false;
    }
    // duplica un .csv
    public static void duplicarArchivo(String rutaAux, String ruta) {
        CsvReader fileRead;
        PrintWriter fileWriter;
        try{
            fileWriter = new PrintWriter(rutaAux);
                fileRead=new CsvReader(ruta);
                while(fileRead.readRecord()){
                    fileWriter.println(fileRead.getRawRecord());
                }
            fileWriter.close();
            fileRead.close();
        }catch (FileNotFoundException ex) {
            System.out.println("archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("no se pudo duplicar...!");
        }
    }

    public static boolean evaluarComandosRegistros(String comando) {
        int i;
        int w = 0;
        boolean aux=false;
        ArrayList<String> palabras_reservadas = new ArrayList<>();
        
        
        //Identificacion del tipo de instruccion_tabla
        StringBuffer atributo = new StringBuffer();
        for(i = 0; i< comando.length(); i++)
        {
            if(comando.charAt(i)!=' ' && comando.charAt(i)!=',')
            {
                atributo.append(comando.charAt(i));
            }
            else
            {
                if(comando.charAt(i)==','){
                    palabras_reservadas.add(atributo.toString());
                    palabras_reservadas.add(",");
                }
                else{
                    palabras_reservadas.add(atributo.toString());
                   
                }
                atributo = new StringBuffer();
            }
        }
        palabras_reservadas.add(atributo.toString());
        //Asignacion tipo
        if((palabras_reservadas.get(0)+" "+palabras_reservadas.get(1)).equals("CREAR REGISTRO")){
            if((palabras_reservadas.get(3)).equals("VALOR")){
                aux=true;
            }
            else{
                ventanaPrincipal.salidaPrintln("Falta la palabra VALOR");
                aux=false;
            }
        }else if((palabras_reservadas.get(0)+" "+palabras_reservadas.get(1)).equals("ELIMINAR REGISTRO")){
            if((palabras_reservadas.get(3)).equals("CLAVE")){
                aux=true;
            }
            else{
                ventanaPrincipal.salidaPrintln("Falta la palabra CLAVE");
                aux=false;
            }
        }else if((palabras_reservadas.get(0)+" "+palabras_reservadas.get(1)).equals("MODIFICAR REGISTRO")){
            if((palabras_reservadas.get(3)).equals("CLAVE")){
                if((palabras_reservadas.get(5)).equals("CAMPO")){
                    if((palabras_reservadas.get(7)).equals("POR")){
                        aux=true;
                    }
                    else{
                        ventanaPrincipal.salidaPrintln("Falta la palabra POR");
                        aux=false;
                    }
                }
                else{
                    ventanaPrincipal.salidaPrintln("Falta la palabra CAMPO");
                    aux=false;
                }
            }
            else{
                ventanaPrincipal.salidaPrintln("Falta la palabra CLAVE");
                aux=false;
            }
        }else
            return false;
        return aux; 
    }
    public static String extraerClaveDelMeta(ArrayList<String> partes) {
        CsvReader lectura;
        String valorRetorno=null;
            try{
                lectura = new CsvReader("METADATA.csv");
                while (lectura.readRecord()){
                     if(partes.get(2).equals(lectura.get(1))){  //  nombres coinciden..
                         valorRetorno=lectura.get(4);
                         break;
                     }
                }
                System.out.println("val clv: "+valorRetorno);
                lectura.close();
            }catch (FileNotFoundException ex) {
                System.out.println("archivo no encontrado, extraerClvMeta");
            } catch (IOException ex) {
                System.out.println("no se pudo xtraer clave del meta!!");
            }
            return valorRetorno;
    }

    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
}
