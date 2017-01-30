/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.cedenaResponsabilidad;

import Logica.Metodos;
import p.proxy.ProxyGestorRegistros;
import p.proxy.ProxyGestorTabla;

/**
 *
 * @author iJona
 */
public class GestorRegistros extends AprobadorInstruccion{

    private AprobadorInstruccion next ;
    public void ejecutarInstruccion(String comando){}
    
    @Override
    public void analizarInstruccion(String comando) {
        if (Metodos.evaluarExpresionDeLaCadena(comando).equals("REGISTRO")){
            
            GestorRegistros proxy = new ProxyGestorRegistros();
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

    private String evaluarExpresion(String comando) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
