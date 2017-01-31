/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.fabricasTabla;

import Datos.TablaModificar;
import Datos.TablaEliminar;
import Datos.TablaCrear;

/**
 *
 * @author iJona
 */
public class FabricaTabla {

    public  Tabla crearOperacionTablas(String comando) {
    
         if (evaluarInstruccion(comando).equals("CREAR")){
            // instanciar clase CrearTabla
            return new TablaCrear();
        }
        else if(evaluarInstruccion(comando).equals("ELIMINAR")){
            // instanciar clase EliminarTabla
            return new TablaEliminar();
        }
        else if(evaluarInstruccion(comando).equals("MODIFICAR")){
            // instanciar clase ModificarTabla
            return new TablaModificar();
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
