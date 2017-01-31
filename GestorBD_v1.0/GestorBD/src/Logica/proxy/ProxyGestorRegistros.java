/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.proxy;

import Logica.Metodos;
import Logica.cedenaResponsabilidad.GestorRegistros;
import Logica.fabricaRegistros.FabricaRegistros;
import Logica.fabricaRegistros.Registro;

/**
 *
 * @author iJona
 */
public class ProxyGestorRegistros extends GestorRegistros{
    @Override
    public void ejecutarInstruccion(String comando) {
        if(Metodos.evaluarComandosRegistros(comando)){// si es valido para crear, modificar o eliminar
            Registro registro = new FabricaRegistros().crearOperacionRegistros(comando);
            
            if (registro!=null){
                registro.operarRegistros(comando);
            }      
        }
        
    }

    
    
}
