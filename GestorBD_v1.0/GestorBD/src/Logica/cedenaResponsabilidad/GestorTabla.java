/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.cedenaResponsabilidad;

import Logica.Metodos;
import Presentacion.GestorBD;
import Logica.proxy.ProxyGestorTabla;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iJona
 */
public  class GestorTabla extends AprobadorInstruccion {
    private AprobadorInstruccion next;
    
    public void ejecutarInstruccion(String comando){}
    
    @Override
    public void analizarInstruccion(String comando) {
        if (Metodos.evaluarExpresionDeLaCadena(comando).equals("TABLA")){
           // GestorBD.ventanaPrincipal.salidaPrintln("Pasa la tabla al proxy.");
            GestorTabla proxy = new ProxyGestorTabla();
            proxy.ejecutarInstruccion(comando);
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
    
    
    
    
    
}
