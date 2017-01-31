/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.proxy;


import Logica.Metodos;
import Presentacion.GestorBD;
import Logica.cedenaResponsabilidad.GestorTabla;
import Logica.fabricasTabla.FabricaTabla;
import Logica.fabricasTabla.Tabla;

/**
 *
 * @author iJona
 */
public class ProxyGestorTabla extends GestorTabla{

    @Override
    public void ejecutarInstruccion(String comando) {
        if(Metodos.evaluarComandosTabla(comando)){// si es valido para crear, modificar o eliminar
            Tabla tabla = new FabricaTabla().crearOperacionTablas(comando); //  Retorna una instancia para crear, modificar  o eliminar tablas
            
            if (tabla!=null){
                tabla.operarTabla(comando);
                
            }      
        }
    }
    

    public String comandoEsValido(String comando) {
        
        int i =0;
        String palabra="";
         while(true){
            
            if(comando.charAt(i)!=' ' )               
                palabra+= comando.charAt(i);
            
            else if(comando.charAt(i)==' ')
                break;
            
             i++;
         }
         return palabra;
    }
    
    
}
