/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.fabricaRegistros;

import Datos.RegistrosAModificar;
import Datos.RegistrosAEliminar;
import Datos.RegistrosAAgregar;

/**
 *
 * @author iJona
 */
public class FabricaRegistros {
     public  Registro crearOperacionRegistros(String comando) {
    
         if (evaluarInstruccion(comando).equals("CREAR")){
            // instanciar clase CrearTabla
            return new RegistrosAAgregar();
        }
        else if(evaluarInstruccion(comando).equals("ELIMINAR")){
            // instanciar clase EliminarTabla
            return new RegistrosAEliminar();
        }
        else if(evaluarInstruccion(comando).equals("MODIFICAR")){
            // instanciar clase ModificarTabla
             System.out.println("paso a modi");
            return new RegistrosAModificar();
        }
        return null;
    }

    public   String evaluarInstruccion(String comando) {
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
