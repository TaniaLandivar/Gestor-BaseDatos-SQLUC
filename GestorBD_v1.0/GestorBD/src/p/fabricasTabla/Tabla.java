/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.fabricasTabla;

import java.util.ArrayList;
import p.templateMethod.Template;

/**
 *
 * @author iJona
 */
public abstract class  Tabla extends Template {
    ArrayList<String> palabras;
   // obtencion de los campos
    ArrayList<String> campos;
    public abstract void operarTabla(String comando);
    
}
