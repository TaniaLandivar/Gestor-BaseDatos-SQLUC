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
public  abstract class AprobadorInstruccion {
    public abstract void analizarInstruccion(String comando);
    public abstract void setNext(AprobadorInstruccion Ap);
    public abstract AprobadorInstruccion getNext();
    
}
