/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.cedenaResponsabilidad;

/**
 *
 * @author iJona
 */
public class Aprobador extends AprobadorInstruccion{

    private AprobadorInstruccion next;
    @Override
    public void analizarInstruccion(String comando) {
        
        AprobadorInstruccion gestorTabla = new GestorTabla();
        this.setNext(gestorTabla);
        
        AprobadorInstruccion gestorRegistros = new GestorRegistros();
        gestorTabla.setNext(gestorRegistros);
        
        AprobadorInstruccion tablaSelectRegistros = new TablaSelecRegistros();
        gestorRegistros.setNext(tablaSelectRegistros);
        
        AprobadorInstruccion tablaUnirRegistros = new TablaUnirRegistros();
        tablaSelectRegistros.setNext(tablaUnirRegistros);
        
        this.next.analizarInstruccion(comando);
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
