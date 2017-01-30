/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.templateMethod;

/**
 *
 * @author iJona
 */
public abstract class Template {
    
    public final void metodoTemplate(String ruta){
        actualizarTabla(ruta);
        actualizarMetaData();
        finalizarOperacion(ruta);
    }

    protected abstract  void actualizarTabla(String ruta);
    protected abstract  void actualizarMetaData();
    protected abstract void finalizarOperacion(String ruta) ;
    
}
